package cn.fulong.bootdemo.controller.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author:GHB
 * @Date:2019-10-12 主页 面板
 */
@Controller
@RequestMapping("home")
public class HomeController {


    @RequestMapping("/dashboard")
    public String dashboard() {
        return "home/index";
    }

    @RequestMapping("/dashboard2")
    public String dashboard2() {
        return "home/index2";
    }

    @RequestMapping("/dashboard3")
    public String dashboard3() {
        return "home/index3";
    }


}
