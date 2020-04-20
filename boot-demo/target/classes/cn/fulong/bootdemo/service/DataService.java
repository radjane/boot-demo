package cn.fulong.bootdemo.service;

import cn.fulong.bootdemo.entity.HeroInfo;
import cn.fulong.bootdemo.entity.HeroInfoVO;

import java.util.List;

/**
 * @Author:GHB
 * @Date:2019-07-07
 */
public interface DataService {
    List<HeroInfo> getHeroInfo();

    List<HeroInfoVO> getHeroInfoView();

}
