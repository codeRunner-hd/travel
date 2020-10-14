package cn.itcast.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 *
 * @author Admin
 */
public final class MailUtils {
    /**
     * 发件人称号，同邮箱地址
     */
    private static final String USER = "1033952404@qq.com";
    /**
     * 如果是qq邮箱可以使用客户端授权码，或者登录密码
     */
    private static final String PASSWORD = "yeviebtfxfadbeff";

    /**
     * 发送验证信息的邮件
     *
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 邮件标题
     */
    public static void sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试发送邮件
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MailUtils.sendMail("test@163.com", "你好，这是一封测试邮件，无需回复。", "测试邮件");
        System.out.println("发送成功");
    }
}