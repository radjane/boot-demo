package cn.fulong.bootdemo.service.impl.news;

import cn.fulong.bootdemo.dao.Kr36NewsFlashDao;
import cn.fulong.bootdemo.entity.Kr36NewsFlash;
import cn.fulong.bootdemo.service.Kr36NewsFlashService;
import cn.fulong.bootdemo.utils.DateUtils;
import cn.fulong.bootdemo.utils.Kr36Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @Author:GHB
 * @Date:2019-07-03
 */
@Service
public class Kr36NewsFlashServiceImpl implements Kr36NewsFlashService {

    @Autowired
    private Kr36NewsFlashDao kr36NewsFlashDao;

    @Override
    public String save36KrNewsFlash(Integer size) {
        Kr36Utils kr36Utils = new Kr36Utils();
        StringBuffer msg = new StringBuffer("");
        /***
         * 默认300条
         */
        if (size == null) {
            size = 300;
        }
        int maxBid = kr36NewsFlashDao.selectMaxBid();
        List<Kr36NewsFlash> newsFlashes = kr36Utils.getKr36NewsFlashes(maxBid, size);
        int length = newsFlashes.size();
        if (length > 0) {
            boolean flag = kr36NewsFlashDao.insertKr36NewsFlashes(newsFlashes);
            if (!flag) {
                msg.append("采集出错了。");
            } else {
                msg.append("采集了" + length + "条快讯。");
            }
        } else {
            msg.append("数据还没更新。");
        }

        return msg.toString();
    }

    @Override
    public List<Kr36NewsFlash> getKr36NewsFlashes1(String startYmd) {
        /***
         * 默认获取昨天的数据
         */
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        return kr36NewsFlashDao.selectKr36NewsFlashes1(startYmd);
    }

    @Override
    public List<Kr36NewsFlash> getKr36NewsFlashes2(String startYmd) {
        /***
         * 默认获取昨天的数据
         */
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        return kr36NewsFlashDao.selectKr36NewsFlashes2(startYmd);
    }

    @Override
    public List<Kr36NewsFlash> getKr36NewsFlashes3(String startYmd, String endYmd) {
        /***
         * 默认获取昨天的数据
         */
        if (StringUtils.isEmpty(startYmd) || StringUtils.isEmpty(endYmd)) {
            startYmd = DateUtils.getYmd(0);
            return kr36NewsFlashDao.selectKr36NewsFlashes2(startYmd);
        }
        return kr36NewsFlashDao.selectKr36NewsFlashes3(startYmd, endYmd);
    }

    @Override
    public String update36KrNewsFlash(String yyyymmdd) {
        List<Kr36NewsFlash> kr36NewsFlashes = kr36NewsFlashDao.selectKr36NewsFlashes4(yyyymmdd);
        StringBuilder msg = new StringBuilder("");
        if (!CollectionUtils.isEmpty(kr36NewsFlashes)) {
            for (int i = 0; i < kr36NewsFlashes.size(); i++) {

                Kr36NewsFlash kr36NewsFlash = new Kr36NewsFlash();

                kr36NewsFlash = kr36NewsFlashes.get(i);
                yyyymmdd = kr36NewsFlash.getYyyymmdd();

                String description = kr36NewsFlash.getDescription();
                String newsUrl = kr36NewsFlash.getNewsUrl();
                String newsSource = Kr36Utils.getNewsSource(newsUrl, description);

                String title = kr36NewsFlash.getTitle();
                String newsType = Kr36Utils.getNewsType(title);

                kr36NewsFlash.setNewsType(newsType);
                kr36NewsFlash.setNewsSource(newsSource);
                kr36NewsFlash.setNewsUrl(newsUrl);
                kr36NewsFlash.setYyyymmdd(yyyymmdd);
                boolean flag = kr36NewsFlashDao.updateKr36NewsFlashes(kr36NewsFlash);
                if (flag) {
                    msg.append(yyyymmdd + ":更新成功。");
                } else {
                    msg.append(yyyymmdd + ":更新失败。");
                }
            }
        }
        return msg.toString();
    }


    /***
     * 整点10分统计
     * 更新最近的300条消息 不一定是300条
     */
    @Scheduled(cron = "0 10 * * * ?")
    public void saveKr36NewsFlashEveryDay() {
        /***
         * 默认300条
         */
        String msg = save36KrNewsFlash(300);
        System.out.println("saveKr36NewsFlashEveryDay：" + msg);
    }

}
