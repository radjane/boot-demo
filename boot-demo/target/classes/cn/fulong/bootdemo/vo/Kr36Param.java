package cn.fulong.bootdemo.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author black猫
 * @date 2020/4/19
 * @time 11:19 下午
 * @desc 36氪参数
 */
@Data
public class Kr36Param {
    private int pageSize;
    private int pageEvent;
    private String pageCallback;
    private int siteId;
    private int platformId;


}
