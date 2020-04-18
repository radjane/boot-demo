package cn.fulong.bootdemo.controller.index;


import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.entity.SysUser;
import cn.fulong.bootdemo.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 *@Author:GHB
 *@Date:2019-06-22
 *
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /***
     * index
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "/index/login";
    }


    /***
     * index
     * @return
     */
    @RequestMapping("/logout")
    public String logout( HttpServletRequest request){
        request.getSession().removeAttribute("SESSION_USER");
        return "/index/login";
    }

    /***
     * 登陆
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
     public ModelAndView login(ModelAndView model, HttpServletRequest request, String username, String password) {
         ResultBean data= indexService.login(username,password);
         if(data.getState()==ResultBean.SUCCESS){
             SysUser user=(SysUser)data.getData();
             log.info("msg:",user.toString());
             //  保存会话
             request.getSession().setAttribute("SESSION_USER",user);
             //  登陆成功 跳转 首页
             model.setViewName("/index/index");
            return model;
         }else{
             //  登陆失败 用户名或者 用户名 错误
             model.addObject("message",data.getMessage());
             log.info("msg:",data.toString());
             model.setViewName("/index/login");
             return model;
         }

    }





}
