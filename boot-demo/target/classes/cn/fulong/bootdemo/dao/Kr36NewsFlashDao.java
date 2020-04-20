package cn.fulong.bootdemo.dao;

import cn.fulong.bootdemo.entity.Kr36NewsFlash;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author black猫
 * @date   2020/4/11
 * @desc   36Kr新闻dao层
 */
@Component
public interface Kr36NewsFlashDao {

    /****
     * 批量插入新闻快讯
     * @param newsFlashes
     * @return
     */
    boolean insertKr36NewsFlashes(List<Kr36NewsFlash> newsFlashes);

    List<Kr36NewsFlash> selectKr36NewsFlashes1(String startYmd);

    List<Kr36NewsFlash> selectKr36NewsFlashes2(String startYmd);

    List<Kr36NewsFlash> selectKr36NewsFlashes3(String startYmd,String endYmd);

    List<Kr36NewsFlash> selectKr36NewsFlashes4(String startYmd);

    int selectMaxBid();

    int selectMinBid();

    boolean updateKr36NewsFlashes(Kr36NewsFlash kr36NewsFlash);
}
