package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:36 上午
 * @desc 猫眼日票房数据
 */
@Data
public class MaoYanDayBoxOffice {

    /***
     * id
     */
    int id;
    /***
     * 更新时间
     */
    String updateInfo;
    /***
     * 票房数据单位
     */
    String totalBoxUnitInfo;
    /***
     * 分账总票房
     */
    double splitTotalBox;
    /***
     * 更新时间 long
     */
    Timestamp serverTimestamp;
    /***
     * 猫眼电影票
     */
    double crystalMaoyanViewInfo;
    /***
     * 大盘总的电影票
     */
    double crystalViewInfo;
    /***
     * 电影票单位
     */
    String crystalViewUnitInfo;
    /***
     * 综合总票房
     */
    double totalBoxInfo;
    /***
     * 综合总票房单位
     */
    String totalBoxUnit;
    /***
     * 综合总票房
     */
    String totalBox;
    /***
     * 综合总票房 单位
     */
    String splitTotalBoxUnit;
    /***
     * 查询的日期
     */
    String queryDate;
    /***
     * 服务器时间
     */
    String serverTime;
    /***
     * 分账票房单位
     */
    String splitTotalBoxUnitInfo;
    /***
     * 分账票房
     */
    String splitTotalBoxInfo;
    /***
     * 年月日
     */
    String yyyymmdd;


}
