package cn.fulong.bootdemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:GHB
 * @Date:2019-07-03 登陆的拦截器
 */
@Component
public class UserInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Object obj = request.getSession().getAttribute("SESSION_USER");

        if (obj != null) {
            return true;
        } else {
            //   没有值，没有登录，返回到登录页面：
            String content = request.getContextPath();
            System.out.println("登陆页面：" + content + "/index/login");
            response.sendRedirect(content + "/index/login");
            //   同时设置错误信息
            request.setAttribute("msg", "没有权限");
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
