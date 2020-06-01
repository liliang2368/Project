package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.dao.rraletime;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class refundConfirm {

	protected Shell shell;
	
	private static String begin = Inquire.getBegin();
	private static String end = Inquire.getEnd();
	private static String trainNum = Inquire.getChangeTrainNum();
	private static String seatRank = Inquire.getOrgSeat();
	private static int pid = Inquire.getPid();
	private static String time = Inquire.getDateTime();
	private static String price = Inquire.getOrgPrice();
	private static String userName = Inquire.getUserName();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			refundConfirm window = new refundConfirm();
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
		label_begin.setBounds(98, 29, 61, 17);
		label_begin.setText("起始站");
		
		Label label_end = new Label(shell, SWT.NONE);
		label_end.setBounds(249, 29, 61, 17);
		label_end.setText("到达站");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel.setBounds(192, 22, 25, 25);
		lblNewLabel.setText("→");
		
		Label label_trainNum = new Label(shell, SWT.NONE);
		label_trainNum.setBounds(178, 68, 53, 17);
		label_trainNum.setText("车次");
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(140, 187, 144, 17);
		label.setText("点击按钮进行确认退票  ↓");
		
		Label label_time = new Label(shell, SWT.NONE);
		label_time.setBounds(170, 110, 61, 17);
		label_time.setText("时间");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(170, 137, 61, 17);
		label_1.setText("席别");
		
		//确认退票
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rraletime rale = new rraletime();
				UserInfoDao userinfo = new UserInfoDao();
				//修改订单状态为已退票
				//System.out.println(userName);
				System.out.println("请看这里，这里时原订单");
				System.out.println(Inquire.getPid());
				//System.out.println(price);
				try {
					int result = rale.refund(Inquire.getPid());
					//修改当前定单状态变成已退票
					int result2 = userinfo.addSal(userName, Integer.parseInt(price));	//在进行运算时要将价格加在
					//主要时获取到改订单的价格
					//从inquire获取到订单的全部信息
					if(result ==1 || result2 == 1){
						swtUtil.showMessage(shell, "温馨提示", "退票成功");
						shell.close();
						return;
					}else{
						swtUtil.showMessage(shell, "错误提示", "退票失败");
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(178, 224, 80, 27);
		button.setText("确认退票");
		
		label_begin.setText(begin);
		label_end.setText(end);
		label_1.setText(seatRank);
		label_trainNum.setText(trainNum);
		label_time.setText(time);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(98, 6, 53, 17);
		label_2.setText("订单号：");
		
		Label label_pid = new Label(shell, SWT.NONE);
		label_pid.setBounds(170, 6, 61, 17);
		label_pid.setText("订单号");
		label_pid.setText(Integer.toString(pid));
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(170, 164, 25, 17);
		label_3.setText("票价");
		
		Label label_price = new Label(shell, SWT.NONE);
		label_price.setBounds(209, 164, 61, 17);
		label_price.setText("票价");
		
		label_price.setText((price));
	}


}
