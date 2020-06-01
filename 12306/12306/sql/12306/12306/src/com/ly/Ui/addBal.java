package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class addBal {

	protected Shell shell;
	//获取用户名用于数据库操作条件
	private static String userName = Cps_userInfo.getUserName();
	private Text text_add;
	private static final Pattern PATTERN = Pattern.compile("0|([-]?[1-9][0-9]*)");

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			addBal window = new addBal();
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
		shell.setText("充值界面");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(72, 71, 114, 17);
		label.setText("输入您要充值的金额");
		
		text_add = new Text(shell, SWT.BORDER);
		text_add.setBounds(202, 68, 95, 23);
		
		//点击完成充值
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sal = text_add.getText().trim();
				if( !isInt(sal) ){
					swtUtil sUtil = new swtUtil();
					sUtil.showMessage(shell, "错误提示", "请输入想充值的正整数");
					return;
				}
				int add = Integer.parseInt(sal);
				UserInfoDao userinfodao = new UserInfoDao();
				try {
					int result = userinfodao.addSal(userName, add);
					if(result == 1){
						
						swtUtil.showMessage(shell, "成功", "充值成功");
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(159, 171, 80, 27);
		button.setText("点击完成充值");

	}
	private static boolean isInt(String str) {
        return PATTERN.matcher(str).matches();
    }
}
