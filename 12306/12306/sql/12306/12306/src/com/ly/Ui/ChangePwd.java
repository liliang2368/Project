package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChangePwd {

	protected Shell shell;
	private Text text_oldpwd;
	private Text text_newpwd;
	private Text text;
	private UserInfoDao dao=new UserInfoDao();
	private static String userName = MeunUi.getUserName();
	private String realPwd;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ChangePwd window = new ChangePwd();
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
		shell.setText("修改用户密码");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(33, 34, 72, 24);
		label.setText("旧密码：");
		
		text_oldpwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_oldpwd.setBounds(131, 31, 145, 30);
		
	
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(31, 95, 72, 24);
		label_1.setText("新密码：");
		
		text_newpwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_newpwd.setBounds(131, 92, 145, 30);
		
		Button button = new Button(shell, SWT.NONE);
		//确认修改
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String oldpwd=text_oldpwd.getText().trim();
				String newpwd=text_newpwd.getText().trim();
				String comfire=text.getText().trim();

				Map<String, Object> map;
				try {
					map = dao.getPwd(userName);
					realPwd = StringUtil.StringUtil(map.get("PWD"));
					System.out.println(realPwd);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//新密码未填写提示
				if(newpwd.equals("")){
					swtUtil.showMessage(shell, "错误提示", "请输入新密码");
				}
				
				//原密码错误提示
				if( ! oldpwd.equals(realPwd)){
					swtUtil.showMessage(shell, "错误提示", "原密码错误");
					return;
				}
				//新密码与原密码相同提示
			    if(  newpwd.equals(oldpwd)){
			    	swtUtil.showMessage(shell, "错误提示", "未修改原密码");
			    	return;
			    }
			    //确认密码与原密码不符提示
			    if( !comfire.equals(newpwd)){
			    	swtUtil.showMessage(shell, "错误提示", "确认密码与原密码不符");
			    	return;
			    }
			  try {
				int i=dao.changepwd(userName,newpwd);
				if( i == 1){
					swtUtil.showMessage(shell, "温馨提示", "密码修改成功");
					return;
				}else
					swtUtil.showMessage(shell, "错误提示", "修改失败");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		button.setBounds(136, 200, 114, 34);
		button.setText("确认修改");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(10, 149, 103, 24);
		label_2.setText("确认新密码：");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(132, 146, 144, 30);

	}

}
