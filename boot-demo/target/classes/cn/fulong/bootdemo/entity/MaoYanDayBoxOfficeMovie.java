package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:36 上午
 * @desc 猫眼日票房数据电影
 */
@Data
public class MaoYanDayBoxOfficeMovie {

    /***
     * id
     */
    int id;
    /***
     * 平均上座率
     */
    String avgSeatView;
    /***
     * 平均上座人次
     */
    String avgShowView;
    /***
     * 综合票价
     */
    String avgViewBox;
    /***
     * 今日票房
     */
    double boxInfo;
    /***
     * 今日票房占比
     */
    String boxRate;
    /***
     * 里程碑票房数据
     */
    long milestoneBox;
    /***
     * 里程碑票房数据 描述
     */
    String milestoneBoxCopy;
    /***
     * 里程碑票房数据 时间
     */
    Timestamp milestoneDateTime;
    /***
     * 里程碑票房数据 时间
     */
    Timestamp milestoneExpireTimestamp;
    /***
     * 里程碑票房数据 图片
     */
    String milestoneImageUrl;
    /***
     * 里程碑票房数据 影片id
     */
    String milestoneMovieId;
    /***
     * 影片id
     */
    String movieId;
    /***
     * 影片名称
     */
    String movieName;
    /***
     * 猫眼退票人次
     */
    String myRefundNumInfo;
    /***
     * 猫眼退票率
     */
    String myRefundRateInfo;
    /***
     * 网售占比
     */
    String onlineBoxRate;
    /***
     * 大盘退票人次
     */
    String refundViewInfo;
    /***
     * 大盘退票占比
     */
    String refundViewRate;
    /***
     * 影片上映天数
     */
    String releaseInfo;
    /***
     * 影片上映天数 展示颜色
     */
    String releaseInfoColor;
    /***
     * 排座占比
     */
    String seatRate;
    /***
     * 排片场次
     */
    double showInfo;
    /***
     * 排片占比
     */
    String showRate;
    /***
     * 分账平均票价
     */
    String splitAvgViewBox;
    /***
     * 分账票房数
     */
    double splitBoxInfo;
    /***
     * 分账票房占比
     */
    String splitBoxRate;
    /***
     * 电影分账总票房
     */
    String splitSumBoxInfo;
    /***
     * 电影总票房
     */
    String sumBoxInfo;
    /***
     * 人次
     */
    double viewInfo;
    /***
     * 人次包含单位
     */
    String viewInfoV2;
    /***
     * 年月日
     */
    String yyyymmdd;
    /***
     * 统计时间
     */
    Timestamp serverTime;


}
