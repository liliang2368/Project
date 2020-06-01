package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.StringUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Cps_userInfo extends Composite {
	private Text text_userId;
	private Text text_userNam;
	private Text text_email;
	private Text text_balance;
	//这里需要通过其他途径来传入查询用的邮箱地址
	private static String userName = MeunUi.getUserName();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Cps_userInfo(Composite parent, int style) {
		super(parent, style);
		
		UserInfoDao userinfodao = new UserInfoDao();
		
		Label label_title = new Label(this, SWT.NONE);
		label_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 38, SWT.NORMAL));
		label_title.setBounds(386, 20, 323, 113);
		label_title.setText("用户信息");
		
		Label label_userId = new Label(this, SWT.NONE);
		label_userId.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_userId.setBounds(261, 136, 103, 29);
		label_userId.setText("用户账号：");
		
		text_userId = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		text_userId.setBounds(396, 139, 183, 35);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(261, 193, 89, 29);
		label.setText("用户名：");
		
		text_userNam = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		text_userNam.setBounds(397, 194, 182, 35);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(261, 248, 77, 29);
		label_1.setText("邮箱：");
		
		text_email = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		text_email.setBounds(396, 248, 183, 35);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(261, 283, 77, 36);
		lblNewLabel.setText("余额");
		
		text_balance = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		text_balance.setBounds(396, 302, 147, 35);
		
		try {
			Map<String,Object> map = userinfodao.InquInfo(userName);
			text_email.setText(StringUtil.toObjectString(map.get("EMAIL")));
			text_balance.setText(StringUtil.toObjectString(map.get("BALANCE")));
			text_userNam.setText(StringUtil.toObjectString(map.get("UNAME").toString()));
			text_userId.setText(StringUtil.toObjectString(map.get("USID")));
			
			//点击按钮修改邮箱
			Button btn_mailChange = new Button(this, SWT.NONE);
			btn_mailChange.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					mailChange ad=new mailChange();
					ad.open();
				}
			});
			btn_mailChange.setBounds(610, 247, 127, 36);
			btn_mailChange.setText("点击修改邮箱");
			
			//点击进入充值界面
			Button btn_addBal = new Button(this, SWT.NONE);
			btn_addBal.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					addBal ad=new addBal();
					ad.open();
				}
			});
			btn_addBal.setBounds(610, 302, 89, 35);
			btn_addBal.setText("充 值");
			
			//点击按钮刷新内容
			Button btn_refresh = new Button(this, SWT.NONE);
			btn_refresh.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Map<String, Object> map;
					try {
						map = userinfodao.InquInfo(userName);
						text_email.setText(map.get("EMAIL").toString());
						text_balance.setText(map.get("BALANCE").toString());
						text_userNam.setText(map.get("UNAME").toString());
						text_userId.setText(map.get("USID").toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btn_refresh.setBounds(578, 413, 97, 34);
			btn_refresh.setText("刷新");
			
			//修改用户密码
			Button button = new Button(this, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ChangePwd ad=new ChangePwd();
					ad.open();
				}
			});
			button.setBounds(322, 424, 114, 34);
			button.setText("修改密码");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public static String getUserName(){
		return userName;
	}
}
