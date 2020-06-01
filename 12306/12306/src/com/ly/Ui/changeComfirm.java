package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import com.ibm.icu.impl.duration.TimeUnitConstants;
import com.ly.dao.PassengerDao;
import com.ly.dao.UserInfoDao;
import com.ly.dao.buyTicketsDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.dao.traininfoDao;
import com.ly.util.StringUtil;
import com.ly.util.setSeatNum;
import com.ly.util.swtUtil;
import com.ly.util.zhuanhuantime;

import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class changeComfirm {

	protected Shell shell;
	public static String userName = newTicket.getUserName();		//获取登陆用户的用户名，方便对余额进行处理
	public static String begin = newTicket.getBegin();				//获取出发地
	public static String end = newTicket.getEnd();					//获取到达地
	public static String trainNum = newTicket.getTrainNum();		//获取车次
	public static int kid = newTicket.getKid();						//获取原订单的订单号
	//这里获取乘客的身份证号码
	public static String passengerId = Inquire.caid;		//获取乘客身份证号码，方便建立新订单
	public static String date= Inquire.getDateTime();				//获取出发日期
	public static String orgPrice = Inquire.getOrgPrice();		//获取原来票价
	public static String orgSeatRank = Inquire.getOrgSeat();		//获取原座位席别，方便计算差价
	public static String newSeatRank ;								//用来设置新座位的席别
	private Label label_price ;
	private Inquire inqu=new Inquire();
	private traininfoDao dao=new traininfoDao();
	private Combo combo;
	private rraletime rale = new rraletime();//实时票数更新表
	swtUtil sul = new swtUtil();
	railwayDao railwaydao = new railwayDao();
	buyTicketsDao buyticketsdao = new buyTicketsDao();
	PassengerDao passengerdao = new PassengerDao();
	UserInfoDao userinfo = new UserInfoDao();
	private Label label_orgprice;
	private String prices;//价格
	private setSeatNum seat=new setSeatNum();
	private Combo combo_1;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			changeComfirm window = new changeComfirm();
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
		shell.setSize(450, 300);
		shell.setText("确认窗口");
		
		Label label_begin = new Label(shell, SWT.NONE);
		label_begin.setBounds(114, 38, 44, 17);
		label_begin.setText(newTicket.getBegin());//获取到改签的初始站
		label_begin.setText("出发地");
		
		Label label_end = new Label(shell, SWT.NONE);
		label_end.setBounds(265, 38, 44, 17);
		label_end.setText(newTicket.getEnd());//获取到改签的终点站
		label_end.setText("到达地");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(196, 38, 21, 17);
		label.setText("→");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(114, 61, 87, 17);
		label_1.setText("改签后时间：");
		
		Label label_time = new Label(shell, SWT.NONE);
		swtUtil.showMessage(shell, "温馨提示", "这里的日期为"+newTicket.getcalder());
		label_time.setText(newTicket.getcalder());//获取改签后的出发日期
		label_time.setBounds(222, 61, 87, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(177, 10, 61, 17);
		label_2.setText("改签窗口");
		Button btn_comfirm = new Button(shell, SWT.NONE);
		btn_comfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//改签
				Map<String, Object> map=new HashMap<String, Object>();
				//根据站点转换成编号
				
				//将所有的信息插入实时票数更新表
				map.put("PNAME", Inquire.getPname())	;//乘客姓名		
				map.put("R_ID",newTicket.getTrainNum() );//改签后的车次列车编号
				try {
					map.put("S_LOCLATION", railwaydao.transToNum(Inquire.getBegin()));
					map.put("S_GETLOC", railwaydao.transToNum(Inquire.getEnd()));//终点站编号
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//初始站编号
				map.put("CALENDER", newTicket.getcalder());//获取到日期
				map.put("SOFTSEATP", combo_1.getText());//席别
				map.put("TICKET_PRICE",prices);//票价
				map.put("TICKET_SPECIES", Inquire.getTicketSpecies());//票种
				map.put("CARRIAGE", seat.getCar());//车厢
				map.put("CAID",Inquire.getCaid());//身份证
				map.put("SEAT", seat.getSeat());//座位
		//		map.put("STATUS", 3);//改签票
				//将新票改签，还要将原票要删除
				try {
					int result=buyticketsdao.buyTickets(map);//这里已经插入成功是待支付状态
					if(result>0){
						//将原订单删除掉再将新的票来经行改签
					
						int sum=rale.delete(Inquire.getPid());
						if(sum>0){
							//再修改原订单也已经删除
							Map<String, Object> map1=rale.findLatest();//获取到最大的订单 
							//修改成改签状态
							rale.changeDate(Integer.parseInt(StringUtil.toObjectString(map1.get("SS"))));
							System.out.println("改签成功");
						swtUtil.showMessage(shell, "温馨提示", "改签成功");
						}
						
						

					}else{
						swtUtil.showMessage(shell, "错误提示", "改签失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btn_comfirm.setBounds(145, 204, 80, 27);
		btn_comfirm.setText("确认改签");
		
		label_begin.setText(begin);
		label_end.setText(end);
		label_time.setText(date);
		Label label_tip = new Label(shell, SWT.NONE);
		label_tip.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip.setBounds(222, 237, 200, 16);
		label_tip.setText("提示：座位的差价多退少补");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(114, 129, 76, 20);
		label_3.setText("改签席别");
		
		 combo_1 = new Combo(shell, SWT.NONE);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("请看这里，这里进入了监听改签的座位席别");
				//当选择席别之后就要来确定票价了
				try {
					Map<String, Object> map=railwaydao.FindPrice(trainNum, begin, end);
	
					if(combo_1.getText().equals("软卧")){
						label_orgprice.setText(StringUtil.toObjectString(map.get("软卧")));
						prices=StringUtil.toObjectString(map.get("软卧"));
	
						}
					if(combo_1.getText().equals("软座")){
						label_orgprice.setText(StringUtil.toObjectString(map.get("软座")));
						prices=StringUtil.toObjectString(map.get("软座"));
		
					}
					if(combo_1.getText().equals("硬座")){
						label_orgprice.setText(StringUtil.toObjectString(map.get("硬座")));
						prices=StringUtil.toObjectString(map.get("硬座"));
			
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
				
			}
		});
		combo_1.add("软卧");
		combo_1.add("软座");
		combo_1.add("硬座");
		combo_1.select(0);
		combo_1.setBounds(222, 126, 92, 27);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(122, 155, 36, 17);
		label_4.setText("原价");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("现价");
		label_5.setBounds(238, 155, 36, 17);
		
		Label label_org = new Label(shell, SWT.NONE);
		label_org.setText(orgPrice);//获取原价
		label_org.setBounds(100, 178, 76, 20);
		
		label_orgprice = new Label(shell, SWT.NONE);
		label_orgprice.setBounds(222, 178, 76, 20);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("改签后车次：");
		label_6.setBounds(114, 91, 87, 17);
		
		Label label_checi = new Label(shell, SWT.NONE);
		label_checi.setText(newTicket.getTrainNum());//获取到改签窗口选中的车次
	//	label_checi.setText((String) null);
		label_checi.setBounds(222, 91, 87, 17);
		


	}
}
