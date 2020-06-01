package com.ly.Ui;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ly.dao.StationInfoDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;
import com.ly.util.zhuanhuantime;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class newTicket {

	protected Shell shell;
	private Table table;
	public static String begin = Inquire.getBegin();//起始站
	public static String end = Inquire.getEnd();//终点站
//	public static String changeDateTime = Inquire.getDateTime();//列车出发的时间
//	public static String orgSeat = Inquire.getOrgSeat();//列车出发的原价
	private railwayDao dao=new railwayDao();
	private String calder2;//列车原下订单的日期
	private rraletime time=new rraletime();
	private 	String k;//软卧数
	private 	String j;//软座数
	private 	String i;//硬座数
	private zhuanhuantime zhuan=new zhuanhuantime();
	private StationInfoDao info=new StationInfoDao();
	
	public static String trainNum ;//列车车次
	public static String launchTime = "出发时间";
	public static String arriveTime;//到达时间
	public static String bytime;//历时
	public static String sofeSaet;//软座
	public static String hardSeat;//硬座
	public static String softSleep;//软卧
	public static String userName = Inquire.getUserName();
	public static int kid = Inquire.getPid();//获取原订单
	private static String calder4;//获取日期
//	public static double orgPrice = Inquire.getOrgPrice();//获取原价格
	//还有一个重要的课题，就是要获取到现在的价格，现在的价格就是要根据选择的改签的车次和

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			newTicket window = new newTicket();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(623, 593);
		shell.setText("改签");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(220, 20, 110, 28);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(22, 28, 175, 20);
		label.setText("请选择需要改签的日期");
		
		Button button = new Button(composite, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//选择改签的日期
			 calder4=zhuan.zhuanCalder(dateTime);//转换改签的日期
			 swtUtil.showMessage(shell, "温馨提示", "您选择的日期为"+calder4);
				cc(calder4);//刷新改签车票
			}
		});
		button.setBounds(414, 20, 98, 30);
		button.setText("确认");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(60);
		tblclmnNewColumn.setText("车次");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(60);
		tblclmnNewColumn_1.setText("出发地");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(58);
		tblclmnNewColumn_2.setText("目的地");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(63);
		tblclmnNewColumn_3.setText("历时");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(85);
		tableColumn.setText("出发时间");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(95);
		tableColumn_1.setText("到达时间");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(70);
		tableColumn_2.setText("软座");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(64);
		tableColumn_3.setText("硬座");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(66);
		tableColumn_4.setText("软卧");
		sashForm.setWeights(new int[] {86, 457});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table);
					table.setMenu(menu);
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText("选择改签");
					item.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem t = table.getItem(table.getSelectionIndex());
							trainNum = t.getText();//获取改签的车次
//						//	System.out.println("您选择改签的车次为"+trainNum);
//							begin1 = t.getText(1);
//						//	System.out.println("您选择改签的车次初始站"+begin);
//							end1 = t.getText(2);
						//	System.out.println("您选择改签的车次终点站为 "+end);
							bytime=t.getText(3);//历时
						//	System.out.println("历时"+bytime);
							//出发时间
							arriveTime=t.getText(4);
						//	System.out.println("出发时间"+arriveTime);
							//到达时间
							launchTime = t.getText(5);
						//	System.out.println("到达时间"+launchTime);
							sofeSaet=t.getText(6);//软座
						//	System.out.println("软座"+sofeSaet);
							hardSeat=t.getText(7);
						//	System.out.println("硬座"+hardSeat);
							softSleep=t.getText(8);
							//System.out.println("软卧"+softSleep);
							//System.out.println("hello");
							
							changeComfirm confirm = new changeComfirm();
							confirm.open();
							
						}
					});
				}
			}
		});
		cc(calder2);//这个理刷新车次
	}
	public void cc(String calder3){
		table.removeAll();
		//查询出票数
		TableItem item=null;
		try {
			List<Map<String, Object>> list =dao.FindAllBy(begin, end);
//			System.out.println("****************************************");
//			System.out.println(list);//根据出发站和中点站已经查询出所有经过的车次
//			System.out.println("******************************************************");
			for(Map<String, Object> map:list){
			//System.out.println(calder2);
				k=time.really(begin, end, "软座", StringUtil.toObjectString(map.get("TID")),calder3);//实时票数统计
				j=time.really(begin, end, "硬座", StringUtil.toObjectString(map.get("TID")), calder3);
				i=time.really(begin, end, "软卧", StringUtil.toObjectString(map.get("TID")), calder3);
				System.out.println(map);//
				//这里获取到出发点到中点站的出发时间和到达时间
				Map<String, Object> list01=info.getTime(begin, end,StringUtil.toObjectString(map.get("TID")));
//				System.out.println("*********************************");
//				System.out.println(list01);
//				System.out.println("***********************************");
				String calder=zhuan.Time(StringUtil.toObjectString(list01.get("S_TIME2")), StringUtil.toObjectString(list01.get("S_TIME1")));
//				System.out.println("*********************************");
//				System.out.println(calder);
				//System.out.println("--------------------------------------------");
				item=new TableItem(table, SWT.NONE);
				item.setText(new String []{StringUtil.toObjectString(map.get("TID")),begin,end,calder,zhuan.formattime(StringUtil.toObjectString(list01.get("S_TIME2"))),
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
	public static String getUserName(){
		return userName;
	}
	public static int getKid(){
		return kid;
	}
	public static String getBegin(){
		return begin;
	}
	public static String getEnd(){
		return end;
	}
	public static String getTrainNum(){
		return trainNum;
	}
	public static String getcalder(){
		return calder4;
	}
}
