package cn.fulong.bootdemo.service;


import cn.fulong.bootdemo.entity.SysUser;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author:GHB
 * @Date:2019-07-19
 *
 */
public interface UserService {


    SysUser getSysUser(int userId);

    Map getSysUsers(Integer offset, Integer limit);

    Map getUsersByPageNumSize(Integer pageNum, Integer pageSize);

    boolean saveSysUser(SysUser user);

    boolean delSysUser(int userId);


    boolean updateSysUser(SysUser user, int userId);



    void saveSysUsers(List<SysUser> users);

    SysUser querySysUser(String username, SysUser user);

    int delSysUsers(List<String> list);

    int delSysUsers(String[] arr);

    SysUser getUserById(int userId);

    JSONObject updateUserById(int userId, String username);

    JSONObject saveUser(String username);

    boolean ifExistsUsername(String username);
}
