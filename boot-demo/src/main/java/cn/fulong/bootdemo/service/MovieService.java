package cn.fulong.bootdemo.service;

import cn.fulong.bootdemo.entity.MaoYanDayBoxOffice;
import cn.fulong.bootdemo.entity.MaoYanDayBoxOfficeMovie;

import java.util.List;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:53 下午
 * @desc 电影的接口类
 */
public interface MovieService {

    boolean saveDouBanAllMovies(String requestUrl);

    boolean saveDouBanTop250Movies();

    boolean saveDouBanMoviesComment(String movieId, int start, int size);

    boolean saveDouBanTop250MovieComments();

    String saveMaoYanDayBoxOffice(String yyyymmdd, String yyyymm, String year);

    List<MaoYanDayBoxOfficeMovie> getMaoYanDayBoxOfficeMovies(String startYmd);

    List<MaoYanDayBoxOffice> getMaoYanDayBoxOffice(Integer days);

    MaoYanDayBoxOffice getMaoYanDayBoxOffice(String startYmd);

}
