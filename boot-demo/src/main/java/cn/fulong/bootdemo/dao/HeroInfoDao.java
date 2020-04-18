package cn.fulong.bootdemo.dao;


import cn.fulong.bootdemo.entity.HeroInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:GHB
 * @Date:2019-07-18
 *
 */
@Component
public interface HeroInfoDao {

    /***
     * 查询英雄列表信息
     * @return
     */
    List<HeroInfo> selectHeroInfos();

    /***
     *
     * @param heroInfoId
     * @return
     */
    HeroInfo selectHeroInfoById(Integer heroInfoId);

}
