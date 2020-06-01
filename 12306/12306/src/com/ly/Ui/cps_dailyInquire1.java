package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.text.SimpleDateFormat;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.dao.traininfoDao;
import com.ly.util.StringUtil;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

public class cps_dailyInquire1 extends Composite {
	private Text text;
	private Table table;
	private zhuanhuantime time=new zhuanhuantime();
	private railwayDao rail=new railwayDao();
	private rraletime rrale=new rraletime();
	private traininfoDao info=new traininfoDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public cps_dailyInquire1(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(442, 10, 88, 24);
		
		Button button = new Button(composite, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
			}
		});
		button.setBounds(568, 10, 80, 27);
		button.setText("确定");
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(375, 15, 61, 17);
		label.setText("选择日期");
		
		Combo combo_start = new Combo(composite, SWT.NONE);
		combo_start.setBounds(63, 10, 68, 22);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(10, 10, 36, 21);
		label_1.setText("起始站");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(139, 15, 46, 19);
		label_2.setText("终点站");
		
		Combo combo_end = new Combo(composite, SWT.NONE);
		combo_end.setBounds(199, 7, 61, 27);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(composite_1, SWT.BORDER);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("车次");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("出发站");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("到达站");
		
		TableColumn tableColumn_first = new TableColumn(table, SWT.NONE);
		tableColumn_first.setWidth(100);
		tableColumn_first.setText("第一天");
		
		TableColumn tableColumn_secong = new TableColumn(table, SWT.NONE);
		tableColumn_secong.setWidth(100);
		tableColumn_secong.setText("第二天");
		
		TableColumn tableColumn_thirt = new TableColumn(table, SWT.NONE);
		tableColumn_thirt.setWidth(100);
		tableColumn_thirt.setText("第三天");
		sashForm.setWeights(new int[] {41, 40, 324});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//先到最下面获取当前日期的票数
				table.removeAll();
				//查询出票数
				String start=combo_start.getText();//初始站
				String end=combo_end.getText();//终点站
				System.out.println(time.zhuanCalder(dateTime));
			//	Date date=new Date(time.zhuanCalder(dateTime)); //取时间 
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");//格式化字符串
				Date date=null;
				try {
					date = format.parse(time.zhuanCalder(dateTime));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}//解析字符串
				
				Calendar   calendar = new GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动 
				Date date1=calendar.getTime(); //这个时间就是日期往后推一天的
				calendar.add(calendar.DATE, 1);
				Date date2=calendar.getTime();//第二天的天数
				calendar.add(Calendar.DATE, 1);//第三天的天数
				Date date3=calendar.getTime();//第三天的天数
				tableColumn_first.setText(format.format(date));
				tableColumn_secong.setText(format.format(date1));
				tableColumn_thirt.setText(format.format(date2));

					TableItem item=null;
				try {
					List<Map<String, Object>> list =rail.FindAllBy(start, end);//根据起始站和终点站来获取所有要经过的车次
					System.out.println("获取经过起始站和终点站的车次为:"+list);
				//	System.out.println("获取到当时下订单的时间"+calder2);
					for(Map<String, Object> map:list){
						String k=rrale.really(start, end, "软座", StringUtil.toObjectString(map.get("TID")),format.format(date));//实时票数统计
						String j=rrale.really(start, end, "硬座", StringUtil.toObjectString(map.get("TID")), format.format(date));
						String i=rrale.really(start, end, "软卧", StringUtil.toObjectString(map.get("TID")), format.format(date));
						String a=rrale.really(start, end, "软座", StringUtil.toObjectString(map.get("TID")),format.format(date1));//实时票数统计
						String b=rrale.really(start, end, "硬座", StringUtil.toObjectString(map.get("TID")), format.format(date1));
						String c=rrale.really(start, end, "软卧", StringUtil.toObjectString(map.get("TID")), format.format(date1));
						String l=rrale.really(start, end, "软座", StringUtil.toObjectString(map.get("TID")),format.format(date2));//实时票数统计
						String m=rrale.really(start, end, "硬座", StringUtil.toObjectString(map.get("TID")), format.format(date2));
						String G=rrale.really(start, end, "软卧", StringUtil.toObjectString(map.get("TID")), format.format(date2));
						int sum1=Integer.parseInt(k)+Integer.parseInt(j)+Integer.parseInt(i);
						int sum2=Integer.parseInt(a)+Integer.parseInt(b)+Integer.parseInt(c);
						int sum3=Integer.parseInt(l)+Integer.parseInt(m)+Integer.parseInt(G);

						item=new TableItem(table, SWT.NONE);
						item.setText(new String []{StringUtil.toObjectString(map.get("TID")),start,end," "+sum1," "+sum2," "+sum3
						});
						}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});

	}

		
	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
