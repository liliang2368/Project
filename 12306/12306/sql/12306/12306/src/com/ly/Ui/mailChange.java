package com.ly.Ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ly.dao.UserInfoDao;
import com.ly.util.MailTest;
import com.ly.util.StringUtil;
import com.ly.util.swtUtil;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class mailChange {

	protected Shell shell;
	private Text text_answer;
	private Text text_oldMail;
	private Text text_newMail;
	private Text text_maiVeri;
	private Text text_veviWd;
	
	private static String testCode;
	private static String sendVeri;
	private String mail;
	private String veriWd;
	
	private Label label_veriWd;
	
	private UserInfoDao userinfodao = new UserInfoDao();
	private StringUtil stringutil = new StringUtil();
	//需从父窗口传入用户名作为查询条件
	private String userName = Cps_userInfo.getUserName();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mailChange window = new mailChange();
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
		shell.setSize(697, 517);
		shell.setText("邮箱修改界面");
	
		Label label_title = new Label(shell, SWT.NONE);
		label_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 17, SWT.NORMAL));
		label_title.setBounds(272, 23, 99, 35);
		label_title.setText("邮箱修改");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(190, 107, 57, 17);
		label.setText("密保问题：");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(169, 165, 99, 17);
		label_1.setText("密保问题答案：");
		
		text_answer = new Text(shell, SWT.BORDER);
		text_answer.setBounds(307, 162, 163, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(169, 224, 78, 17);
		label_2.setText("原邮箱地址：");
		
		text_oldMail = new Text(shell, SWT.BORDER);
		text_oldMail.setBounds(307, 221, 163, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(169, 282, 78, 17);
		label_3.setText("新邮箱地址：");
		
		text_newMail = new Text(shell, SWT.BORDER);
		text_newMail.setBounds(307, 279, 163, 23);
		
		//点击按钮发送验证码
		Button btn_sendWd = new Button(shell, SWT.NONE);
		btn_sendWd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mail = text_newMail.getText().trim();
				sendVeri = getRandomString2(6);
				System.out.println(sendVeri);
				MailTest sendMail = new MailTest();
				swtUtil sUtil = new swtUtil();
				boolean flag = false;
				if(mail.equals("")){
					sUtil.showMessage(shell, "错误提示", "请输入邮箱");
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
				sendMail.send(sendVeri, mail);
				
			}
		
		});
		btn_sendWd.setBounds(491, 277, 99, 27);
		btn_sendWd.setText("点击发送验证码");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(169, 343, 99, 17);
		label_4.setText("收到的验证码");
		
		text_maiVeri = new Text(shell, SWT.BORDER);
		text_maiVeri.setBounds(307, 340, 78, 23);
		
		//验证信息并提示是否修改成功
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				swtUtil sUtil = new swtUtil();
				//获取文本框中信息
				String answer = text_answer.getText().trim();
				String oldMail = text_oldMail.getText().trim();
				String newMail = text_newMail.getText().trim();
				String mailVeri = text_maiVeri.getText().trim();
				String VeriWd = text_veviWd.getText().trim();
				String realAnswer = "";
				String realOldMail = "";
				//获取该账号的问题和答案进行比较
				try {
					realAnswer = stringutil.toObjectString(userinfodao.getAns(userName).get("ANSWER"));
					realOldMail = stringutil.toObjectString(userinfodao.getMail(userName).get("EMAIL"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(realAnswer);
				System.out.println(realOldMail);
				
				//若有空格未填写则提示错误
				if(answer.equals("") || oldMail.equals("") || newMail.equals("") || mailVeri.equals("") || VeriWd.equals("")){
					sUtil.showMessage(shell, "错误提示", "有空格未填写\n检查后重试");
					try {
						image(label_veriWd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				//密保问题回答错误提示错误
				if( ! answer.equals(realAnswer)){
					sUtil.showMessage(shell, "修改失败", "密保问题回答错误");
					try {
						image(label_veriWd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				//原邮箱地址填写错误提示
				if( ! oldMail.equals(realOldMail)){
					sUtil.showMessage(shell, "修改失败", "原邮箱地址填写错误");
					try {
						image(label_veriWd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
				//确认邮箱是否合法
				boolean flag = false;
				if(mail.indexOf('@') > 0 && mail.indexOf('.') > mail.indexOf('@')){
					flag = true;
				}else{
					flag = false;
				}
				if(flag == false){
					sUtil.showMessage(shell, "错误提示", "请输入正确的邮箱地址后重试");
					return;
				}
				
				//判断收到的验证码是否正确
				if(!sendVeri.equals(mailVeri)){
					sUtil.showMessage(shell, "修改失败", "邮箱验证码填写错误");
					try {
						image(label_veriWd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
				//判断验证码是否正确
				if( !testCode.equals(VeriWd) ){
					sUtil.showMessage(shell, "修改失败", "验证码填写错误");
					try {
						image(label_veriWd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
				//通过认证并提交
				try {
					int result = userinfodao.updateMail(userName, newMail);
					System.out.println(result);
					if(result == 1){
						sUtil.showMessage(shell, "成功", "修改成功");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button_1.setBounds(272, 441, 99, 27);
		button_1.setText("点击完成修改");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBounds(169, 397, 61, 17);
		label_5.setText("验证码：");
		
		text_veviWd = new Text(shell, SWT.BORDER);
		text_veviWd.setBounds(307, 394, 78, 23);
		
		label_veriWd = new Label(shell, SWT.NONE);
		label_veriWd.setBounds(418, 397, 83, 23);
		
		Label label_question = new Label(shell, SWT.NONE);
		label_question.setBounds(307, 107, 163, 17);
		try {
			label_question.setText(stringutil.toObjectString(userinfodao.getQue(userName).get("QUESTION")));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			image(label_veriWd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	 //生成图片验证码
	 public static void image(Label label ) throws IOException{
		   testCode = "";
		 	//BufferedImage 的构造函数可以设置图片的大小
		   BufferedImage image = new BufferedImage(label.getSize().x, label.getSize().y,BufferedImage.TYPE_INT_RGB);//这里设置图片的大小
		   //这里需要使用到java.awt.Graphics来绘制图片
		   java.awt.Graphics graphics = image.getGraphics();
		   Color color = new Color(245, 245, 220);
		   graphics.setColor(color);//为图片添加的底色
		   graphics.fillRect(0,0,label.getSize().x,label.getSize().y);
		   char[] content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		   Random random = new Random();
		   int index;
		   for(int i=0;i<4;i++){//验证码长度
					index = random.nextInt(content.length);
					testCode+=String.valueOf(content[index]);//testcode是验证码
					//图片中文字的颜色
					graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					//图片中文字的位置
					graphics.drawString(content[index]+"",10+20*i,10+3*random.nextInt(4));
					//1,验证码文字,2文字距离上边的距离3,距离下部分的距离,可以更改这后面的两个数据,来改变图片的,验证码显示位置
		   }
		  ByteArrayOutputStream stream=new ByteArrayOutputStream();
		  ImageIO.write(image, "jpg", stream);
		  InputStream inputStream=new ByteArrayInputStream(stream.toByteArray());
		  label.setImage(new Image(null, new ImageData(inputStream).scaledTo(label.getSize().x, label.getSize().y)));
		  System.out.println(testCode);
	 }
	//生成验证码
		 public static String getRandomString2(int length){
			    Random random=new Random();
			    StringBuffer sb=new StringBuffer();
			    for(int i=0;i<length;i++){
			       int number=random.nextInt(3);
			       long result=0;
			       switch(number){
			          case 0:
			              result=Math.round(Math.random()*25+65);
			              sb.append(String.valueOf((char)result));
			              break;
			         case 1:
			             result=Math.round(Math.random()*25+97);
			             sb.append(String.valueOf((char)result));
			             break;
			         case 2:     
			             sb.append(String.valueOf(new Random().nextInt(10)));
			             break;
			        }


			     }
			     return sb.toString();
			 }
}
