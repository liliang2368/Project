package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ibm.icu.text.SimpleDateFormat;
import com.ly.dao.adminDao;
import com.ly.util.DataDis;
import com.ly.util.Time;
import com.ly.util.swtUtil;

import org.eclipse.swt.layout.FillLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MeunUi {

	protected Shell shell;
	private static Composite composite_4;
	private adminDao addao=new adminDao();
	public static String mail;
	public static String userName;
	public static boolean isLog = false;
	private static  Label btn_userLogin;
	public static Label btn_wel;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MeunUi window = new MeunUi();
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
		shell.setImage(SWTResourceManager.getImage(MeunUi.class, "/image/timg.jpg"));
		shell.setSize(1260, 1045);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/33.jpg"));
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		//左上角按钮，若未登录进入登录界面，若已登录，进入信息查询界面
		btn_wel = new Label(composite_5, SWT.BORDER);
		btn_wel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		btn_wel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(isLog == false){
					Cps_userLogin ad;
					try {
						ad = new Cps_userLogin(composite_4, SWT.NONE);
						DataDis.stackLayout.topControl=ad;
						composite_4.layout();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else if(isLog == true){
					UserInfoUi ad=new UserInfoUi();
					try {
						ad.open();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		btn_wel.setText("\u6B22\u8FCE\u60A8\uFF0C\u6E38\u5BA2");
		
		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		
		Label label = new Label(composite_6, SWT.BORDER);
		label.setBounds(51, 1, 117, 46);
		
		Label button_2 = new Label(composite_6, SWT.BORDER);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DataDis.stackLayout.topControl = DataDis.homepage;
				composite_4.layout();
			}
		});
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_2.setText("\u9996\u9875");
		button_2.setBounds(169, 0, 45, 47);
		
		Label button_1 = new Label(composite_6, SWT.BORDER);
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//用户注册
				Cps_register ad;
				try {
					ad = new Cps_register(composite_4, SWT.NONE);
					DataDis.stackLayout.topControl=ad;
					composite_4.layout();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
			}
		});
		button_1.setText("\u6CE8\u518C");
		button_1.setBounds(0, 1, 50, 46);
		
		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_3 = new Label(composite_7, SWT.BORDER);
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		Time.Time2(button_3);//时间的异步，计时器
		Composite composite_8 = new Composite(sashForm_2, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_4 = new Label(composite_8, SWT.BORDER);
		button_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_4.setText("\u8D27\u8FD0\u670D\u52A1");
		
		Composite composite_9 = new Composite(sashForm_2, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_5 = new Label(composite_9, SWT.BORDER);
		button_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_5.setText("\u884C\u5305\u670D\u52A1");
		
		Composite composite_10 = new Composite(sashForm_2, SWT.NONE);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_6 = new Label(composite_10, SWT.NONE);
		button_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_6.setText("\u7AD9\u8F66\u98CE\u91C7");
		
		Composite composite_11 = new Composite(sashForm_2, SWT.NONE);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_7 = new Label(composite_11, SWT.NONE);
		button_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_7.setText("\u94C1\u8DEF\u5E38\u8BC6");
		
		Composite composite_12 = new Composite(sashForm_2, SWT.NONE);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_8 = new Label(composite_12, SWT.NONE);
		button_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_8.setText("\u8F66\u7AD9\u5F15\u5BFC");
		
		Composite composite_13 = new Composite(sashForm_2, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label button_9 = new Label(composite_13, SWT.NONE);
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			Cps_sendMess ad;
				try {
					ad = new Cps_sendMess(composite_4, SWT.NONE);
					DataDis.stackLayout.topControl=ad;
					composite_4.layout();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		button_9.setText("\u5BA2\u670D\u4FE1\u7BB1");
		sashForm_2.setWeights(new int[] {137, 215, 119, 120, 119, 124, 119, 127, 130});
		
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.BORDER);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_3, SWT.VERTICAL);
		
		Composite composite_14 = new Composite(sashForm_3, SWT.NONE);
		
		//用户登录
		btn_userLogin = new Label(composite_14, SWT.BORDER);
		btn_userLogin.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/38.jpg"));
		btn_userLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(isLog == false){
					Cps_userLogin ad;
					try {
						ad = new Cps_userLogin(composite_4, SWT.NONE);
						DataDis.stackLayout.topControl=ad;
						composite_4.layout();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				//已登录则实现注销功能
				else if(isLog == true){
					userName = "";
					isLog = false;
					btn_userLogin.setText("");
					btn_userLogin.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/38.jpg"));
					btn_wel.setText("欢迎您，游客");
				}
			}
		});
		btn_userLogin.setBounds(5, 8, 192, 49);
		
		Label btn_register = new Label(composite_14, SWT.BORDER);
		btn_register.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/26.jpg"));

		btn_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Cps_register ad;
				try {
					ad = new Cps_register(composite_4, SWT.NONE);
					DataDis.stackLayout.topControl=ad;
					composite_4.layout();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btn_register.setBounds(4, 63, 193, 49);
		
		//购票按钮
		Label button_buyTicket = new Label(composite_14, SWT.BORDER);
		button_buyTicket.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/36.jpg"));
		button_buyTicket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//若未登录则弹出提示
				if(isLog == false){
					notLog();
					return;
				}
				//若已登录继续操作
				Inquire inquire = new Inquire();
				try {
					inquire.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_buyTicket.setBounds(4, 118, 193, 44);
		
		//退票按钮
		Label button_12 = new Label(composite_14, SWT.BORDER);
		button_12.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/28.jpg"));
		button_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//若未登录则弹出提示
				if(isLog == false){
					notLog();
					return;
				}
				//若已登录继续操作
			}
		});
		button_12.setBounds(4, 173, 193, 44);
		
		Label button_13 = new Label(composite_14, SWT.BORDER);
		button_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//票价查询
				
				
				
				
				
				
				
			}
		});
		button_13.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/31.jpg"));
		button_13.setBounds(4, 228, 193, 44);
		
		Label button_14 = new Label(composite_14, SWT.BORDER);
		button_14.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/37.jpg"));
		button_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				cps_dailyInquire1 cha=new cps_dailyInquire1(composite_4, SWT.NONE);
				DataDis.stackLayout.topControl=cha;
				composite_4.layout();
			}
		});
		button_14.setBounds(5, 283, 192, 49);
		
		//点击进入列车时刻表
		Label btn_schedule = new Label(composite_14, SWT.BORDER);
		btn_schedule.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/29.jpg"));
		btn_schedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(isLog == false){
					 notLog();
					 return;
				}
				DataDis.stackLayout.topControl = DataDis.timetable;
				composite_4.layout();
			}
		});
		btn_schedule.setBounds(4, 338, 193, 49);
		
		//点击查看个人信息
		Label button_ckInfo = new Label(composite_14, SWT.BORDER);
		button_ckInfo.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/1.png"));
		button_ckInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(isLog == false){
					notLog();
					return;
				}
				System.out.println("hello");
				UserInfoUi ad=new UserInfoUi();
				try {
					ad.open();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_ckInfo.setBounds(4, 393, 193, 49);
		
		Label button_17 = new Label(composite_14, SWT.BORDER);
		button_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DataDis.stackLayout.topControl=DataDis.site;
				composite_4.layout();
			}
		});
		button_17.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/35.jpg"));
		button_17.setBounds(4, 448, 193, 49);
		
		Label button_adminLog = new Label(composite_14, SWT.BORDER);
		button_adminLog.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/34.jpg"));
		button_adminLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Admin ad=new Admin(composite_4, SWT.NONE);
				DataDis.stackLayout.topControl=ad;
				composite_4.layout();
			}
		});
		button_adminLog.setBounds(4, 503, 193, 44);
		
		Composite composite_15 = new Composite(sashForm_3, SWT.NONE);
		composite_15.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/17 .jpg"));

		sashForm_3.setWeights(new int[] {549, 219});
		
		 composite_4 = new Composite(sashForm_1, SWT.NONE);
		
		DataDis.site=new Site(composite_4, SWT.NONE);
		DataDis.timetable = new TimeTable(composite_4, SWT.NONE);
		composite_4.setLayout(DataDis.stackLayout);
		sashForm_1.setWeights(new int[] {200, 1027});
		sashForm.setWeights(new int[] {132, 67, 735});
		
//		HomePage homepage=new HomePage(composite_4, SWT.NONE);
//		DataDis.stackLayout.topControl=homepage;
//		composite_4.layout();

	}

	public static String getUserName(){
		return userName;
	}
	public static void setUserName(String username){
		userName = username;
	}
	public static void setBtn_Login(){
		btn_userLogin.setText("");
		btn_userLogin.setBackgroundImage(SWTResourceManager.getImage(MeunUi.class, "/image/2.png"));
	}
	public static void setFlag(boolean flag){
		isLog = flag;
	}
	public  void notLog(){
		swtUtil sUtil = new swtUtil();
		sUtil.showMessage(shell, "温馨提示", "请先登录");
		return;
	}
	
	//若已登录，将用户名显示在左上角的按钮上
	public static void setBtn_Wel(){
		btn_wel.setText("欢迎您，" + Cps_userLogin.getuserName());
	}
	public static void setbackground(){
		
	}

}
