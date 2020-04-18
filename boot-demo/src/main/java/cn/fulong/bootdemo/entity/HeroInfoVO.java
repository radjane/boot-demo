package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:GHB
 * @Date:2019-07-08
 *
 */
@Data
public class HeroInfoVO implements Serializable {

    private String ename;
    private String cname;
    private String title;
    private String newType;
    private String heroType;
    private String heroType2;
    private String skinName;
    private String heroAttrName1;
    private Integer heroAttrNum1;
    private String heroAttrName2;
    private Integer heroAttrNum2;
    private String heroAttrName3;
    private Integer heroAttrNum3;
    private String heroAttrName4;
    private Integer heroAttrNum4;


}
