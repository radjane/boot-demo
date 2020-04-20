package cn.fulong.bootdemo.utils;

import java.util.UUID;
/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:07 上午
 * @desc 获取uuid的工具类
 */
public class UuidUtils {

    /***
     *
     * @param length 小于32
     * @return
     */
    public static String getUuid(int length) {
        if (length > 32) {
            length = 32;
        }
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }


}
