package cn.fulong.bootdemo.service.impl.email;

import cn.fulong.bootdemo.service.EmailService;
import cn.fulong.bootdemo.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author:GHB
 * @Date:2019-07-07
 */
@Service
public class EmailServiceImpl implements EmailService {


    /***
     *     获取yml里的配置username
     */
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public String sendSimpleEmail(String users, String emailTitle, String emailText) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] emails = users.split(",");

        int countSuccess = 0;
        int countFail = 0;
        for (int i = 0; i < emails.length; i++) {
            boolean flag = emailUtils.sendSimpleEmail(from, emails[i], emailTitle, emailText);
            if (!flag) {
                stringBuffer.append(emails[i] + "发送失败。");
                countFail++;
            } else {
                stringBuffer.append(emails[i] + "发送成功。");
                countSuccess++;
            }
        }
        stringBuffer.insert(0, "成功："+countSuccess+"，失败"+countFail+"｜");
        return stringBuffer.toString();
    }


    @Override
    public String sendMimeEmail(String users, String emailTitle, String emailText, String attachName, File attachPath) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] emails = users.split(",");

        int countSuccess = 0;
        int countFail = 0;
        for (int i = 0; i < emails.length; i++) {
            boolean flag = emailUtils.sendMimeEmail(from, emails[i], emailTitle, emailText, attachName, attachPath);
            if (!flag) {
                stringBuffer.append(emails[i] + "发送失败。");
                countFail++;
            } else {
                stringBuffer.append(emails[i] + "发送成功。");
                countSuccess++;
            }
        }
        stringBuffer.insert(0, "成功："+countSuccess+"，失败"+countFail+"｜");
        return stringBuffer.toString();
    }


    @Override
    public String sendHtmlEmail(String users, String emailTitle, String emailText, String attachName, File attachPath) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] emails = users.split(",");

        int countSuccess = 0;
        int countFail = 0;
        for (int i = 0; i < emails.length; i++) {
            boolean flag = emailUtils.sendHtmlEmail(from, emails[i], emailTitle, emailText, attachName, attachPath);
            if (!flag) {
                stringBuffer.append(emails[i] + "发送失败。");
                countFail++;
            } else {
                stringBuffer.append(emails[i] + "发送成功。");
                countSuccess++;
            }
        }
        stringBuffer.insert(0, "成功："+countSuccess+"，失败"+countFail+"｜");
        return stringBuffer.toString();
    }

}
