package com.ly.Ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.PassengerInfoDao;
import com.ly.dao.UserInfoDao;
import com.ly.util.DataDis;
import com.ly.util.DataDis;
import com.ly.util.RegisterUtils;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Cps_userLogin extends Composite {
	private Text pwd_text;
	private Button rmb_button;
	private Button begin_button;
    private UserInfoDao dao = new UserInfoDao();
	private static String userName;
	private Combo combo_user;
	private Label label;
	private PassengerInfoDao infoDao=new PassengerInfoDao();
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws SQLException 
	 */
	public Cps_userLogin(Composite parent, int style) throws SQLException {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(319, 48, 136, 55);
		lblNewLabel.setText("登录系统");
		
		Label user_label = new Label(this, SWT.NONE);
		user_label.setBounds(191, 124, 76, 20);
		user_label.setText("用户名：");
		
		Label pwd_label = new Label(this, SWT.NONE);
		pwd_label.setBounds(191, 175, 76, 20);
		pwd_label.setText("密  码：");
		
		pwd_text = new Text(this, SWT.BORDER | SWT.PASSWORD);
		pwd_text.setBounds(301, 179, 174, 26);
		
		//复选框
		rmb_button = new Button(this, SWT.CHECK);
		rmb_button.setBounds(259, 240, 121, 20);
		rmb_button.setText("记住密码");
		
		//登录
		begin_button = new Button(this, SWT.NONE);
		//用户登录操作
		begin_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取用户名和密码
				userName = combo_user.getText().trim();
				String userpwd = pwd_text.getText().trim();
				boolean flag = rmb_button.getSelection(); //获取按钮是否被选中
				//调用dao中登录的方法
				Map<String, Object> map = null;
				try {
					map = dao.FindByPwd(userName, userpwd);
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
						MeunUi.setFlag(true);
						MeunUi.setBtn_Login();
						MeunUi.setBtn_Wel();
			
						swtUtil.showMessage(getShell(), "提示", "登陆成功");
					}else {
						//登录失败
						swtUtil.showMessage(getShell(), "错误提示", "登录失败");
					}
			}
		});
		begin_button.setBounds(292, 301, 98, 30);
		begin_button.setText("登录");
		
		combo_user = new Combo(this, SWT.NONE);
		combo_user.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDown(MouseEvent e) {
				combo_user.removeAll();
				try {
					List<Map<String, Object>> list=infoDao.FindaLL();
					for(Map<String, Object> map:list){
						combo_user.add(StringUtil.toObjectString(map.get("PNAME")));
					}
					combo_user.select(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		//查询所有的账户
		List<Map<String, Object>> list=dao.message();//查询得到所有的信息
		
		
		combo_user.setBounds(301, 124, 174, 35);
		
		label = new Label(this, SWT.NONE);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//忘记密码
				ForgetPwd forget=new ForgetPwd();
				forget.open();
			}
		});
		label.setBounds(432, 240, 76, 20);
		label.setText("忘记密码");

		    // 是否操作了记住密码，从注册表中查看
			String info = RegisterUtils.findInfo("login");
			if (null != info && !"".equals(info)) {
				String arr[] = info.split("-");
				if (null != arr && arr.length == 2) {
					combo_user.setText(arr[0]);
						pwd_text.setText(arr[1]);
					}
				}
		
	}
	public static String getuserName(){
		return  userName;
	}



	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
