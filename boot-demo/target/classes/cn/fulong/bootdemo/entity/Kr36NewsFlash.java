package cn.fulong.bootdemo.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:37 下午
 * @desc 36氪新闻实体类
 */
@Data
public class Kr36NewsFlash {

    /***
     * 主键
     */
    int id;
    /***
     *  快讯的id
     */
    int bId;
    /***
     *  标题
     */
    String title;

    /***
     *  标题长度
     */
    int titleLength;
    /***
     *  内容
     */
    String description;
    /***
     * 新闻地址
     */
    String newsUrl;
    /***
     *  发布时间
     */
    Date publishedAt;

    /***
     *  编辑人
     */
    String userName;
    /***
     *  编辑人id
     */
    String userId;
    /***
     *  编辑人头像
     */
    String userAvatarUrl;
    /***
     * yyyymmdd
     */
    String yyyymmdd;

    /***
     * 服务器时间
     */
    Timestamp serverTime;
    /***
     * 新闻类型
     */
    String newsType;
    /***
     * 新闻来源
     */
    String newsSource;


}
