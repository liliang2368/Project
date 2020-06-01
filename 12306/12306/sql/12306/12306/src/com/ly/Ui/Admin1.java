package com.ly.Ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.StationInfoDao;
import com.ly.dao.addbianDao;
import com.ly.dao.railwayDao;
import com.ly.dao.trainTimeDao;
import com.ly.dao.traininfoDao;
import com.ly.util.StringUtil;
import com.ly.util.algorithm;
import com.ly.util.swtUtil;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class Admin1 {

	protected Shell shell;
	private Text text_sleep;
	private Text text_softseat;
	private Text text_3;
	private Text text_hard;
	private Table table;
	private Text text_sofeseat;
	private Text text_softslpper;
	private Text text_hardseatprice;
	private Table table_1;
	private trainTimeDao dao=new trainTimeDao();
	private StationInfoDao stationDao=new StationInfoDao();
	private zhuanhuantime zhuan=new zhuanhuantime();//用于时间的转换
	private Text text_1;
	private railwayDao railDao=new railwayDao();
	private Combo combo_checi ;
	private algorithm algorithm1=new algorithm();
	private Combo combo_tujinzhandian;
	private Combo combo_liechecheci;
	private Text text;
	private Text text_2;
	private DateTime dateTime_startCalder;
	private DateTime dateTime_arrive ;
	private String railyWay;
	private String railyWay2;//这个是另一个面板
	private String basc;
	private DateTime dateTime_start;
	private DateTime dateTime_end;
	private Combo combo_arriver;
	String asc="asc";
	private Text text_asc;
	private Text text_desc;
	private Text text_xianluasc;
	private Text text_roaddesc;
	private addbianDao bianDao=new addbianDao();
	private Combo combo_selectcixu;
	private Text text_yulan;
	private Combo combo_delstation;//选择删除的站台
	private Combo combo_delcheci;//选择删除的车次
	private Combo combo_selectroad;//选择线路
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private traininfoDao infoDao=new traininfoDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Admin1 window = new Admin1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws SQLException 
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException {
		shell = new Shell();
		shell.setSize(909, 726);
		shell.setText("列车信息管理");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
		Button button = new Button(tabFolder, SWT.NONE);
		button.setText("New Button");
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u6DFB\u52A0\u5217\u8F66\u4FE1\u606F");
		
		SashForm sashForm = new SashForm(tabFolder, SWT.VERTICAL);
		tabItem.setControl(sashForm);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				asc="desc";
			}
		});
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(32, 65, 38, 20);
		label.setText("\u8F66\u6B21");
		 combo_checi = new Combo(composite, SWT.READ_ONLY);

		combo_checi.setBounds(76, 62, 92, 28);
		//将所有的列车信息要添加这个框

		List<Map<String, Object>> list2=railDao.FindAll();//这里从编号表里获取到线路上运行的车次 如京广线  G1001 G1002 G1003
		for(Map<String, Object> map:list2){
			combo_checi.add(map.get("TID").toString());
			//railyWay=map.get("RAILWAY_NAME").toString();//京广线或者其他线路
		}
		combo_checi.select(0);//选择车次，所有的后续操作，都要根据车次来进行，根据车次，我已经查出了列车次运行的线路
		 combo_checi.addFocusListener(new FocusAdapter() {
			 	@Override
			 	public void focusLost(FocusEvent e) {
			 		//选择车次这里必须要设置一个监听，就是要根据选择的车次来获取运行的线路
			 		//1.先获取到选择的车次
			 		String ttd=combo_checi.getText();
			 		//2.根据车次获取到站台名
			 		try {
						Map<String, Object> map3=railDao.findBian(ttd);
						railyWay=map3.get("RAILWAY_NAME").toString();//好了,这里就是根据用户选择的车次获取到运行线路，这里是获取到京广线
						System.out.println(railyWay);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//按sac升序排列
					//都要获取运行的车次
					StringBuffer sb=new StringBuffer();
					sb.append("起始站");
					String ttId=combo_checi.getText();//获取到车次
					List<Map<String, Object>> list=null;
					try {
						list = railDao.station(ttId, "asc");
						for(Map<String, Object> map:list){
							sb.append("->"+map.get("RAILWAY_STATION").toString());
							
						}
						sb.append("->终点站");
						text_asc.setText(sb.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
					
					StringBuffer sb2=new StringBuffer();
					sb2.append("起始站");
					List<Map<String, Object>> list1;
					try {
						list1 = railDao.station(ttId, "desc");
						for(Map<String, Object> map:list1){
							sb2.append("->"+map.get("RAILWAY_STATION").toString());
							
						}
						sb2.append("->终点站");
						text_desc.setText(sb2.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		
			 	}
			 });

		

		
		Combo combo_selectchufa = new Combo(composite, SWT.NONE);

		combo_selectchufa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			
				//这里要获取次序根据列车名来获取次序
				String tid=combo_checi.getText();
				try {
					Map<String, Object> map=bianDao.SingleFindAsc(tid);//获取列车是升序还是逆序
					System.out.println();
					algorithm1.addcheci( combo_selectchufa, combo_checi,StringUtil.toObjectString(map.get("BASC")));
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
	
			}
		});

		//algorithm1.addcheci( combo_selectchufa, combo_checi,asc);//这里------这里是没问题的
		//这里选择的车站
		combo_selectchufa.select(0);
		combo_selectchufa.setBounds(76, 216, 92, 28);
		
		 combo_arriver = new Combo(composite, SWT.NONE);
		combo_arriver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String tid=combo_checi.getText();
				String  station=combo_selectchufa.getText();
				try {
					Map<String, Object> map=bianDao.SingleFindAsc(tid);
					algorithm1.add(station, combo_arriver,railyWay,StringUtil.toObjectString(map.get("BASC")));
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//这里------这里是没问题的
			
					//终点站的复选框的话就要根据线面选择的站台

			}
		});
//		System.out.println(asc);

//System.out.println("***************************************************************");
//System.out.println(combo_arriver.getText());
//System.out.println("*************************************************************");

		combo_arriver.setBounds(205, 216, 92, 28);
	
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(92, 190, 76, 20);
		label_1.setText("\u8D77\u70B9\u7AD9");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(233, 190, 76, 20);
		label_2.setText("\u7EC8\u70B9\u7AD9");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(421, 190, 76, 20);
		label_3.setText("\u8F6F\u5367\u7968\u4EF7");
		
		text_sleep = new Text(composite, SWT.BORDER);
		text_sleep.setBounds(408, 216, 73, 30);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(535, 190, 76, 20);
		label_4.setText("\u8F6F\u5EA7\u7968\u4EF7");
		
		text_softseat = new Text(composite, SWT.BORDER);
		text_softseat.setBounds(535, 218, 73, 26);
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setText("\u786C\u5EA7\u7968\u4EF7");
		text_3.setBounds(641, 187, 73, 26);
		
		text_hard = new Text(composite, SWT.BORDER);
		text_hard.setBounds(641, 216, 73, 26);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBounds(200, 130, 76, 20);
		label_5.setText("\u51FA\u53D1\u65F6\u95F4");
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER | SWT.TIME);
		dateTime.setBounds(230, 156, 92, 28);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setBounds(477, 130, 76, 20);
		label_6.setText("\u5230\u8FBE\u65F6\u95F4");
		
		DateTime dateTime_1 = new DateTime(composite, SWT.BORDER | SWT.TIME);
		dateTime_1.setBounds(519, 156, 92, 28);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				String tid=combo_checi.getText();
				String start=combo_selectchufa.getText();
				String arrives=combo_arriver.getText();
				String sleeperp=text_sleep.getText();
				String softseatp=text_softseat.getText();
				String hardseatp=text_hard.getText();
				String gotime=zhuan.zhuanCalderTime( dateTime_start,dateTime);
				String arrivertime=zhuan.zhuanCalderTime(dateTime_end, dateTime_1);
				System.out.println(start+"\t"+arrives+"\t"+sleeperp+"\t"+softseatp+"\t"+hardseatp+"\t"+gotime+"\t"+arrivertime+"\t"+tid);
				Map<String, Object> map=new HashMap<String, Object>();
//				map.put("TID", tid);
//				map.put("START", start);
//				map.put("ARRIVES", arrives);
//				map.put("SLEEPERP", sleeperp);
//				map.put("SOFTEEATP", softseatp);
//				map.put("HARDSEATP", hardseatp);
//				map.put("GOTIME", gotime);
//				map.put("ARRIVETIME", arrivertime);
				try {
					int result=dao.update(tid, start, arrives, sleeperp, softseatp, hardseatp, gotime, arrivertime);//插入
					if(result>0){
						swtUtil.showMessage(shell, "温馨提示ʾ", "插入成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "插入失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(641, 288, 98, 30);
		button_1.setText("\u63D0\u4EA4");
		
		 dateTime_start = new DateTime(composite, SWT.BORDER);
		dateTime_start.setBounds(116, 156, 110, 28);
		
		 dateTime_end = new DateTime(composite, SWT.BORDER);
		dateTime_end.setBounds(408, 156, 110, 28);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setBounds(246, 24, 76, 20);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setBounds(464, 24, 154, 20);
		label_8.setText("请选择列车运行的方向");
		
		text_asc = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
	
		text_asc.setBounds(233, 51, 609, 28);
		
		text_desc = new Text(composite, SWT.BORDER | SWT.READ_ONLY);

		text_desc.setBounds(233, 85, 609, 28);
		
		Button btnAsc = new Button(composite, SWT.NONE);

		btnAsc.setBounds(196, 54, 30, 20);
		btnAsc.setText("asc");
		
		Button btnDsc = new Button(composite, SWT.NONE);
		btnDsc.setText("dsc");
		btnDsc.setBounds(196, 85, 30, 20);
		

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button button_2 = new Button(composite_2, SWT.NONE);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				TableItem item=null;
				table.removeAll();
				try {
					List<Map<String, Object>> map=dao.SingleFind();
					System.out.println(map);
					for(Map<String, Object> a:map) {
						item=new TableItem(table, SWT.NONE);					
						item.setText(new String[]{StringUtil.toObjectString(a.get("TID")),StringUtil.toObjectString(a.get("STARTS"))
								,StringUtil.toObjectString(a.get("ARRIVES")),StringUtil.toObjectString(a.get("SLEEPERP")),StringUtil.toObjectString(a.get("SOFTSEATP"))
								,StringUtil.toObjectString(a.get("HARDSEATP")),zhuan.formattime(StringUtil.toObjectString(a.get("GOTIME"))),zhuan.formattime(StringUtil.toObjectString(a.get("ARRIVETIME")))});
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_2.setText("\u70B9\u51FB\u5237\u65B0\u5217\u8F66\u4FE1\u606F");
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table);
					table.setMenu(menu);
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText("删除");
					item.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem t = table.getItem(table.getSelectionIndex());
							String trainNum = t.getText(0);
							System.out.println(trainNum);
							
						}
					});
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(85);
		tableColumn_1.setText("\u8F66\u6B21");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(69);
		tableColumn_2.setText("\u51FA\u53D1\u7AD9");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(80);
		tableColumn_3.setText("\u5230\u8FBE\u7AD9");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(79);
		tableColumn_4.setText("\u8F6F\u5367\u7968\u4EF7");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(81);
		tblclmnNewColumn.setText("\u8F6F\u5EA7\u7968\u4EF7");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(77);
		tableColumn_5.setText("\u786C\u5EA7\u7968\u4EF7");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(132);
		tableColumn_6.setText("\u51FA\u53D1\u65F6\u95F4");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(140);
		tableColumn_7.setText("\u5230\u8FBE\u65F6\u95F4");
		sashForm_1.setWeights(new int[] {39, 249});
		sashForm.setWeights(new int[] {329, 290});
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u6DFB\u52A0\u5217\u8F66\u7ECF\u8FC7\u7AD9\u53F0");
		
		SashForm sashForm_2 = new SashForm(tabFolder, SWT.VERTICAL);
		tabItem_1.setControl(sashForm_2);
		
		Composite composite_4 = new Composite(sashForm_2, SWT.NONE);
		
		Label label_10 = new Label(composite_4, SWT.NONE);
		label_10.setBounds(90, 10, 76, 20);
		label_10.setText("\u8F66\u6B21");
		
	
		
		Label lblNewLabel = new Label(composite_4, SWT.NONE);
		lblNewLabel.setBounds(234, 10, 76, 20);
		lblNewLabel.setText("\u9014\u7ECF\u7AD9\u70B9");
		
		Label label_11 = new Label(composite_4, SWT.NONE);
		label_11.setBounds(429, 10, 105, 20);
		label_11.setText("\u5217\u8F66\u5230\u8FBE\u65F6\u95F4");
		
		DateTime dateTime_larrivertime = new DateTime(composite_4, SWT.BORDER | SWT.TIME);
		dateTime_larrivertime.setBounds(487, 36, 105, 26);
		
		Label label_12 = new Label(composite_4, SWT.NONE);
		label_12.setBounds(700, 10, 97, 20);
		label_12.setText("\u5217\u8F66\u51FA\u53D1\u65F6\u95F4");
		
		DateTime dateTime_startTime = new DateTime(composite_4, SWT.BORDER | SWT.TIME);
		dateTime_startTime.setBounds(764, 36, 92, 26);
		
		Label label_13 = new Label(composite_4, SWT.NONE);
		label_13.setBounds(118, 98, 76, 20);
		label_13.setText("\u8F6F\u5EA7\u4EF7\u683C");
		
		text_sofeseat = new Text(composite_4, SWT.BORDER);
		text_sofeseat.setBounds(118, 124, 73, 26);
		
		Label label_14 = new Label(composite_4, SWT.NONE);
		label_14.setText("\u8F6F\u5367\u4EF7\u683C");
		label_14.setBounds(263, 98, 76, 20);
		
		text_softslpper = new Text(composite_4, SWT.BORDER);
		text_softslpper.setBounds(263, 134, 73, 26);
		
		Label label_15 = new Label(composite_4, SWT.NONE);
		label_15.setText("\u786C\u5EA7\u4EF7\u683C");
		label_15.setBounds(444, 98, 76, 20);
		
		text_hardseatprice = new Text(composite_4, SWT.BORDER);
		text_hardseatprice.setBounds(444, 134, 73, 26);
		
	

		
		combo_liechecheci = new Combo(composite_4, SWT.NONE);
		//失去焦点获取到所有的车站信息
		combo_liechecheci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String ttd=combo_liechecheci.getText();//获取到列车车次，
				try {
					Map<String, Object> map3=railDao.findBian(ttd);
					railyWay2=map3.get("RAILWAY_NAME").toString();//好了,这里就是根据用户选择的车次获取到运行线路，这里是获取到京广线
					basc=map3.get("BASC").toString();//获取到列车是升序还是降序
					//这里是给途径站来添加所有的车站信息
					algorithm1.addcheci(combo_tujinzhandian, combo_liechecheci, basc);
					combo_tujinzhandian.select(0);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		combo_liechecheci.setBounds(63, 36, 92, 28);
		
		for(Map<String, Object> map:list2){
			combo_liechecheci.add(map.get("TID").toString());
		}
		combo_liechecheci.select(0);
		combo_tujinzhandian = new Combo(composite_4, SWT.NONE);

	
		combo_tujinzhandian.setBounds(222, 36, 92, 28);
		//这里是算法步骤

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_5, SWT.VERTICAL);
		
		Composite composite_6 = new Composite(sashForm_3, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_4 = new SashForm(composite_6, SWT.VERTICAL);
		
		Composite composite_8 = new Composite(sashForm_4, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button button_4 = new Button(composite_8, SWT.NONE);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//这里可以传入
				try {
					indisplay(text_1);//传入值
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_4.setText("\u9884\u89C8\u5217\u8F66\u7ECF\u8FC7\u7AD9\u53F0\u7684\u4FE1\u606F");
		
		Composite composite_9 = new Composite(sashForm_4, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_1 = new Text(composite_9, SWT.BORDER | SWT.MULTI);
		sashForm_4.setWeights(new int[] {23, 166});
		
		Composite composite_7 = new Composite(sashForm_3, SWT.NONE);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table_1);
					table_1.setMenu(menu);
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText("删除");
					item.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem t = table_1.getItem(table_1.getSelectionIndex());
							String stationName = t.getText(1);
							//根据车次删除列车时刻表
							try {
								Map<String, Object> map=dao.deleteBy(stationName);
								if(map.size()>0){
									swtUtil.showMessage(shell, "温馨提示", "删除成功");
								}else{
									swtUtil.showMessage(shell, "错误提示", "删除失败");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(stationName);	
						}
					});
				}
			}
		});
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(69);
		tableColumn_9.setText("\u8F66\u6B21");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(103);
		tableColumn_11.setText("\u7AD9\u53F0\u540D");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(142);
		tableColumn_12.setText("到站时间");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(115);
		tableColumn_13.setText("出站时间");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(107);
		tableColumn_14.setText("\u8F6F\u5367\u7968\u4EF7");
		
		TableColumn tableColumn_15 = new TableColumn(table_1, SWT.NONE);
		tableColumn_15.setResizable(false);
		tableColumn_15.setWidth(114);
		tableColumn_15.setText("\u8F6F\u5EA7\u7968\u4EF7");
		
		TableColumn tableColumn_16 = new TableColumn(table_1, SWT.NONE);
		tableColumn_16.setWidth(105);
		tableColumn_16.setText("\u786C\u5EA7\u7968\u4EF7");
		sashForm_3.setWeights(new int[] {190, 227});
		
		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("\u6DFB\u52A0\u8FD0\u884C\u7684\u8F66\u6B21");
		
		SashForm sashForm_5 = new SashForm(tabFolder, SWT.VERTICAL);
		tabItem_2.setControl(sashForm_5);
		
		Composite composite_10 = new Composite(sashForm_5, SWT.NONE);
		
		Label label_16 = new Label(composite_10, SWT.NONE);
		label_16.setBounds(29, 54, 60, 20);
		label_16.setText("添加路线");
		
		Label label_17 = new Label(composite_10, SWT.NONE);
		label_17.setBounds(235, 54, 73, 20);
		label_17.setText("途径站点");
		
		Combo combo_addroad = new Combo(composite_10, SWT.NONE);
		combo_addroad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				List<Map<String, Object>> list05=null;
				try {
					list05 = railDao.FindRoadName();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//这里查询出所有经过的线路信息。入京广线或者沪昆线等......
				for(Map<String, Object> map:list05){
					combo_selectroad.add(StringUtil.toObjectString(map.get("RAILWAY_NAME")));
				}
				
			}
		});
		combo_addroad.setBounds(99, 51, 87, 20);
		
		Combo combo_bystation = new Combo(composite_10, SWT.NONE);
		combo_bystation.setBounds(314, 51, 92, 28);
		
		Button button_5 = new Button(composite_10, SWT.NONE);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringBuffer sb=new StringBuffer();
				sb.append("预览线路信息");
				try {
					List<Map<String, Object>> list=railDao.FindRoadName();
					for(Map<String, Object> map:list){
						sb.append("\n"+"线路名->"+StringUtil.toObjectString(map.get("RAILWAY_NAME")));
						List<Map<String, Object>> list01=railDao.FindStation(StringUtil.toObjectString(map.get("RAILWAY_NAME")));
						for(Map<String, Object> map01:list01){
							
							sb.append("->"+StringUtil.toObjectString(map01.get("RAILWAY_STATION")));
						}
					}
					text_yulan.setText(sb.toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//提交，插入信息
				String railyWaily =combo_addroad.getText();
				String station=combo_bystation.getText();
				try {
					int reult=railDao.insert(railyWaily, station);
					if(reult>0){
						swtUtil.showMessage(shell, "温馨提示", "修改成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "修改失败");
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_5.setBounds(437, 53, 87, 23);
		button_5.setText("提交");
		
		text = new Text(composite_10, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text.setText("选择列车运行的线路");
		text.setBounds(130, 85, 324, 28);
		
		Label lblNewLabel_1 = new Label(composite_10, SWT.NONE);
		lblNewLabel_1.setBounds(29, 137, 66, 25);
		lblNewLabel_1.setText("选择路线");
		
		combo_selectroad = new Combo(composite_10, SWT.NONE);
		//选择车次查询出所有的线路信息

		combo_selectroad.setBounds(94, 134, 92, 28);
		
		Label label_18 = new Label(composite_10, SWT.NONE);
		label_18.setBounds(448, 137, 76, 20);
		label_18.setText("添加车次");
		
		Combo combo_addcheci = new Combo(composite_10, SWT.NONE);
		combo_addcheci.setBounds(530, 134, 92, 28);
		
		Button button_6 = new Button(composite_10, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String railyName=combo_selectroad.getText();
				
				String basc=combo_selectcixu.getText();
				String tid=combo_addcheci.getText();
				System.out.println(railyName+"\t"+basc+tid);
				try {
					int result=bianDao.insert(railyName, tid, basc);
					if(result>0){
						swtUtil.showMessage(shell, "温馨提示", "插入成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "插入失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_6.setText("提交");
		button_6.setBounds(655, 136, 87, 23);
		
		text_2 = new Text(composite_10, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text_2.setText("添加新增的路线");
		text_2.setBounds(130, 10, 324, 28);
		
		text_xianluasc = new Text(composite_10, SWT.BORDER | SWT.READ_ONLY);
		text_xianluasc.setBounds(58, 204, 728, 25);
		
		Label lblDesc = new Label(composite_10, SWT.NONE);
		lblDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				asc="desc";
				//通过京广线来得到所有的站台信息
				StringBuffer sb=new StringBuffer();
				sb.append("起始站");
				try {
					List<Map<String, Object>> list01=railDao.FindStation(combo_selectroad.getText(), asc);
					for(Map<String, Object> map:list01){
						sb.append("->"+StringUtil.toObjectString(map.get("RAILWAY_STATION")));
						
					}
					text_roaddesc.setText(sb.toString());
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		lblDesc.setBounds(11, 238, 41, 20);
		lblDesc.setText("desc");
		
		text_roaddesc = new Text(composite_10, SWT.BORDER | SWT.READ_ONLY);
		text_roaddesc.setBounds(58, 235, 728, 28);
		
		Label label_9 = new Label(composite_10, SWT.CENTER);
		label_9.setBounds(211, 173, 302, 25);
		label_9.setText("预览次序");
		
		Label lblAsc = new Label(composite_10, SWT.NONE);
		lblAsc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				asc="asc";
				//通过京广线来得到所有的站台信息
				StringBuffer sb=new StringBuffer();
				sb.append("起始站");
				try {
					List<Map<String, Object>> list01=railDao.FindStation(combo_selectroad.getText(), asc);
					for(Map<String, Object> map:list01){
						sb.append("->"+StringUtil.toObjectString(map.get("RAILWAY_STATION")));
						
					}
					text_xianluasc.setText(sb.toString());
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				
			}
		});
		lblAsc.setText("asc");
		lblAsc.setBounds(29, 207, 23, 22);
		
		Label label_19 = new Label(composite_10, SWT.NONE);
		label_19.setBounds(235, 137, 66, 20);
		label_19.setText("选择次序");
		
		combo_selectcixu = new Combo(composite_10, SWT.NONE);
		combo_selectcixu.setBounds(314, 134, 92, 28);
		combo_selectcixu.add("asc");
		combo_selectcixu.add("desc");
		
		Label label_20 = new Label(composite_10, SWT.NONE);
		label_20.setBounds(710, 10, 76, 20);
		label_20.setText("删除线路");
		
		Combo combo = new Combo(composite_10, SWT.NONE);
		combo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				combo.removeAll();
				//删除线路名取得所有的路线信息
				try {
					List<Map<String, Object>> list=bianDao.FindName();
					System.out.println(list);
					for(Map<String, Object> map:list){
					combo.add(map.get("RAILWAY_NAME").toString());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		combo.setBounds(694, 36, 92, 28);
		
		Button button_9 = new Button(composite_10, SWT.NONE);
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//删除
				try {
					int result=bianDao.delete(combo.getText());
					if(result>0){
						swtUtil.showMessage(shell, "温馨提示","删除成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "删除失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		button_9.setBounds(710, 75, 60, 20);
		button_9.setText("删除");
		Composite composite_11 = new Composite(sashForm_5, SWT.NONE);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_6 = new SashForm(composite_11, SWT.VERTICAL);
		
		Composite composite_12 = new Composite(sashForm_6, SWT.NONE);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button button_7 = new Button(composite_12, SWT.NONE);
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//预览插入的信息，先把一直的线路打印出来
				//先循环线路名京广线或者是沪昆线
				StringBuffer sb=new StringBuffer();
				sb.append("预览线路信息");
				try {
					List<Map<String, Object>> list=railDao.FindRoadName();
					for(Map<String, Object> map:list){
						sb.append("\n"+"线路名->"+StringUtil.toObjectString(map.get("RAILWAY_NAME")));
						List<Map<String, Object>> list01=railDao.FindStation(StringUtil.toObjectString(map.get("RAILWAY_NAME")));
						for(Map<String, Object> map01:list01){
							
							sb.append("->"+StringUtil.toObjectString(map01.get("RAILWAY_STATION")));
						}
					}
					text_yulan.setText(sb.toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		
			}
		});
		button_7.setText("预览插入的信息");
		
		Composite composite_13 = new Composite(sashForm_6, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_yulan = new Text(composite_13, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		
		Composite composite_14 = new Composite(sashForm_6, SWT.NONE);
		
		Label label_21 = new Label(composite_14, SWT.NONE);
		label_21.setBounds(20, 40, 76, 20);
		label_21.setText("添加票数");
		
		text_4 = new Text(composite_14, SWT.BORDER);
		text_4.setBounds(95, 50, 73, 26);
		
		Label label_22 = new Label(composite_14, SWT.NONE);
		label_22.setBounds(103, 10, 76, 20);
		label_22.setText("软卧数");
		
		Label label_23 = new Label(composite_14, SWT.NONE);
		label_23.setBounds(217, 10, 76, 20);
		label_23.setText("软座数");
		
		text_5 = new Text(composite_14, SWT.BORDER);
		text_5.setBounds(217, 50, 73, 26);
		
		Label label_24 = new Label(composite_14, SWT.NONE);
		label_24.setBounds(342, 10, 76, 20);
		label_24.setText("硬座数");
		
		text_6 = new Text(composite_14, SWT.BORDER);
		text_6.setBounds(345, 50, 73, 26);
		
		Label label_25 = new Label(composite_14, SWT.NONE);
		label_25.setBounds(510, 10, 76, 20);
		label_25.setText("选择车次");
		
		Combo combo_1 = new Combo(composite_14, SWT.NONE);
		combo_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				combo_1.removeAll();
				//获取所有的车次信息
				try {
					List<Map<String, Object>> list=bianDao.FindALL();
					for(Map<String, Object> map:list){
						combo_1.add(StringUtil.toObjectString(map.get("TID")));
					}
					combo_1.select(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//获取所有的编号
				
				
				
			}
		});
		combo_1.setBounds(499, 37, 92, 28);
		
		Button button_10 = new Button(composite_14, SWT.NONE);
		button_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//map.get("TTID"),map.get("SOFTSEATP"),map.get("SOFT1SEATP")
				//鼠标单机设置票数
				String ttid=combo_1.getText();//获取车次
				String sofeSleep=text_4.getText();
				String sofe=text_5.getText();
				String hard=text_6.getText();
				
				try {
					int result=infoDao.insert(ttid,"软卧",sofeSleep);
					int result1=infoDao.insert(ttid,"软座",sofe);
					int result2=infoDao.insert(ttid,"硬座",hard);
					if(result >0 && result1 >0 && result2 >0 ){
						swtUtil.showMessage(shell, "温馨提示", "插入票数成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "插入票数失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button_10.setBounds(648, 30, 98, 30);
		button_10.setText("设置");
		sashForm_6.setWeights(new int[] {35, 323, 179});
		sashForm_5.setWeights(new int[] {283, 360});
		Button button_3 = new Button(composite_4, SWT.NONE);


		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Map<String, Object> map1=new HashMap<String,Object>();
				//提亲要线到这里获取到车次   途径站点    列车到达时间   列车出发的时间
				String ttd=combo_liechecheci.getText();//列车的车次
				String ssid=combo_tujinzhandian.getText();//列出的途径站
				String arrivierStationTime=zhuan.zhuanCalderTime(dateTime_arrive, dateTime_larrivertime);//列车到达时间
				String gostation=zhuan.zhuanCalderTime(dateTime_startCalder, dateTime_startTime);//列车从站台出发的时间
				String softwu=text_softslpper.getText();//软卧的票价
				String hardseat=text_hardseatprice.getText();//硬座的票价
				String softseat=text_sofeseat.getText();//硬卧的票价
				//将值插入数据库
				map1.put("S_NAME", ttd);
				map1.put("S_STATNAME", ssid);
				map1.put("S_TIME1", arrivierStationTime);
				map1.put("S_TIME2", gostation);
				map1.put("S_SPRICE", softwu);
				map1.put("S_LPRICE", softseat);
				map1.put("S_PRICE", hardseat);
				int result;
				try {
					result = stationDao.update(map1);
					
					if(result>0){
						swtUtil.showMessage(shell, "温馨提示", "修改成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "修改失败");
						return ;
					}
				table_1.removeAll();
				TableItem item=null;
					List<Map<String, Object>> list03=stationDao.Allbianhao();
					//循环所有的列车车次
					List<Map<String, Object>> list=null;
					for(Map<String, Object> map3:list03){
						list=stationDao.getAllStation(StringUtil.toObjectString(map3.get("TID")));
						for(Map<String, Object> map:list){
							item=new TableItem(table_1, SWT.NONE);									
							item.setText(new String []{StringUtil.toObjectString(map.get("S_NAME")),StringUtil.toObjectString(map.get("S_STATNAME")),
								StringUtil.toObjectString(map.get("S_TIME1")),StringUtil.toObjectString(map.get("S_TIME2")),StringUtil.toObjectString(map.get("S_SPRICE")),
									StringUtil.toObjectString(map.get("S_LPRICE")),
										StringUtil.toObjectString(map.get("S_PRICE"))});
							
						}
						algorithm1.add(combo_tujinzhandian.getText(), combo_tujinzhandian,railyWay2,basc);
						System.out.println("****************************************");
						System.out.println(combo_tujinzhandian.getText());
						indisplay(text_1);//显示信息到问本框
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});

		button_3.setBounds(551, 174, 98, 30);
		button_3.setText("\u63D0\u4EA4");
		
		 dateTime_arrive = new DateTime(composite_4, SWT.BORDER);
		dateTime_arrive.setBounds(371, 34, 110, 28);
		
		 dateTime_startCalder = new DateTime(composite_4, SWT.BORDER);
		dateTime_startCalder.setBounds(648, 36, 110, 28);
		
		 combo_delcheci = new Combo(composite_4, SWT.NONE);
		combo_delcheci.setBounds(727, 98, 92, 28);
		
		combo_delstation = new Combo(composite_4, SWT.NONE);
		combo_delstation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//当其选择这个框是就会令
				combo_delcheci.setText(combo_liechecheci.getText());
				//然后就根据列车车次循环得到所有的值
				try {
					Map<String, Object> map=bianDao.GetLine(combo_liechecheci.getText());
					//查询出这趟车的编号信息//得到所有的站台信息
					System.out.println("选择的车次为"+combo_liechecheci.getText());
					List<Map<String, Object>> list=railDao.FindStation1(map.get("TID").toString(), map.get("BASC").toString());
					System.out.println("空指针异常下的"+list);
					for(Map<String, Object> map01:list){
						combo_delstation.removeAll();
						combo_delstation.add(StringUtil.toObjectString(map01.get("S_STATNAME")));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});

		combo_delstation.setBounds(727, 142, 92, 28);
		
		Button button_8 = new Button(composite_4, SWT.NONE);
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//删除
				try {
					int result=railDao.delect(combo_delcheci.getText(), combo_delstation.getText());
					if(result>0){
						swtUtil.showMessage(shell, "温馨提示", "修改成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "修改失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		button_8.setBounds(727, 174, 98, 30);
		button_8.setText("撤销");
		sashForm_2.setWeights(new int[] {218, 425});

	}
	//显示到问泵框的信息
	public void indisplay(Text text) throws SQLException{
		StringBuffer sb=new StringBuffer();
		sb.append("显示列车的预览信息为");
		//这里就要获取所有的车次
		List<Map<String,Object>> list=stationDao.Allbianhao();
		//获取大盘所有的车次信息后就必须要开始根据列车的车次信息来拼接
		for(Map<String, Object> map:list){
			System.out.println(map);
			sb.append("\n"+StringUtil.toObjectString(map.get("TID").toString()));//这里获取到所有车次再根据车次来讲站台都输出来
			List<Map<String, Object>> list01=railDao.FindStation1(map.get("TID").toString(),map.get("BASC").toString());
			//再拼接
			for(Map<String, Object> map01:list01){
				System.out.println(map01);
			sb.append("->"+StringUtil.toObjectString(map01.get("S_STATNAME")));
			}
		}
		text.setText(sb.toString());
		
	}
}
