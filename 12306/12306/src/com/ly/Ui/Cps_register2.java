package com.ly.Ui;


import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.swtUtil;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class Cps_register2 {

	protected Shell shell;
	private Text text_userName;		//用户名文本框
	private Text text_pwd;				//密码文本框
	private Text text_pwdConfirm;		//密码确认文本框
	private Text text_mail;				//邮箱文本框
	private Text text_mailVeri;			//邮箱验证码文本框
	private Text text_seAnswer;		//密保问题答案文本框
	private Text text_veriWd;			//验证码文本框
	
	//设置字符串变量  方便获取文本框中内容
	private String userName;			//用户名
	private String pwd;					//密码
	private String pwdConfirm;			//密码确认
	private String mail;					//邮箱
	private String mailVeri;				//邮箱验证码
	private String seQues;				//密保问题题目
	private String seAnswer;			//密保问题答案
	private String veriWd;				//验证码
	
	private Label label_seRank_low;
	private Label label_seRankMid;
	private Label label_seRank_high;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Cps_register2 window = new Cps_register2();
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
		shell.setSize(1076, 689);
		shell.setText("SWT Application");
		
		Label label_title = new Label(shell, SWT.NONE);
		label_title.setAlignment(SWT.CENTER);
		label_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 32, SWT.NORMAL));
		label_title.setBounds(357, 10, 172, 57);
		label_title.setText("用户注册");
		
		Label label_userName = new Label(shell, SWT.NONE);
		label_userName.setBounds(234, 100, 48, 17);
		label_userName.setText("用户名：");
		
		text_userName = new Text(shell, SWT.BORDER);
		text_userName.setBounds(288, 97, 147, 23);
		
		Label label_tip_userName = new Label(shell, SWT.NONE);
		label_tip_userName.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_userName.setBounds(441, 100, 313, 17);
		label_tip_userName.setText("*  必须由字母、数字组成，长度不少于2位，不多于10位");
		
		Label label_pwd = new Label(shell, SWT.NONE);
		label_pwd.setBounds(234, 136, 36, 17);
		label_pwd.setText("密  码：");
		
		//根据输入的字符做调整
		text_pwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//获取验证码 
				pwd = text_pwd.getText().trim();							//密码
				int count = 0;
				count = countSeRank(pwd);
				
				
				if(count == 1){
					label_seRank_low.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_seRankMid.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
					label_seRank_high.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
				}else if(count == 2 ){
					label_seRank_low.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_seRankMid.setBackground(SWTResourceManager.getColor(255, 165, 0));
					label_seRank_high.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
				}else if(count == 3){
					label_seRank_low.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					label_seRankMid.setBackground(SWTResourceManager.getColor(255, 165, 0));
					label_seRank_high.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					
				}else if(count == 0){
					label_seRank_low.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
					label_seRankMid.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
					label_seRank_high.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
				}
			}
		});
		text_pwd.setBounds(288, 130, 147, 23);
		
		Label label_tip_pwd = new Label(shell, SWT.NONE);
		label_tip_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_pwd.setBounds(441, 136, 97, 17);
		label_tip_pwd.setText("*  不少于2位字符");
		
		Label label_seRank = new Label(shell, SWT.NONE);
		label_seRank.setBounds(222, 173, 61, 17);
		label_seRank.setText("安全级别：");
		
		label_seRank_low = new Label(shell, SWT.NONE);
		label_seRank_low.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		label_seRank_low.setBounds(298, 173, 61, 17);
		
		label_seRankMid = new Label(shell, SWT.NONE);
		label_seRankMid.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		label_seRankMid.setBounds(365, 173, 61, 17);
		
		label_seRank_high = new Label(shell, SWT.NONE);
		label_seRank_high.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		label_seRank_high.setBounds(436, 173, 61, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(222, 211, 61, 17);
		label_1.setText("确认密码：");
		
		text_pwdConfirm = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_pwdConfirm.setBounds(288, 208, 147, 23);
		
		Label label_tip_pwdConfirm = new Label(shell, SWT.NONE);
		label_tip_pwdConfirm.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_tip_pwdConfirm.setBounds(441, 211, 97, 17);
		label_tip_pwdConfirm.setText("*  请再次输入密码");
		
		Label label_mail = new Label(shell, SWT.NONE);
		label_mail.setBounds(222, 251, 61, 17);
		label_mail.setText("邮箱账号：");
		
		text_mail = new Text(shell, SWT.BORDER);
		text_mail.setBounds(288, 248, 147, 23);
		
		Label label_tip_mail = new Label(shell, SWT.NONE);
		label_tip_mail.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_mail.setBounds(441, 251, 104, 17);
		label_tip_mail.setText("*  请输入邮箱账号");
		
		Label label_mailVeri = new Label(shell, SWT.NONE);
		label_mailVeri.setBounds(209, 298, 73, 17);
		label_mailVeri.setText("邮箱验证码：");
		
		text_mailVeri = new Text(shell, SWT.BORDER);
		text_mailVeri.setBounds(288, 295, 147, 23);
		
		Label label_tip_mailVeri = new Label(shell, SWT.NONE);
		label_tip_mailVeri.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_mailVeri.setBounds(441, 298, 14, 17);
		label_tip_mailVeri.setText("*");
		
		
		//点击按钮获取验证码
		Button btn_veriWdGet = new Button(shell, SWT.NONE);
		btn_veriWdGet.setBounds(458, 291, 80, 27);
		btn_veriWdGet.setText("获取验证码");
		
		Label label_seQuestion = new Label(shell, SWT.NONE);
		label_seQuestion.setBounds(221, 349, 61, 17);
		label_seQuestion.setText("密保问题：");
		
		Combo combo_question = new Combo(shell, SWT.NONE);
		combo_question.setBounds(288, 346, 147, 25);
		combo_question.add("李阳和吴彦祖谁帅");
		combo_question.add("李阳和姚明谁高");
		combo_question.add("李阳和傅广健谁更能吃");
		combo_question.add("李阳和傅广健谁更厉害");
		
		Label label_tip_seQuestion = new Label(shell, SWT.NONE);
		label_tip_seQuestion.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_seQuestion.setBounds(441, 349, 129, 17);
		label_tip_seQuestion.setText("*  必填，方便邮箱修改");
		
		Label label_seAnswer = new Label(shell, SWT.NONE);
		label_seAnswer.setBounds(245, 398, 37, 17);
		label_seAnswer.setText("答案：");
		
		text_seAnswer = new Text(shell, SWT.BORDER);
		text_seAnswer.setBounds(288, 395, 147, 23);
		
		Label label_tip_Answer = new Label(shell, SWT.NONE);
		label_tip_Answer.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tip_Answer.setBounds(441, 398, 14, 17);
		label_tip_Answer.setText("*");
		
		Label label_veriWd = new Label(shell, SWT.NONE);
		label_veriWd.setBounds(234, 456, 48, 17);
		label_veriWd.setText("验证码：");
		
		text_veriWd = new Text(shell, SWT.BORDER);
		text_veriWd.setBounds(288, 453, 147, 23);
		
		Label label_pic_veriWd = new Label(shell, SWT.NONE);
		label_pic_veriWd.setBounds(445, 456, 93, 27);
		label_pic_veriWd.setText("验证码图片");
		
		
		//同意协议并注册按钮
		Button btn_agreeAndReg = new Button(shell, SWT.NONE);
		btn_agreeAndReg.addSelectionListener(new SelectionAdapter() {
			@Override 
			public void widgetSelected(SelectionEvent e) {
				UserInfoDao userinfodao = new UserInfoDao();
				//获取文本框中的内容
				swtUtil sUtil = new swtUtil();
				userName = text_userName.getText().trim();				//用户名
				pwd = text_pwd.getText().trim();							//密码
				pwdConfirm = text_pwdConfirm.getText().trim();			//密码确认
				mail = text_mail.getText().trim();							//邮箱
				mailVeri = text_mailVeri.getText().trim();					//邮箱验证码
				seQues = combo_question.getText().trim();				//密保问题题目
				seAnswer = text_seAnswer.getText().trim();				//密保问题答案
				veriWd = text_veriWd.getText().trim();					//验证码
				
				//若有空格未填写则弹出错误提示
				if(userName.equals("") || pwd.equals("") || pwdConfirm.equals("") || mail.equals("") || mailVeri.equals("") || seAnswer.equals("") || veriWd.equals("")){
					sUtil.showMessage(shell, "错误提示", "请将消息填写完整 \n其中 * 为必填项");
					return;
				}
				boolean flag = false;
				//判断用户名是否合法
				flag = isLetterOrDigit(flag,userName);
				
				//若用户名不合法  弹出提示
				if(flag == false){
					sUtil.showMessage(shell, "错误提示", "用户名输入不合法\n 检查后重试");
					return;
				}
				//若密码不合法 弹出提示
				if(pwd.length() <= 0){
					sUtil.showMessage(shell, "错误提示", "密码设置不合法  \n  检查后重试");
					return;
				}
				//确认密码和密码输入不同
				if(pwdConfirm.equals(pwd)){
					sUtil.showMessage(shell, "错误提示", "确认密码和密码输入不相同 \n 检查后重试");
					return;
				}
				//确认邮箱是否合法
				if(mail.indexOf('@') > 0 && mail.indexOf('.') > mail.indexOf('@')){
					flag = true;
				}else{
					flag = false;
				}
				if(flag == false){
					sUtil.showMessage(shell, "错误提示", "请输入正确的邮箱地址后重试");
					return;
				}
				//邮箱验证码认证
				
				//密保问题答案认证
				
				
				//验证码认证
				
				
				
				//进行信息认证并确认是否注册成功
				try {
					int result = userinfodao.setNewUser(userName, pwd, mail, seQues, seAnswer);
					System.out.println(result);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}

			
			
			
			//判断用户名是否仅包含数字字母
			private boolean isLetterOrDigit(boolean flag,String string) {
				for(int i = 0;i<string.length();i++){
					if(Character.isLowerCase(string.charAt(i)) || Character.isUpperCase(string.charAt(i)) || Character.isDigit(string.charAt(i))){
						flag = true;
					}else{
						flag = false;
						return flag;
					}
				}
				return flag;
			}
		});
		btn_agreeAndReg.setBounds(365, 489, 104, 27);
		btn_agreeAndReg.setText("同意协议并注册");
	}
	
	//对输入的密码字符串进行等级评估
	private int countSeRank(String pwd) {
		String s1 = ".*[a-zA-Z].*";
		String s2 = ".*[0-9].*";
		String s3 = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(s3);
		Matcher m = p.matcher(pwd);
		int count = 0;
		if(pwd.matches(s1))
			count++;
		if(pwd.matches(s2))
			count++;
		if(m.find())
			count++;
		
		return count;
	}

}
