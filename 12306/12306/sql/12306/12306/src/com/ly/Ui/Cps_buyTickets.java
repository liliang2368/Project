package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;

import com.ibm.icu.text.SimpleDateFormat;
import com.ly.Ui.Inquire;
import com.ly.dao.PassengerDao;
import com.ly.dao.buyTicketsDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.dao.traininfoDao;
import com.ly.util.RanadomSeat;
import com.ly.util.setSeatNum;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
public class Cps_buyTickets extends Inquire{

	protected Shell shell;
	private String seatRank;
	private traininfoDao dao=new traininfoDao();
	private Inquire inqu=new Inquire();
	private Label label_price; 
	private String passengerId = "身份证号";
	private Combo combo_1;
	private railwayDao wayDao=new railwayDao();
	private Label label_price1;
	private String passerage;
	protected static double price;//票价
	private Combo combo_2 ;//这里是票种
	private setSeatNum seat=new setSeatNum();
	private rraletime rrrale=new rraletime();
	public static String carrige;//车厢
	public static String seatof;//座位
	public static String caid;
	public static String userName = Inquire.getUserName();//获取到用户名
	int tid;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Cps_buyTickets window = new Cps_buyTickets();
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
		shell.setSize(729, 493);
		shell.setText("点击确认购买");
		carrige=seat.getCar();//这里要获取车厢号
		seatof=seat.getSeat();//这里要获取座位号
		Label label_begin = new Label(shell, SWT.NONE);
		label_begin.setBounds(181, 39, 61, 17);
		
		Label label_end = new Label(shell, SWT.NONE);
		label_end.setBounds(461, 39, 61, 17);
		
		label_begin.setText(begin);
		label_end.setText(end);
		
		System.out.println("看这里");
		System.out.println(userName);
		
		Label label_trainNum = new Label(shell, SWT.CENTER);
		label_trainNum.setBounds(289, 10, 61, 17);
		label_trainNum.setText(trainNum);
		
		Label label_time = new Label(shell, SWT.NONE);
		label_time.setText(inqu.bytime);//历时
		label_time.setBounds(302, 103, 48, 17);
		
		Label label_passenger = new Label(shell, SWT.NONE);
		label_passenger.setBounds(194, 129, 48, 17);
		label_passenger.setText("乘车人");
//		System.out.println("请看这里"+trainNum);
		//到前面来获取到价格
		Map<String, Object> map01=wayDao.FindPrice(trainNum, begin, end);
//		System.out.println(map01);
		//点击获取乘车人身份证号码
		Combo combo = new Combo(shell, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				passengerId = combo.getText().substring(0, 18);
//				System.out.println(passengerId);
			}
		});
		PassengerDao passengerdao = new PassengerDao();
		try {
			List<Map<String, Object>> list =passengerdao.findPassenger();
//			System.out.println(list);
			if(null != list && list.size()>0){
				for(Map<String,Object> map:list){
					combo.add(map.get("CARID").toString() + "-" + map.get("PNAME").toString());
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		combo.setBounds(260, 126, 188, 25);

		//点击按钮完成购票确认
		Button btn_buyTickets = new Button(shell, SWT.NONE);
		btn_buyTickets.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//这里要进行字符串的分割
				
				String []arr=combo.getText().split("-");
				caid=arr[0];
				passerage=arr[1];
				System.out.println("这里分割成的身份证号码为"+caid);
				System.out.println("这里分割成的乘客姓名为"+passerage);
				swtUtil sul = new swtUtil();
				railwayDao railwaydao = new railwayDao();
				buyTicketsDao buyticketsdao = new buyTicketsDao();
				//判断座位的选择
				if(Integer.parseInt(label_price.getText()) <=0 ){
					sul.showMessage(shell, "错误提示", "票数不足，无法购买");
					return;
				}
				//根据站名获取站台对应的编号
				 try {
					Map<String, Object> map = railwaydao.fandBian(begin, end);
					int launch = Integer.parseInt(map.get("起点站").toString());
					int finalPoint = Integer.parseInt(map.get("终点站").toString());
					Map<String, Object> map04=new HashMap<String, Object>();
					map04.put("PNAME", passerage);//乘客
					map04.put("R_ID", trainNum);//列车编号
					map04.put("S_LOCLATION", launch);//列车出发站的编号
					map04.put("S_GETLOC", finalPoint);//列车到达站
					map04.put("CALENDER", time2);//出发的日期
					map04.put("SOFTSEATP", combo_1.getText());//座位的席
					map04.put("TICKET_PRICE", price);//票价
					map04.put("TICKET_SPECIES", combo_2.getText());//票种
				
					map04.put("CARRIAGE",carrige );//获取车厢
					//这里要插入身份证号码
					map04.put("CAID", caid);
					//这里要获取当前得时间
					Date date =new Date();
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
					//获取当前得时间的话就没有必要对事件来进行解析
					map04.put("ORDERUP", format.format(date));//插入下单的日期和时间，将状态便为待支付状态
					map04.put("SEAT",seatof);//获取到座位
					map04.put("STATUS", "0");//改为代支付状态
//					System.out.println( passerage+"\t"+trainNum+"\t编号"+launch+"\t到达站"+finalPoint+"出发日期"+time2+"座位席别");
					//将乘客的身份证号转为乘客编号
					//购票
					int result = buyticketsdao.buyTickets(map04);
					if(map04.size()>0){
						//这里如果插入成功的话就要设定一个触发器，
						//用的是日期类
						//购票成功的话要获取到最大的订单
						Map<String, Object> map05=rrrale.findLatest();//获取到最大的订单
						String pid=map05.get("SS").toString();
						tid=Integer.parseInt(pid);
						Calendar calendar=Calendar.getInstance();//获取实例化对象
								//指定触发的时间
						calendar.set(Calendar.DAY_OF_MONTH, date.getYear());//互殴去年份对象
						calendar.set(Calendar.MONTH, date.getMonth());//获取月份对象
						calendar.set(Calendar.HOUR_OF_DAY, date.getDay());//获取某一天
						calendar.set(Calendar.MINUTE, date.getMinutes()+15);//十五分钟触发
						calendar.set(Calendar.SECOND, 1);//设置成第一秒的时候触发
						
						Date time=calendar.getTime();//获取时间
						Timer timer=new Timer();
						timer.schedule(new RemindTask(), time);//这里是十五分钟触发
						inqu1 in=new inqu1();
						in.open();
					
					}else{
						swtUtil.showMessage(shell, "错误提示", "购票失败");
					}
//					System.out.println(result);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_buyTickets.setBounds(276, 374, 80, 27);
		btn_buyTickets.setText("确认购票");
		
		Label labelstart = new Label(shell, SWT.CENTER);
		labelstart.setText(inqu.arriveTime);
		labelstart.setBounds(152, 103, 76, 20);
		
		Label labelend = new Label(shell, SWT.CENTER);;
		labelend.setText(arriveTime);//到达时间
		labelend.setBounds(413, 98, 61, 20);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(276, 229, 76, 20);
		label.setText("选择席别");
		
		combo_1 = new Combo(shell, SWT.NONE);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(combo_1.getText());
				if(combo_1.getText().equals("软座")){
					label_price.setText(inqu.hardSeat);
					label_price1.setText(map01.get("软座").toString());
					price=getprice(map01.get("软座").toString());
				}
				if(combo_1.getText().equals("硬座")){
					label_price.setText(inqu.hardSeat);//硬座的票价
					label_price1.setText(map01.get("硬座").toString());
					price=getprice(map01.get("硬座").toString());
				}
				if(combo_1.getText().equals("软卧")){
					label_price.setText(inqu.softSleep);
					label_price1.setText((map01.get("软卧").toString()));
					price=getprice(map01.get("软卧").toString());
				}
			}
		});
		combo_1.select(0);
		//这里要连接到数据库，因为不同的车次设置的席别是不同的
//		System.out.println("到购票界面选择的车次"+inqu.trainNum);
		List<Map<String, Object>> list=dao.FindByTid("G1001");
//		System.out.println(list);
		for(Map<String, Object> map:list){
			combo_1.add(map.get("SOFTSEATP").toString());
		}
		combo_1.setBounds(276, 264, 92, 28);
		
		Label btnNewButton = new Label(shell, SWT.CENTER);
		btnNewButton.setText("⇌");
		btnNewButton.setBounds(263, 35, 162, 28);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(177, 76, 76, 20);
		label_1.setText("出发时间");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(417, 76, 76, 20);
		label_2.setText("到达时间");
		
		Label label_3 = new Label(shell, SWT.CENTER);
		label_3.setBounds(394, 239, 76, 20);
		label_3.setText("票数");
		
		 label_price = new Label(shell, SWT.NONE);
		label_price.setBounds(394, 267, 76, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(181, 239, 61, 27);
		label_4.setText("票价");
		
		 label_price1 = new Label(shell, SWT.NONE);
		label_price1.setBounds(177, 267, 61, 17);
		
		Label label_5 = new Label(shell, SWT.CENTER);
		label_5.setBounds(289, 77, 76, 20);
		label_5.setText("历时");
		
	 combo_2 = new Combo(shell, SWT.NONE);
		combo_2.add("学生票");
		combo_2.add("成人票");
		combo_2.add("儿童票");
		combo_2.setBounds(276, 195, 92, 28);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("选择票种");
		label_6.setBounds(280, 160, 76, 20);
		System.out.println("我选择的车厢信息为"+carrige+seatof);
		
		
	}
	//将价格传进来,传进来之后对选择的票种来进行计算
	public double getprice(String price){
		int pd=Integer.parseInt(price);//将价格转便成整型
		if(combo_2.getText().equals("学生票")){
			return pd*0.75;
		}
		if(combo_2.getText().equals("儿童票")){
			return pd*0.5;
		}
		return pd;
	}

class RemindTask extends TimerTask {
    
    public void run() {
       //这里面来实现触发
    	System.out.println("改时间已经触发");
    	//将原来的订单删除
    	try {
			int result=rrrale.delete(tid);//删除原订单
			if(result>0){
				swtUtil.showMessage(shell, "温馨提示", "您的订单已取消");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
} 
	
	
	
}
