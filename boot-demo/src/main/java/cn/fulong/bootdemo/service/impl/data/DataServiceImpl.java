package cn.fulong.bootdemo.service.impl.data;

import cn.fulong.bootdemo.dao.MovieDao;
import cn.fulong.bootdemo.entity.HeroAttr;
import cn.fulong.bootdemo.entity.HeroInfo;
import cn.fulong.bootdemo.entity.HeroInfoVO;
import cn.fulong.bootdemo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GHB
 * @Date:2019-07-07
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private MovieDao movieDao;


    @Override
    public List<HeroInfo> getHeroInfo() {
        List list = jdbcTemplate.query(" select * from HERO_INFO ", new BeanPropertyRowMapper<>(HeroInfo.class));
        return list;
    }

    @Override
    public List<HeroInfoVO> getHeroInfoView() {
        List<HeroInfoVO> list = new ArrayList<>();
        List<HeroInfo> listHero = jdbcTemplate.query(" select * from HERO_INFO ", new BeanPropertyRowMapper<>(HeroInfo.class));
        for (HeroInfo hero : listHero) {
            List<HeroAttr> listHeroAttr = jdbcTemplate.query(" select * from HERO_ATTR t where t.ename=? ", new BeanPropertyRowMapper<>(HeroAttr.class), hero.getEname());
            HeroInfoVO heroInfoVO = new HeroInfoVO();
            heroInfoVO.setCname(hero.getCname());
            heroInfoVO.setEname(hero.getEname());
            heroInfoVO.setHeroType(hero.getHeroType());
            heroInfoVO.setHeroType2(hero.getHeroType2());
            heroInfoVO.setNewType(hero.getNewType());
            heroInfoVO.setSkinName(hero.getSkinName());
            heroInfoVO.setTitle(hero.getTitle());
            for (HeroAttr attr : listHeroAttr) {
                if (hero.getEname().equals(attr.getEname())) {
                    String attrName = attr.getHeroAttrName();
                    if ("上手难度".equals(attrName)) {
                        heroInfoVO.setHeroAttrName1(attr.getHeroAttrName());
                        heroInfoVO.setHeroAttrNum1(Integer.parseInt(attr.getHeroAttrNum()));
                    }
                    if ("生存能力".equals(attrName)) {
                        heroInfoVO.setHeroAttrName2(attr.getHeroAttrName());
                        heroInfoVO.setHeroAttrNum2(Integer.parseInt(attr.getHeroAttrNum()));
                    }
                    if ("攻击伤害".equals(attrName)) {
                        heroInfoVO.setHeroAttrName3(attr.getHeroAttrName());
                        heroInfoVO.setHeroAttrNum3(Integer.parseInt(attr.getHeroAttrNum()));
                    }
                    if ("技能效果".equals(attrName)) {
                        heroInfoVO.setHeroAttrName4(attr.getHeroAttrName());
                        heroInfoVO.setHeroAttrNum4(Integer.parseInt(attr.getHeroAttrNum()));
                    }
                }
            }
            list.add(heroInfoVO);
        }
        return list;
    }




}
