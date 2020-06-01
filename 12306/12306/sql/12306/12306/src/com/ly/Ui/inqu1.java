package com.ly.Ui;
/**
 * 这里暂时还缺少了用户名和用户价格
 */
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.dao.rraletime;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class inqu1 extends Cps_buyTickets{

	protected Shell shell;
	private Text text;
	private Text txtNull;
	private Text txtNull_1;
	private Text text_1;
	private UserInfoDao use=new UserInfoDao();
	private rraletime rr=new rraletime();//实时票数更新表

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			inqu1 window = new inqu1();
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
		shell.setSize(653, 410);
		shell.setText("确认购票");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Cps_buyTickets cps=new Cps_buyTickets();
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(composite, SWT.BORDER);
		text.setText("占座成功，车厢号为:"+carrige+"\t座位号为"+seatof+"请及时支付您的车票");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(86, 60, 76, 20);
		label.setText("车厢号");
		
		txtNull = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		txtNull.setBounds(183, 60, 73, 26);
		txtNull.setText(carrige);//车厢
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(86, 124, 63, 20);
		label_1.setText("车厢");
		
		txtNull_1 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		txtNull_1.setBounds(183, 121, 73, 26);
		txtNull_1.setText(seatof);//座位号
		
		Button button = new Button(composite_1, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//购票
				//就是相当于要支付票价了
				//先要获取到用户名
				System.out.println("用户名"+MeunUi.userName);
				//这里要获取余额，根据用户名来获取余额
				try {
					Map<String, Object> map=use.getBal(MeunUi.userName);//获取到余额
					Double prices1=Double.parseDouble(StringUtil.toObjectString(map.get("BALANCE")));//获取到用户的余额
					if(price<prices1){
				int result=	use.payTicket(MeunUi.userName, price);
				if(result>0){
					swtUtil.showMessage(shell, "温馨提示", "买票成功");
					//买票成功就可以把这个订单号的状态换成1就是已支付
					//先查询出最大打订单编号，集是我刚刚插入的值
					Map<String, Object> map02=rr.findLatest();//查询出最大的订单号
					//可以根据订单号修改状态
					int sum=rr.afterPay(Integer.parseInt(map02.get("SS").toString()));
					if(sum>0){
					//购买成功
					swtUtil.showMessage(shell, "温馨提示", "购买成功");
					}else{
						swtUtil.showMessage(shell, "错误提示", "购票失败");
					}
				}
					}else{
						swtUtil.showMessage(shell, "温馨提示", "余额不足,支付失败");
						//弹出充值窗口
					}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		button.setBounds(355, 83, 98, 30);
		button.setText("支付");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(355, 46, 185, 20);
		label_2.setText("请在15分钟之后提交订单");
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(93, 190, 56, 20);
		label_3.setText("票价");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setText("价格"+price);
		text_1.setBounds(183, 190, 73, 26);
		sashForm.setWeights(new int[] {1, 3});

	}

}
