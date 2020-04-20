package cn.fulong.bootdemo.service;

import java.io.File;

public interface EmailService {

    String sendSimpleEmail(String users, String emailTitle, String emailText);

    String sendMimeEmail(String users, String emailTitle, String emailText, String attachName, File  attachPath);

    String sendHtmlEmail(String users, String emailTitle, String emailText, String attachName, File attachPath);
}
