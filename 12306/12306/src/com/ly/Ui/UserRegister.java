package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.DataDis;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UserRegister extends Composite {
	private Text text_pwd;
	private UserInfoDao dao=new UserInfoDao();

	/**
	 * @throws SQLException 
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws  
	 */
	public UserRegister(Composite parent, int style) throws SQLException{
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.NORMAL));
		label.setBounds(298, 71, 180, 95);
		label.setText("用户登录");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_1.setBounds(185, 180, 97, 44);
		label_1.setText("用户名：");
		
		Combo combo_username = new Combo(this, SWT.NONE);
		//这里要获取到所有的用户名
		List<Map<String, Object>> list=dao.FindAll();
		System.out.println("这里的list集合为"+list);
		for(Map<String, Object> map:list){
			combo_username.add(StringUtil.toObjectString(map.get("UNAME")));
		}
		combo_username.setBounds(329, 186, 212, 63);
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_2.setBounds(185, 288, 97, 44);
		label_2.setText("密    码：");
		
		text_pwd = new Text(this, SWT.BORDER|SWT.PASSWORD);
		text_pwd.setBounds(329, 294, 212, 30);
		
		Button button = new Button(this, SWT.RADIO);
		button.setBounds(219, 380, 141, 24);
		button.setText("记住密码");
		
		Label label_3 = new Label(this, SWT.NONE);
		//忘记密码
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ForgetPwd pwd=new ForgetPwd();
				pwd.open();
			}
		});
		label_3.setBounds(438, 380, 90, 24);
		label_3.setText("忘记密码");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//登陆按钮先获取到用户名和密码
				String userName=combo_username.getText();
				String pwd=text_pwd.getText();
				//已经获取到账户和密码
				try {
					Map<String, Object> map=dao.FindByPwd(userName, pwd);
					if(map.size()>0 || map !=null){
						swtUtil.showMessage(getShell(), "温馨提示", "登陆成功");
						
					}else{
						swtUtil.showMessage(getShell(), "错误提示", "登陆失败");
						return ;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		button_1.setBounds(206, 451, 114, 34);
		button_1.setText("登录");
		
		Button button_2 = new Button(this, SWT.NONE);
		button_2.setBounds(438, 451, 114, 34);
		button_2.setText("取消");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
