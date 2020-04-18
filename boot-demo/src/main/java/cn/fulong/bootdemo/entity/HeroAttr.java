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
public class HeroAttr implements Serializable {

    private String id;
    private String ename;
    private String heroAttrName;
    private String heroAttrNum;
    private Date createTime;


}
