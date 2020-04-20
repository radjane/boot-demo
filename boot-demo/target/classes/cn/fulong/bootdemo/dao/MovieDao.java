package cn.fulong.bootdemo.dao;


import cn.fulong.bootdemo.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:33
 * @desc 电影dao层
 */
@Component
public interface MovieDao {

    /***
     * 插入豆瓣电影 list
     * @param douBanMovies
     * @return
     */
    boolean insertDouBanMovies(List<DouBanMovie> douBanMovies);

    /***
     * 插入豆瓣电影 top250
     * @param douBanMovieTop250s
     * @return
     */
    boolean insertDouBanMoviesTop250(List<DouBanMovieTop250> douBanMovieTop250s);

    /***
     * 插入豆瓣电影评论 list
     * @param douBanMovieComments
     * @return
     */
    boolean insertDouBanMovieComments(List<DouBanMovieComment> douBanMovieComments);

    /***
     * 插入猫眼电影日票房数据
     * @param boxOffice
     * @return
     */
    boolean insertMaoYanDayBoxOffice(MaoYanDayBoxOffice boxOffice);

    /***
     * 插入猫眼电影日票房影片数据 list
     * @param movies
     * @return
     */
    boolean insertMaoYanDayBoxOfficeMovies(List<MaoYanDayBoxOfficeMovie> movies);

    /***
     * 查询猫眼日票房影片数据  等于某一天
     * @param startYmd
     * @return
     */
    List<MaoYanDayBoxOfficeMovie> selectMaoYanDayBoxOfficeMovieEqYmd(String startYmd);

    /***
     * 查询猫眼日票房数据数据 大于某一天
     * @param startYmd
     * @return
     */
    List<MaoYanDayBoxOffice> selectMaoYanDayBoxOfficesGteYmd(String startYmd);

    /***
     * 查询猫眼日票房数据 等于某一天
     * @param ymd
     * @return
     */
    MaoYanDayBoxOffice selectMaoYanDayBoxOfficeEqYmd(String ymd);

}
