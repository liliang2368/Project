package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class ForgetPwd2 {

	protected Shell shell;
	private Text text_pwd;
	private Text text_pwd2;
	private UserInfoDao dao=new UserInfoDao();
	private ForgetPwd pwd2=new ForgetPwd();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ForgetPwd2 window = new ForgetPwd2();
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
		shell.setSize(608, 433);
		shell.setText("修改密码");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(88, 83, 90, 24);
		label.setText("新密码：");
		
		text_pwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setBounds(219, 83, 179, 30);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(88, 166, 90, 24);
		lblNewLabel.setText("确认密码：");
		
		text_pwd2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
//		text_pwd2.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				//判断两次输入的密码是否相等
//				if(text_pwd.getText()!=text_pwd2.getText()){
//					swtUtil.showMessage(shell, "错误提示", "两次输入的密码不相等");
//					
//				}else{
//					
//				}
//			}
//		});
		System.out.println("输入的密码为"+text_pwd.getText()+"\t"+text_pwd2.getText());
		text_pwd2.setBounds(219, 163, 179, 30);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(442, 83, 134, 24);
		label_1.setText("不少于两个字符");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(439, 166, 137, 24);
		label_2.setText("请再次输入密码");
		System.out.println("请看这里"+pwd2.username);
		Button button = new Button(shell, SWT.NONE);
		//修改密码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String pwd=text_pwd.getText().trim();
				try {
					int i=dao.changepwd(pwd,pwd2.username);
					if(i>0){
						swtUtil.showMessage(shell, "温馨提示", "修改密码成功");
						//跳转到主页面
						MeunUi meun=new MeunUi();
						meun.open();//打开主菜单
					}else{
						swtUtil.showMessage(shell, "错误提示", "修改密码失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(233, 263, 114, 34);
		button.setText("修改");

	}

}
