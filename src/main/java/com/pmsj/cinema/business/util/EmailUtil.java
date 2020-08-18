package com.pmsj.cinema.business.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    public int sendMail(String email) {
        try {
            int code = (int) (Math.random() * 900000 + 100000);
            final Properties props = new Properties();
            props.put("mail.user", "1594578345@qq.com");
            props.put("mail.password", "xoqnwlrubqnzbaeg"); // 授权码duduzzqqujlzfgbb,xoqnwlrubqnzbaeg
            props.put("mail.smtp.auth", "true");
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.port", "25");
            Session mailSession = Session.getDefaultInstance(props);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("1594578345@qq.com"));  //发件人
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); //收件人
            msg.setSubject("验证码-蜗牛影院"); //主题
            msg.setContent("<h1>此邮件为蜗牛影院影视平台专用验证码</h1><br />"
                    +"<h3>验证码在一分钟之内有效!</h3><br />"
                    + "验证码:" + code, "text/html;charset=UTF-8");
            msg.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.user"),
                    props.getProperty("mail.password"));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
