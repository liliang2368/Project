package com.ly.Text;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import oracle.jdbc.driver.Message;

public class test04 {

	/**
     * 注册成功，发送消息通知
     * @param toEmail 收件人电子邮箱
     * @param fromEmail 发件人电子邮箱
     * @param authEmail 发件人电子邮箱帐号
     * @param authPaw 发件人电子邮箱密码
     * @param title 邮件标题
     * @param text 邮件内容
     */
    public static void sendEmail(String toEmail,String fromEmail,
                    final String authEmail,final char[] authPaw,
                    String title,String text){
        // 收件人电子邮箱
        String to = toEmail;

        // 发件人电子邮箱
        String from = fromEmail;

        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        // 获取默认session对象
        Session session = Session.getInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(authEmail, authPaw); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            //message.setSubject("I love u");
            message.setSubject(title);

            // 设置消息体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送 HTML 消息, 可以插入html标签
            // 发送消息
            Transport.send(message);
            System.out.println("Sent email successfully...");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
        

        
        
        
        
        
    }
    public static void main(String[] args) {
        String toEmail = "xxxxxxx@qq.com";
        String sendTitle = "标题";
        String sendText = "<p>全CSDN的丰（好）色（se）博客，这里有书本看不到的Java技术，电影院禁播的电影，欢迎关注QQ群494808400</p>";
        sendEmails(toEmail, sendTitle, sendText);;
    }
}
