package cn.fulong.bootdemo.config;


import cn.fulong.bootdemo.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:GHB
 * @Date:2019-06-23
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 直接编写实现方法
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //排除"/"下的全部路径，除了"/login.html","/","/user/login"
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/index/login.html","/user/doRegister", "/", "/index/login", "/static/**", "/webjars/**");
    }

}

