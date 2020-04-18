package cn.fulong.bootdemo.service.impl.user;

import cn.fulong.bootdemo.dao.UserDao;
import cn.fulong.bootdemo.entity.SysUser;
import cn.fulong.bootdemo.service.UserService;
import cn.fulong.bootdemo.utils.MyPageHelper;
import cn.fulong.bootdemo.utils.PageUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author:GHB
 * @Date:2019-07-03
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public SysUser getUserById(int userId) {
        StringBuilder sql=new StringBuilder("select * from SYS_USER t where t.id=?");
         List<SysUser> listUser= jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(SysUser.class),userId);
        return  listUser.get(0);
    }

    @Override
    public JSONObject updateUserById(int userId, String username) {

        JSONObject data=new JSONObject();
        StringBuilder sql=new StringBuilder("update SYS_USER set username=? where id=? ");
        int rows=jdbcTemplate.update(sql.toString(),username,userId);
        if(rows>0){
            data.put("msgCode",1);
            data.put("msg","update success");
        }else{
            data.put("msgCode",-1);
            data.put("msg","update fail");
        }
        return data;
    }

    @Override
    public JSONObject saveUser(String username) {
        JSONObject data=new JSONObject();
        StringBuilder sql=new StringBuilder("insert into SYS_USER (username) values(?) ");
        int rows=jdbcTemplate.update( sql.toString(),username);
        if(rows>0){
            data.put("msgCode",1);
            data.put("msg","save success");
        }else{
            data.put("msgCode",-1);
            data.put("msg","save fail");
        }
        return data;
    }


    @Override
    public SysUser getSysUser(int userId) {

        return userDao.selectUserById(userId);
    }


    @Override
    public Map getSysUsers(Integer pageNum,Integer pageSize) {
        Map data=new HashMap(0);

        // 总共多少条
        StringBuffer sql=new StringBuffer("select count(*) from sys_user");
        int totalCount=jdbcTemplate.queryForObject(sql.toString(),Integer.class);

        MyPageHelper pageHelper = PageUtils.getMyPageHelper(pageNum,pageSize,totalCount);


        Map params = new HashMap(0);
        params.put("offset", (pageHelper.getCurrentPage() - 1) * pageHelper.getPageSize());
        params.put("limit", pageHelper.getPageSize());

        List<SysUser> users= userDao.selectUsersByMyPageHelper(params);
        // 数据信息
        data.put("users",users);
        // 分页信息
        data.put("pageHelper",pageHelper);

        return data;
    }

    /***
     *
     * @param pageNum  开始的条数号
     * @param pageSize 一页分几条
     * @return
     */
    @Override
    public Map getUsersByPageNumSize(Integer pageNum, Integer pageSize) {
        Map data=new HashMap(0);

        // 总共多少条数据
        ISelect iSelect=new ISelect() {
            @Override
            public void doSelect() {
                userDao.selectUsersByPageHelper();
            }
        };
        Long totalNum= PageHelper.count(iSelect);
        int  totalCount=totalNum.intValue();
        MyPageHelper pageHelper = PageUtils.getMyPageHelper(pageNum,pageSize,totalCount);

        //用插件进行分页
        Page page=PageHelper.startPage(pageNum, pageSize);
        List<SysUser> users=userDao.selectUsersByPageHelper();
        data.put("users",users);
        data.put("pageHelper",pageHelper);
        return data;
    }

    @Override
    public boolean saveSysUser(SysUser user) {
        return userDao.insertSysUser(user);
    }

    @Override
    public void saveSysUsers(List<SysUser> users) {
        userDao.insertSysUsers(users);
    }

    @Override
    public boolean delSysUser(int userId) {
        return userDao.deleteSysUserById(userId);
    }

    @Override
    public boolean updateSysUser(SysUser user, int userId) {
       return userDao.updateSysUserById(user,userId);
    }

    @Override
    public SysUser querySysUser(String username, SysUser user) {
        return userDao.selectUserByNameAndPhone(username,user);
    }

    @Override
    public int delSysUsers(List<String> list) {
        return userDao.delSysUsersByList(list);
    }

    @Override
    public int delSysUsers(String[] arr) {
        return userDao.delSysUsersByArr(arr);
    }


}
