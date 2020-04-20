package cn.fulong.bootdemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author:GHB
 * @Date:2019-11-04 邮件发送
 */
@Component
public class EmailUtils {

    @Autowired
    JavaMailSender javaMailSender;

    /***
     * 文本邮件
     * @param from 发送的账号
     * @param users 接收的账号
     * @param subject 邮件标题
     * @param text 邮件内容
     */
    public boolean sendSimpleEmail(String from, String users, String subject, String text) {
        if (StringUtils.isEmpty(subject)) {
            subject = "测试邮件";
        }
        if (StringUtils.isEmpty(text)) {
            text = "测试邮件内容";
        }
        try {
            // 构造Email消息
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(users);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /***
     * 附件发送
     * @param from 发送的账号
     * @param users 接收的账号
     * @param subject 邮件标题
     * @param text  邮件内容
     * @param attachPath  路径
     */
    public boolean sendMimeEmail(String from, String users, String subject, String text, String attachName, File attachPath) {
        try {
            // MimeMessage 本身的 API 有些笨重，我们可以使用 MimeMessageHelper
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // 第二个参数是 true ，表明这个消息是 multipart类型的/
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(users);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
            if (attachPath != null) {
                mimeMessageHelper.addAttachment(attachName, attachPath);
            }
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /***
     * 富文本格式
     * @param from 发送的账号
     * @param users 接收的账号
     * @param subject 邮件标题
     * @param html  邮件内容 html
     * @param attachPath  路径
     * @throws MessagingException
     */
    public boolean sendHtmlEmail(String from, String users, String subject, String html, String attachName, File attachPath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 第二个参数是 true ，表明这个消息是 multipart类型的/
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(users);
            mimeMessageHelper.setSubject(subject);
            if (StringUtils.isEmpty(html)) {
                html = "<html><body><h4>Hello,SpringBoot</h4><img src='cid:boot' /></body></html>";
            }
            mimeMessageHelper.setText(html, true);
            // 设置内嵌元素 cid，第一个参数表示内联图片的标识符，第二个参数标识资源引用
            if (attachPath != null) {
                mimeMessageHelper.addAttachment(attachName, attachPath);
            }
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
