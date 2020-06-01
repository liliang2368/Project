package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.StationInfoDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.dao.traininfoDao;
import com.ly.util.StringUtil;
import com.ly.util.algorithm;
import com.ly.util.swtUtil;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class Inquire {

	protected Shell shell;
	private Table table;
	private railwayDao dao=new railwayDao();
	private rraletime time=new rraletime();
	private algorithm alg=new algorithm();
	private StationInfoDao info=new StationInfoDao();

	private 	String k;
	private 	String j;
	private 	String i;
	private 	Combo combo_start ;//出发站
	private Combo combo_end;//终点站
	private zhuanhuantime zhuan=new zhuanhuantime();
	private String calder2;
	private Table table_1;
	public static String begin = "出发地";
	public static String end = "目的地";
	public static String trainNum ;//车次
	public static String launchTime = "出发时间";
	public static String arriveTime;//到达时间
	public static String bytime;//历时
	public static String sofeSaet;//软座
	public static String hardSeat;//硬座
	public static String softSleep;//软卧
	public static DateTime dateTime;//获取到了出发的日期
	public static String changeBegin = "";//起始站
	public static String changeEnd = "";//终点站
	public static String changeDateTime;//出发的日期
	public static String orgSeat;//列车座位的席别
	public static String time2;//这里获取到出发的日期
	public static int pid;//改票的订单编号
	public static String changeTrainNum;//改变的车次
	public static String userName = MeunUi.getUserName(); //获取用户名对数据进行修改操作
	public static String orgPrice;//原来的价格
	public static String caid;//身份证号码
	private static String pname;
	private static String ticketSpecies;
	public static String tid;
	public rraletime rrle=new rraletime();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Inquire window = new Inquire();
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
		shell.setSize(1224, 781);
		shell.setText("票务查询");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMaximized(true);
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);

		label.setText("出发地");
		label.setBounds(10, 10, 60, 20);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("目的地");
		label_1.setBounds(193, 10, 52, 20);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("日期");
		label_2.setBounds(368, 10, 37, 20);
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String str=format.format(date);
		dateTime = new DateTime(composite, SWT.BORDER);
		time2=zhuan.zhuanCalder(dateTime);//转换成日历
		dateTime.setBounds(428, 7, 110, 28);
		
		Button button_7 = new Button(composite, SWT.NONE);

		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table.removeAll();
				//查询出票数
				String start=combo_start.getText();//初始站
				String end=combo_end.getText();//终点站
				calder2=zhuan.zhuanCalder(dateTime);//转换成日历
				TableItem item=null;
				try {
					List<Map<String, Object>> list =dao.FindAllBy(start, end);//根据起始站和终点站来获取所有要经过的车次
					System.out.println("获取经过起始站和终点站的车次为:"+list);
					System.out.println("获取到当时下订单的时间"+calder2);
					for(Map<String, Object> map:list){
						k=time.really(start, end, "软座", StringUtil.toObjectString(map.get("TID")),calder2);//实时票数统计
						j=time.really(start, end, "硬座", StringUtil.toObjectString(map.get("TID")), calder2);
						i=time.really(start, end, "软卧", StringUtil.toObjectString(map.get("TID")), calder2);
//						System.out.println(map);//
						//这里获取到出发点到中点站的出发时间和到达时间
						Map<String, Object> list01=info.getTime(start, end,StringUtil.toObjectString(map.get("TID")));
;
//						System.out.println(list01);
//				
						String calder=zhuan.Time(StringUtil.toObjectString(list01.get("S_TIME2")), StringUtil.toObjectString(list01.get("S_TIME1")));
					
//						System.out.println(calder);
						//System.out.println("--------------------------------------------");
						item=new TableItem(table, SWT.NONE);
						item.setText(new String []{StringUtil.toObjectString(map.get("TID")),start,end,calder,zhuan.formattime(StringUtil.toObjectString(list01.get("S_TIME2"))),
								zhuan.formattime(StringUtil.toObjectString(list01.get("S_TIME1"))),k,j,i});
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
		button_7.setBounds(864, 51, 98, 30);
		button_7.setText("查询");
		 combo_end = new Combo(composite, SWT.NONE);
			

		combo_end.setBounds(270, 7, 92, 28);	
		
	combo_start = new Combo(composite, SWT.NONE);
	combo_start.addFocusListener(new FocusAdapter() {
		//当选择出发点失去焦点是线要根据这条信息获取到经过那一条线路
		@Override
		public void focusLost(FocusEvent e) {
			combo_end.removeAll();
			//线获取到所有的线路
			try {
				List<Map<String, Object>>list=dao.FindByRoad(combo_start.getText());
				for(Map<String, Object> map:list){
//					System.out.println("这里应该是应该得到京广线或者是沪昆线"+map);
					List<Map<String, Object>> list01=dao.FindStationbyname(map.get("RAILWAY_NAME").toString());
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
		alg.addstation(combo_start);
		
		
		combo_start.setBounds(73, 7, 92, 28);
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				System.out.println("hello kitty");
				int row = table.getSelectionIndex();//这里是获取到你选择的是那一行
				System.out.println(row);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table);
					table.setMenu(menu);
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText("点击购票");
					item.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem t = table.getItem(table.getSelectionIndex());
							trainNum = t.getText();//车次
							begin = t.getText(1);//起点站
//							System.out.println(begin);
							end = t.getText(2);//终点站
//							System.out.println(end);
							bytime=t.getText(3);//历时
//							System.out.println("历时"+bytime);
							//出发时间
							arriveTime=t.getText(4);
//							System.out.println("出发时间"+arriveTime);
							//到达时间
							launchTime = t.getText(5);
//							System.out.println("到达时间"+launchTime);
							sofeSaet=t.getText(6);//软座
//							System.out.println("软座"+sofeSaet);
							hardSeat=t.getText(7);//硬座
//							System.out.println("硬座"+hardSeat);
							softSleep=t.getText(8);//软卧
//							System.out.println("软卧"+softSleep);
							//System.out.println(launchTime);
							Cps_buyTickets buy = new Cps_buyTickets();
							try {
								buy.open();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
				}
			}
		});

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(81);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(88);
		tableColumn_1.setText("出发地");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(86);
		tableColumn_2.setText("目的地");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(73);
		tableColumn_3.setText("历时");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(99);
		tableColumn_4.setText("出发时间");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(113);
		tableColumn_5.setText("到达时间");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(98);
		tableColumn_6.setText("软座");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(89);
		tableColumn_7.setText("硬座");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(83);
		tableColumn_8.setText("硬卧");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(composite_2, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("订单界面");
		
		SashForm sashForm_1 = new SashForm(tabFolder, SWT.VERTICAL);
		tbtmNewItem.setControl(sashForm_1);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_3, SWT.NONE);
		group.setText("订单操作");
		
		Button button = new Button(group, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table_1.removeAll();//移除所有的值
				//刷新订单
				List<Map<String, Object>> list;
				try {
					list = dao.updatepiao();
		//更新完票数
//				System.out.println("更新的票数信息"+list);
				TableItem item1=null;
				String status="";
				for(Map<String, Object> map:list){
					if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==1){
						status="已支付";
					}
					if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==0){
						status="待付款";
					}
					if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==2){
						status="已退票";
					}
					if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==3){
						status="已改签";
					}
				item1=new TableItem(table_1, SWT.NONE);
				//根据编号来查出站台的信息
				//初始站
					Map<String, Object> map1=dao.Findbian2(Integer.parseInt(map.get("S_LOCLATION").toString()));//起点站
					Map<String, Object> map2=dao.Findbian2(Integer.parseInt(map.get("S_GETLOC").toString()));//终点站
					
					item1.setText(new String []{StringUtil.toObjectString(map.get("R_ID")),
							StringUtil.toObjectString(map.get("KID")),
							StringUtil.toObjectString(map.get("PNAME")),
							StringUtil.toObjectString(map.get("CALENDER")),
							StringUtil.toObjectString(map1.get("RAILWAY_STATION")),
							StringUtil.toObjectString(map2.get("RAILWAY_STATION")),
							StringUtil.toObjectString(map.get("TICKET_SPECIES")),
							StringUtil.toObjectString(map.get("SOFTSEATP")),
							StringUtil.toObjectString(map.get("CARRIAGE")),
							StringUtil.toObjectString(map.get("SEAT")),
							StringUtil.toObjectString(map.get("TICKET_PRICE")),
							status,
							StringUtil.toObjectString(map.get("CAID"))});
			}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button.setBounds(24, 36, 98, 30);
		button.setText("刷新订单列表");
		
		Button button_5 = new Button(group, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//继续支付就是要将所有未付款的都要来经行支付
				incha in=new incha();
				try {
					in.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.setBounds(156, 36, 98, 30);
		button_5.setText("继续支付");
		
		Button button_8 = new Button(group, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				TableItem t = table_1.getItem(table_1.getSelectionIndex());
				//这里时退票窗口只需要价格，状态   订单号
				pid = Integer.parseInt(t.getText(1));//订单号
//				System.out.println(pid);
				changeBegin = t.getText(4);//起始站
//				System.out.println(changeBegin);
				changeEnd = t.getText(5);
//				System.out.println(changeEnd);
				changeDateTime = t.getText(3);
//				System.out.println(changeDateTime);
				orgSeat = t.getText(7);
//				System.out.println(orgSeat);
				changeTrainNum = t.getText(0);
				orgPrice = t.getText(10);//价格再未进行运算之前都必须要用字符串来进行表示
				refundConfirm refund = new refundConfirm();
				refund.open();
			}
		});
		button_8.setBounds(277, 36, 98, 30);
		button_8.setText("退票");
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		//订单列表右键添加改签按钮执行
		table_1 = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table);
					table_1.setMenu(menu);
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText("改签");
					//改签最主要的就要将原来的价格和车次和起点站和终点站出发的日期座位的席别都要传到改签窗口去
					item.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem t = table_1.getItem(table_1.getSelectionIndex());
							//改签这里要获取到姓名
							
							pname=t.getText(2);//从表格中获取姓名
							ticketSpecies=t.getText(6);//票种
							tid=t.getText(0);//这里获取到车次
							changeBegin = t.getText(4);//起始站
							System.out.println(changeBegin);
							changeEnd = t.getText(5);//终点站
//							System.out.println(changeEnd);
							changeDateTime = t.getText(3);
//							System.out.println(changeDateTime);
							orgSeat = t.getText(7);//座位席别
							orgPrice =t.getText(10);//座位价格同样的座位加原来价格都要用字符串来进行表示
							pid = Integer.parseInt(t.getText(1));//订单编号
							caid=t.getText(12);//身份证号码
							newTicket newticket = new newTicket();
							newticket.open();
							
						}
					});
				}
			}
		});
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		System.out.println("看这里");
		System.out.println(userName);
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(71);
		tableColumn_9.setText("车次");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("订单号");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(63);
		tableColumn_11.setText("乘客");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(113);
		tableColumn_12.setText("发车时间");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(89);
		tableColumn_13.setText("出发地");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(87);
		tableColumn_14.setText("目的地");
		
		TableColumn tableColumn_15 = new TableColumn(table_1, SWT.NONE);
		tableColumn_15.setWidth(78);
		tableColumn_15.setText("票种");
		
		TableColumn tableColumn_16 = new TableColumn(table_1, SWT.NONE);
		tableColumn_16.setWidth(100);
		tableColumn_16.setText("席别");
		
		TableColumn tableColumn_20 = new TableColumn(table_1, SWT.NONE);
		tableColumn_20.setWidth(100);
		tableColumn_20.setText("车厢");
		
		TableColumn tableColumn_17 = new TableColumn(table_1, SWT.NONE);
		tableColumn_17.setText("座位");
		tableColumn_17.setWidth(92);
		
		TableColumn tableColumn_18 = new TableColumn(table_1, SWT.NONE);
		tableColumn_18.setWidth(71);
		tableColumn_18.setText("票价");
		
		TableColumn tableColumn_19 = new TableColumn(table_1, SWT.NONE);
		tableColumn_19.setWidth(48);
		tableColumn_19.setText("状态");
		
		TableColumn tableColumn_21 = new TableColumn(table_1, SWT.NONE);
		tableColumn_21.setWidth(100);
		tableColumn_21.setText("身份证");
		
		TableColumn tableColumn_22 = new TableColumn(table_1, SWT.NONE);
		tableColumn_22.setWidth(100);
		tableColumn_22.setText("下单时间");
		sashForm_1.setWeights(new int[] {91, 202});
		sashForm.setWeights(new int[] {85, 214, 429});
		//显示订单就是把所有的实时票数的
		//将获取到的值放入到实时票数更新表中
		List<Map<String, Object>> list=dao.updatepiao();//更新完票数
		System.out.println("更新的票数信息"+list);
		TableItem item1=null;
		String status="";
		for(Map<String, Object> map:list){
			if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==1){
				status="已支付";
			}
			if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==0){
				status="待付款";
			}
			if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==2){
				status="已退票";
			}
			if(Integer.parseInt(StringUtil.toObjectString(map.get("STATUS")))==3){
				status="改签票";
			}
		item1=new TableItem(table_1, SWT.NONE);
		//根据编号来查出站台的信息
		//初始站
			Map<String, Object> map1=dao.Findbian2(Integer.parseInt(map.get("S_LOCLATION").toString()));//起点站
			Map<String, Object> map2=dao.Findbian2(Integer.parseInt(map.get("S_GETLOC").toString()));//终点站
			
			item1.setText(new String []{StringUtil.toObjectString(map.get("R_ID")),
					StringUtil.toObjectString(map.get("KID")),
					StringUtil.toObjectString(map.get("PNAME")),
					StringUtil.toObjectString(map.get("CALENDER")),
					StringUtil.toObjectString(map1.get("RAILWAY_STATION")),
					StringUtil.toObjectString(map2.get("RAILWAY_STATION")),
					StringUtil.toObjectString(map.get("TICKET_SPECIES")),
					StringUtil.toObjectString(map.get("SOFTSEATP")),
					StringUtil.toObjectString(map.get("CARRIAGE")),
					StringUtil.toObjectString(map.get("SEAT")),
					StringUtil.toObjectString(map.get("TICKET_PRICE")),
					status,
					StringUtil.toObjectString(map.get("CAID")),
					StringUtil.toObjectString(map.get("ORDERUP"))
					});//获取到当前得时间，将当前得时间设置一个触发器
	}
		//到这里要将超过分钟的订单都要删掉
		//用当前的时间减去十五分钟之后就可以删除订单每次开始都要获取到当前的时间
		Date date2=new Date();
		//获取日历类
		List<Map<String, Object>> list01=rrle.getCalder();
		for(Map<String, Object> map06:list01){
			SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
			try {
				Date date3=format2.parse(map06.get("ORDERUP").toString());
				Calendar calder=Calendar.getInstance();//获取实例化对象
				calder.add(Calendar.MINUTE, -15);
				Date date5=calder.getTime();//和过去减去十五分钟的订单
				if(date5.getTime()>date3.getTime()){
					//获取到订单后再删除
					int kid=Integer.parseInt(map06.get("KID").toString());
					int sum=rrle.delete(kid);
					if(Integer.parseInt(map06.get("status").toString())==1 && sum>0){
					swtUtil.showMessage(shell, "温馨提示", "改订单编号"+kid+"乘车人"+map06.get("PNAME").toString()+"已经超过十五分钟删除");
					
					}
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	//起始站
	public static String getPname(){
		return pname;
	}
	public static String getBegin(){
		return changeBegin;
	}
	//终点站
	public static String getEnd(){
		return changeEnd;
	}
	//改签的日期
	public static String getDateTime(){
		return changeDateTime;
	}
	//员改签的席别
	public static String getOrgSeat(){
		return orgSeat;
	}
	
	public static int getPid(){ //订单编号  表中是kid
		return pid;
	}
	//改签号的车次
	public static String getChangeTrainNum(){
		return changeTrainNum;
	}
	//改签原来的价格
	public static String getOrgPrice(){
		return orgPrice;
	}
	//获取到改签后的新订单
	public static String getCaid(){
		return caid;
	}
	//获取到车次
	public static String getTid(){
		return tid;
	}
	//获取到原来从meun一直传过来的用户名
	public static String getUserName(){
		return userName;
	}
	//获取到票种
	public static String getTicketSpecies(){
		return ticketSpecies;
	}
}
