package utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	/**
	 * 发送邮件
	 * 
	 * @param email
	 *            邮件的接收者
	 * @param emailMsg
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws GeneralSecurityException
	 */
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException, GeneralSecurityException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		// 设置发送的协议
		props.setProperty("mail.transport.protocol", "SMTP");

		// 开启ssl加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		// 设置发送邮件的服务器
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// 设置发送人的帐号和密码
				return new PasswordAuthentication("1424659514@qq.com", "ywperrfgwlpfibef");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		// 设置发送者
		message.setFrom(new InternetAddress("1424659514@qq.com"));

		// 设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email));

		// 设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		// 设置邮件内容
		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}


}
