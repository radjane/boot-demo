package cn.fulong.bootdemo.service.impl.index;

import cn.fulong.bootdemo.dao.UserDao;
import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.entity.SysUser;
import cn.fulong.bootdemo.service.IndexService;
import cn.fulong.bootdemo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:GHB
 * @Date:2019-06-23
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDao userDao;

    @Override
    public ResultBean login(String username, String password) {
        ResultBean data = new ResultBean();
        SysUser user = null;

        try {
            user= (SysUser) redisUtils.get(username);
        } catch (Exception e) {
            log.error("redis 出错了！");
            e.printStackTrace();
        }

        //缓存里面有
        if (user != null) {
            data.setState(ResultBean.SUCCESS);
            data.setData(user);
        } else {
            user = userDao.selectUserByUsernameAndPassword(username, password);
            if (user != null) {
                data.setState(ResultBean.SUCCESS);
                data.setData(user);
                redisUtils.set(username + "|" + password, user);
            } else {
                data.setState(ResultBean.ERROR);
                data.setData("用户名或密码错误，请重新输入！");
            }
        }


        return data;
    }
}


