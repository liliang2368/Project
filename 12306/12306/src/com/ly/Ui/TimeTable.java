package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ly.dao.StationInfoDao;
import com.ly.dao.railwayDao;
import com.ly.dao.trainTimeDao;
import com.ly.util.StringUtil;
import com.ly.util.algorithm;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TimeTable extends Composite {
	private Table table;
	private Combo combo_start;
	private Combo combo_end;
	private algorithm alg=new algorithm();
	private StationInfoDao info=new StationInfoDao();
	private railwayDao dao=new railwayDao();
	private zhuanhuantime time=new zhuanhuantime();
	/** 
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws 
	 */
	public TimeTable(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(74, 47, 90, 24);
		label.setText("出发地：");
		combo_end = new Combo(this, SWT.NONE);
		combo_end.setBounds(455, 47, 153, 32);
		
		combo_start = new Combo(this, SWT.NONE);
		
		combo_start.addFocusListener(new FocusAdapter() {
//			combo_end.removeAll();你的错误就在这里

			//当选择出发点失去焦点是线要根据这条信息获取到经过那一条线路
			@Override
			public void focusLost(FocusEvent e) {
				combo_end.removeAll();

				//线获取到所有的线路
				try {
					List<Map<String, Object>>list=dao.FindByRoad(combo_start.getText());
					//这里已经查询到了这个站点是经过了那些线路，就可以根据那些线路查询出这个站的所有能到达的站再终点站显示出来
					for(Map<String, Object> map:list){
						System.out.println("这里应该是应该得到京广线或者是沪昆线"+map);
						List<Map<String, Object>> list01=dao.FindStationbyname(StringUtil.toObjectString(map.get("RAILWAY_NAME")));
						//将所有的信息添加到选择终点站的框里面
						for(Map<String, Object> map01:list01){
							combo_end.add(StringUtil.toObjectString(map01.get("RAILWAY_STATION")));
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//出发地
		try {
			alg.addstation(combo_start);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		combo_start.setBounds(170, 47, 153, 32);
				
		
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(376, 47, 77, 24);
		label_1.setText("目的地：");
		
	
		
		
		Button button = new Button(this, SWT.NONE);
		//查询
	
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(30, 111, 908, 609);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(171);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(174);
		tableColumn_1.setText("出发站");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(151);
		tableColumn_2.setText("到达站");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(189);
		tableColumn_3.setText("出发时间");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(180);
		tableColumn_4.setText("到达时间");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();//清楚原来所有的数据

				String start=combo_start.getText();
				String end=combo_end.getText();
				try {
					List<Map<String,Object>>list=dao.FindAllBy(start, end);//这里根据出发点和终点站查询出车次信息
					System.out.println(list);
					TableItem item=null;
					for(Map<String,Object>map:list){
						//这里是对查询出来的列车经行显示
						//这里还是要对时间经行格式化操做就是把日期给去掉，只剩下时间就行
						System.out.println(time.formattime(StringUtil.toObjectString(map.get("S_TIME1")))+"\t"+time.formattime(StringUtil.toObjectString(map.get("S_TIME2"))));
						System.out.println(map);
						item=new TableItem(table,SWT.NONE);
						item.setText(new String[]{StringUtil.toObjectString(map.get("TID").toString()),StringUtil.toObjectString(map.get("GS"))
								        ,StringUtil.toObjectString(map.get("GE")),time.formattime(StringUtil.toObjectString(map.get("S_TIME1")))
								         ,time.formattime(StringUtil.toObjectString(map.get("S_TIME2")))});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(702, 47, 114, 34);
		button.setText("查询");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
