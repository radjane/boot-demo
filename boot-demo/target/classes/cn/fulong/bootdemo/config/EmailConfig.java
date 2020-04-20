//package cn.fulong.bootdemo.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.mail")
//public class EmailConfig {
//
//    /****
//     * host
//     */
//    private String host;
//    /****
//     * 端口
//     */
//    private Integer port;
//    /****
//     * 用户名
//     */
//    private String username;
//    /****
//     * 用户名
//     */
//    private String password;
//
//    @Bean(value = "javaMailSenderMe")
//    public MailSender javaMailSenderMe() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost(host);
//        javaMailSender.setPort(port);
//        javaMailSender.setUsername(username);
//        javaMailSender.setPassword(password);
//        return javaMailSender;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public void setPort(Integer port) {
//        this.port = port;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
