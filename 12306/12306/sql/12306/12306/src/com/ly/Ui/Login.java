package com.ly.Ui;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.RegisterUtils;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login {

	protected Shell shell;
	private Text user_text;
	private Text pwd_text;
	private Button rmb_button;
	private Button begin_button;
    private UserInfoDao dao = new UserInfoDao();
	private static String userName;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		shell.setSize(786, 519);
		shell.setText(" ");
		
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(319, 48, 136, 55);
		lblNewLabel.setText("登录系统");
		
		Label user_label = new Label(shell, SWT.NONE);
		user_label.setBounds(191, 124, 76, 20);
		user_label.setText("用户名：");
		
		Label pwd_label = new Label(shell, SWT.NONE);
		pwd_label.setBounds(191, 175, 76, 20);
		pwd_label.setText("密  码：");
		
		user_text = new Text(shell, SWT.BORDER);
		user_text.setBounds(299, 122, 178, 26);
		
		pwd_text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		pwd_text.setBounds(301, 179, 174, 26);
		
		//复选框
		rmb_button = new Button(shell, SWT.CHECK);
		rmb_button.setBounds(300, 241, 121, 20);
		rmb_button.setText("记住密码");
		
		//登录
		begin_button = new Button(shell, SWT.NONE);
		//用户登录操作
		begin_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取用户名和密码
				String username = user_text.getText().trim();
				String userpwd = pwd_text.getText().trim();
				boolean flag = rmb_button.getSelection(); //获取按钮是否被选中
				//调用dao中登录的方法
				Map<String, Object> map = null;
				try {
					map = dao.FindByPwd(username, userpwd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				    //登录成功
					if(null!=map && map.size()>0) {
						//选中了记住密码
						if(flag==true) {
							//操作注册表
							Map<String, String> map2 = new HashMap<String, String>();
							map2.put("login", map.get("UNAME") + "-" + map.get("UPWD"));
							RegisterUtils.add(map2);
						}else {
							//取消记住密码
							RegisterUtils.deleteInfo("login");// 删除注册表信息
						}
						//页面的跳转
						MeunUi.setBtn_Login();
						MeunUi.setUserName(userName);
						System.out.println("这里这里");
						System.out.println(userName);
						MeunUi.setFlag(true);
						MeunUi.setBtn_Login();
						MeunUi.setBtn_Wel();
						
					}else {
						//登录失败
						swtUtil.showMessage(shell, "错误提示", "登录失败");
					}
			}
		});
		begin_button.setBounds(292, 301, 98, 30);
		begin_button.setText("登录");

		    // 是否操作了记住密码，从注册表中查看
			String info = RegisterUtils.findInfo("login");
			if (null != info && !"".equals(info)) {
				String arr[] = info.split("-");
				if (null != arr && arr.length == 2) {
						user_text.setText(arr[0]);
						pwd_text.setText(arr[1]);
					}
				}
	}
}
