package cn.fulong.bootdemo.controller.email;


import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.service.EmailService;
import cn.fulong.bootdemo.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;


/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:01 上午
 * @desc 这个类是干啥呢
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Value("${my.config.upload-file}")
    private String uploadFile;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private EmailService emailService;


    @ResponseBody
    @RequestMapping("/sendTemplateEmail")
    public ResultBean sendTemplateEmail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("18701559738@163.com");
        mimeMessageHelper.setTo("haibog@outlook.com");
        mimeMessageHelper.setSubject("ThymeLeaf 模板邮件");
        // 利用 Thymeleaf 模板构建 html 文本
        Context ctx = new Context();
        ctx.setVariable("title", "Craig Walls");
        ctx.setVariable("content", "Hello Boot!");
        String emailText = templateEngine.process("/thymeleaf/email.html", ctx);
        mimeMessageHelper.setText(emailText, true);
        // 设置内嵌元素 cid，第一个参数表示内联图片的标识符，第二个参数标识资源引用
        mimeMessageHelper.addInline("boot", new ClassPathResource("/static/images/hero/105.jpg"));
        javaMailSender.send(mimeMessage);

        return new ResultBean<String>(ResultBean.SUCCESS, "", "");
    }


    /***
     * 文本邮件
     * @param users 多个逗号隔开
     * @param emailTitle 标题
     * @param emailText 内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public ResultBean sendSimpleEmail(String users, String emailTitle, String emailText) {
        String resultMsg = emailService.sendSimpleEmail(users, emailTitle, emailText);
        return new ResultBean<>(ResultBean.SUCCESS, resultMsg, "文本邮件发送");
    }

    /***
     * 附件发送
     * @param users 多个逗号隔开
     * @param emailTitle 标题
     * @param emailText 内容
     * @param attachFile 附件
     * @param attachName 附件名
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendMimeEmail")
    public ResultBean sendMimeEmail(String users, String emailTitle, String emailText, MultipartFile attachFile, String attachName) {
        File attach = null;
        if (!attachFile.isEmpty()) {
            // 附件名 带有后缀
            attachName = attachFile.getOriginalFilename();
            // 上传的地址
            String attachPath = uploadFile + File.separator + "email" + File.separator + UuidUtils.getUuid(32) + File.separator + attachName;
            attach = new File(attachPath);
            if (!attach.getParentFile().exists()) {
                attach.getParentFile().mkdirs();
            }
            try {
                attachFile.transferTo(attach);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String resultMsg = emailService.sendMimeEmail(users, emailTitle, emailText, attachName, attach);
        return new ResultBean<>(ResultBean.SUCCESS, resultMsg, "文本邮件发送");
    }

    /***
     * 富文本 html 内容 发送
     * @param users 多个逗号隔开
     * @param emailTitle 标题
     * @param emailText 内容
     * @param attachFile 附件
     * @param attachName 附件名
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public ResultBean sendHtmlEmail(String users, String emailTitle, String emailText, MultipartFile attachFile, String attachName) {
        File attach = null;
        if (!attachFile.isEmpty()) {
             //  附件名 带有后缀
            attachName = attachFile.getOriginalFilename();
            //  上传的地址
            String attachPath = uploadFile + File.separator + "email" + File.separator + UuidUtils.getUuid(32) + File.separator + attachName;
            attach = new File(attachPath);
            if (!attach.getParentFile().exists()) {
                attach.getParentFile().mkdirs();
            }
            try {
                attachFile.transferTo(attach);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String resultMsg = emailService.sendHtmlEmail(users, emailTitle, emailText, attachName, attach);
        return new ResultBean<>(ResultBean.SUCCESS, resultMsg, "富文本邮件发送");
    }


    @RequestMapping("/sendEmail")
    public String sendEmail() {
        return "emails/send_email";
    }

}
