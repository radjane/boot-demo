package cn.fulong.bootdemo.service.impl.game;


import cn.fulong.bootdemo.dao.HeroInfoDao;
import cn.fulong.bootdemo.entity.HeroInfo;
import cn.fulong.bootdemo.service.HeroInfoService;
import cn.fulong.bootdemo.utils.MyPageHelper;
import cn.fulong.bootdemo.utils.PageUtils;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:GHB
 * @Date:2019-07-18
 *
 */
@Service
public class HeroInfoServiceImpl  implements HeroInfoService {

    
    @Autowired
    private HeroInfoDao heroInfoDao;
    


    @Override
    public Map getHeronIfo(Integer pageNum, Integer pageSize) {
        Map data=new HashMap(0);

        ISelect iSelect =new ISelect() {
            @Override
            public void doSelect() {
              heroInfoDao.selectHeroInfos();
            }
        };

        Long totalCount=  PageHelper.count(iSelect);
        MyPageHelper pageHelper= PageUtils.getMyPageHelper(pageNum,pageSize,totalCount.intValue());

        // 分页下面 跟查询语句
        PageHelper.startPage(pageNum,pageSize);
        List<HeroInfo> heros=heroInfoDao.selectHeroInfos();

        data.put("heros",heros);
        data.put("pageHelper",pageHelper);

        return data;
    }

    @Override
    public Map getHeroInfo(Integer heroInfoId) {
        Map data=new HashMap(0);

        HeroInfo heroInfo= heroInfoDao.selectHeroInfoById(heroInfoId);
        data.put("heroInfo",heroInfo);
        return data;
    }
}
