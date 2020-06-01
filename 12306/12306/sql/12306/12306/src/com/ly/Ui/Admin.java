package com.ly.Ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.adminDao;
import com.ly.util.DataDis;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Admin extends Composite {
	private Text user_text;
	private Text pwd_test;
	private adminDao dao=new adminDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Admin(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 26, SWT.NORMAL));
		label.setBounds(270, 83, 222, 75);
		label.setText("\u7BA1\u7406\u5458\u767B\u9646");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(203, 246, 76, 20);
		label_1.setText("\u7528\u6237\u540D");
		
		user_text = new Text(this, SWT.BORDER);
		user_text.setBounds(297, 243, 165, 26);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(203, 298, 76, 20);
		label_2.setText("\u5BC6    \u7801");
		
		pwd_test = new Text(this, SWT.BORDER | SWT.PASSWORD);
		pwd_test.setBounds(297, 298, 165, 26);
		
		Button button = new Button(this, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				String username=user_text.getText();
				String pwd=pwd_test.getText();
				try {
					Map<String, Object> map=dao.resint(username, pwd);
					if(map.size()>0){
						swtUtil.showMessage(getShell(), "温馨提示", "登陆成功");
					//	getShell().close();
						Admin1 admin=new Admin1();
					admin.open();	
					}else{
						swtUtil.showMessage(getShell(), "错误提示ʾ", "登陆失败");
						return ;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setBounds(233, 376, 76, 26);
		button.setText("\u767B\u9646");
		 
		Button button_1 = new Button(this, SWT.NONE);
		button_1.setText("\u53D6\u6D88");
		button_1.setBounds(432, 376, 76, 26);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
