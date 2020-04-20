package cn.fulong.bootdemo.controller.user;


import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.entity.SysUser;
import cn.fulong.bootdemo.r.R;
import cn.fulong.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Author:GHB
 * @Date:2019-07-19 用户表的
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户列表 mysql 自己手动分页
     *
     * @return
     */
    @GetMapping
    public ResultBean getUsers(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        Map map = userService.getSysUsers(pageNum, pageSize);
        ResultBean<Map> resultBean = new ResultBean();
        resultBean.setState(ResultBean.SUCCESS);
        resultBean.setMessage("用户数据列表");
        resultBean.setData(map);
        return resultBean;
    }


    /**
     * 用户列表 mysql 自己手动分页
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResultBean getUsersByPageNumSize(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        Map map = userService.getUsersByPageNumSize(pageNum, pageSize);
        ResultBean<Map> resultBean = new ResultBean();
        resultBean.setState(ResultBean.SUCCESS);
        resultBean.setMessage("用户数据列表");
        resultBean.setData(map);
        return resultBean;
    }


    /***
     * 单个用户信息
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}")
    public SysUser getUser(@PathVariable int userId) {

        return userService.getSysUser(userId);
    }

    /***
     * 修改用户信息
     * @param userId
     * @param user
     * @return
     */
    @PutMapping(value = "/{userId}")
    public ResultBean putUser(@PathVariable int userId, @ModelAttribute SysUser user) {

        boolean flag = userService.updateSysUser(user, userId);
        if (flag) {
            return new ResultBean(ResultBean.SUCCESS, "success", "更新用户成功");
        } else {
            return new ResultBean(ResultBean.ERROR, "error", "更新用户失败");
        }
    }

    /***
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/{userId}")
    public R deleteUser(@PathVariable int userId) {
        boolean flag = userService.delSysUser(userId);
        if (flag) {
            return R.ok().data("", "").message("删除用户成功");
        } else {
            return R.error().data("", "").message("删除用户失败");
        }
    }

    /***
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/register")
    public R saveUser(@ModelAttribute SysUser user) {
        boolean flag = userService.saveSysUser(user);
        if (flag) {
            return R.ok().data("username", user.getUsername()).message("用户注册成功,输入密码登陆");
        } else {
            return R.error().data("", "").message("用户注册失败，联系管理员");
        }
    }


    @RequestMapping(value = "/ifExistsUsername")
    public R ifExistsUsername(@RequestParam String username) {
        boolean flag = userService.ifExistsUsername(username);
        if (flag) {
            return R.ok().data("", "").message("用户名已存在，换一个吧");
        } else {
            return R.error().data("", "").message("");
        }
    }

}
