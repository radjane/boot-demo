package cn.fulong.bootdemo.service;

import java.util.Map;

/**
 * @Author:GHB
 * @Date:2019-07-26
 *
 */
public interface HeroInfoService {


    /***
     * 获取英雄列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map getHeronIfo(Integer pageNum, Integer pageSize);

    /***
     *
     * @param heroInfoId
     * @return
     */
    Map getHeroInfo(Integer heroInfoId);
}
