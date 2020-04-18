package cn.fulong.bootdemo.service;


import cn.fulong.bootdemo.entity.ResultBean;



/**
 * @Author:GHB
 * @Date:2019-06-22
 *
 */
public interface IndexService {

    ResultBean login(String username, String password);
}
