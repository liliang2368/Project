package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Cps_userLogin3 extends Composite {
	private Text text_userName;
	private Text text_pwd;
	private static String userName;
	private String pwd;
	private Button btn_rembPwd;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Cps_userLogin3(Composite parent, int style) {
		super(parent, style);
		
		UserInfoDao userinfodao = new UserInfoDao();
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 25, SWT.NORMAL));
		label.setBounds(289, 46, 208, 64);
		label.setText("用户登录界面");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(198, 191, 85, 21);
		label_1.setText("用户名：");
		
		text_userName = new Text(this, SWT.BORDER);
		text_userName.setBounds(289, 192, 208, 23);
		
		Label label_pwd = new Label(this, SWT.NONE);
		label_pwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_pwd.setBounds(198, 278, 61, 30);
		label_pwd.setText("密码：");
		
		text_pwd = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setBounds(289, 278, 208, 23);
		
		//登录
		Button btn_login = new Button(this, SWT.NONE);
		btn_login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				swtUtil sUtil = new swtUtil();
				//获取邮箱和密码
				userName = text_userName.getText().trim();
				pwd = text_pwd.getText().trim();
				String realPwd = "";
				
				try {
					Map<String , Object> map = userinfodao.getPwd(userName);
					realPwd = map.get("PWD").toString();
					if(pwd.equals(realPwd)){
						//登陆成功
						sUtil.showMessage(getShell(), "提示", "登录成功");
						MeunUi.setBtn_Login();
						MeunUi.setUserName(userName);
						MeunUi.setFlag(true);
						MeunUi.setBtn_Login();
						MeunUi.setBtn_Wel();
					}else{
						//登录失败
						sUtil.showMessage(getShell(), "错误提示", "用户名或密码错误");
						return;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_login.setBounds(354, 446, 80, 27);
		btn_login.setText("登录");
		
		btn_rembPwd = new Button(this, SWT.CHECK);
		btn_rembPwd.setBounds(267, 365, 69, 17);
		btn_rembPwd.setText("记住密码");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(436, 365, 76, 20);
		label_2.setText("忘记密码");

	}
	public static String getuserName(){
		return  userName;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
