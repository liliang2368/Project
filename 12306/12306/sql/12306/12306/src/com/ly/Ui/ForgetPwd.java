package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.ly.dao.UserInfoDao;
import com.ly.util.GetVerCode;
import com.ly.util.MailTest;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class ForgetPwd {

	protected Shell shell;
	private Text text_username;
	private Text text_yanzhenm;
	private MailTest email=new MailTest();
	private Text text_answer;
	private UserInfoDao info=new UserInfoDao();
	private Combo combo_question;
	private GetVerCode code=new GetVerCode();
	private String emailNmae;//邮箱
	private String str;//验证码
	public String answer;//密保答案
	public static String username;//用户名

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ForgetPwd window = new ForgetPwd();
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
		shell.setSize(580, 385);
		shell.setText("找回密码");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(94, 45, 90, 24);
		label.setText("用户名：");
		
		text_username = new Text(shell, SWT.BORDER);
		text_username.addFocusListener(new FocusAdapter() {
			//输入用户名要查找出密保的问题
			@Override
			public void focusLost(FocusEvent e) {
				//从这里获取到用户的姓名
				//然后将密保放入框内
				username=text_username.getText();
				try {
					Map<String, Object> map=info.Findby(username);
					//将map的值放入到密保框中
					combo_question.add(StringUtil.toObjectString(map.get("QUESTION")));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		text_username.setBounds(226, 42, 167, 30);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(94, 195, 108, 24);
		label_1.setText("邮箱验证码：");
		
		text_yanzhenm = new Text(shell, SWT.BORDER);
		text_yanzhenm.setBounds(226, 192, 167, 30);
		
		Button button = new Button(shell, SWT.NONE);
		//获取验证码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//先应该获取验证码
				str=code.getRandomString2(6);//获取六位数字的验证码
				System.out.println("邮箱的验证码为:"+str);
				//将验证码发送到邮箱
				Map<String, Object> map;
				try {
					System.out.println(username);
					map = info.Findby(username);
					emailNmae=StringUtil.toObjectString(map.get("EMAIL"));
					 answer=StringUtil.toObjectString(map.get("ANSWER"));
						System.out.println(emailNmae);

							 //这里是获取打密保的答案				
						email.send( str,emailNmae);	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//这里查询出邮箱
	
			}
		});
		button.setBounds(438, 188, 114, 34);
		button.setText("获取验证码");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//先检查密保的正确性
				if(text_answer.equals(answer) && text_yanzhenm.equals(str)) {
					//说明密保正确可以修改
					//页面就可以跳转到下一步
					ForgetPwd pew=new ForgetPwd();
					pew.open();//页面跳转
				}else{
					swtUtil.showMessage(shell, "错误提示", "验证码或者是验证码输入错误");
					return;
				}
			}
		});
		//下一步
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ForgetPwd2 pwd2=new ForgetPwd2();
				shell.dispose();
				pwd2.open();
			}
		});
		button_1.setBounds(251, 238, 114, 34);
		button_1.setText("下一步");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(74, 89, 97, 24);
		label_2.setText("选择密保问题");
		
		combo_question = new Combo(shell, SWT.NONE);
		combo_question.setBounds(226, 89, 167, 28);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(94, 148, 76, 20);
		lblNewLabel.setText("密保答案");
		
		text_answer = new Text(shell, SWT.BORDER);
		text_answer.setBounds(226, 145, 167, 30);

	}
}
