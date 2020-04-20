package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:GHB
 * @Date:2019-07-08
 *
 */
@Data
public class HeroInfo implements Serializable {

    private String id;
    private String ename;
    private String cname;
    private String title;
    private String newType;
    private String heroType;
    private String heroType2;
    private String skinName;
    private Date   createTime;


}
