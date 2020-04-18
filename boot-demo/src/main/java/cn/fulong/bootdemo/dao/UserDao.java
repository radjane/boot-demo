package cn.fulong.bootdemo.dao;


import cn.fulong.bootdemo.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author:GHB
 * @Date:2019-07-18
 */

@Component
public interface UserDao {


    /**
     * 分页插件
     *
     * @return
     */
    List<SysUser> selectUsersByPageHelper();

    /***
     * 查询用户列表
     * @param map 分页参数 pageNum pageSize
     * @return
     */
    List<SysUser> selectUsersByMyPageHelper(Map map);

    /***
     * 查询单个用户
     * @param userId  用户的id
     * @return
     */
    SysUser selectUserById(int userId);


    /**
     * 插入一条用户信息
     *
     * @param user
     * @return
     */
    boolean insertSysUser(SysUser user);

    /**
     * 插入多条用户信息
     *
     * @param users
     */
    void insertSysUsers(List<SysUser> users);

    /**
     * 删除单个用户
     *
     * @param userId
     */
    boolean deleteSysUserById(int userId);

    /***
     *  更新用户信息
     * @param user
     * @param userId
     */
    boolean updateSysUserById(SysUser user, int userId);


    /***
     * 查询用户 通过名称和手机号
     * @param username
     * @param user
     * @return
     */
    SysUser selectUserByNameAndPhone(String username, SysUser user);

    /***
     * 删除用户多个 入参 list
     * @param list
     * @return
     */
    int delSysUsersByList(List<String> list);

    /***
     * 删除用户多个 入参 数组
     * @param arr
     * @return
     */
    int delSysUsersByArr(String[] arr);

    /***
     * 用户名密码 查询用户
     * @param username
     * @param password
     * @return
     */
    SysUser selectUserByUsernameAndPassword(String username, String password);
}
