package cn.fulong.bootdemo.utils;

import cn.fulong.bootdemo.entity.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:33 上午
 * @desc 猫眼票房实时数据
 */
public class MaoYanUtils {

    private static String mao_yan_day_box_office_URL = "http://piaofang.maoyan.com/second-box?beginDate=";


    public static void main(String[] args) {
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String jsonString = httpClientUtils.doGet(mao_yan_day_box_office_URL );
        System.out.println(jsonString);
    }

    /***
     * 获取猫眼票房数据
     * @param yyyymmdd
     * @return
     */
    public MaoYanDayBoxOfficeInfo getBoxOfficeData(String yyyymmdd) {
        // 票房数据
        MaoYanDayBoxOfficeInfo info = new MaoYanDayBoxOfficeInfo();

        List<MaoYanDayBoxOfficeMovie> maoYanDayBoxOfficeMovies = new ArrayList<MaoYanDayBoxOfficeMovie>(0);

        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String jsonString = httpClientUtils.doGet(mao_yan_day_box_office_URL + yyyymmdd);
        if (!StringUtils.isEmpty(jsonString) && !"404".equals(jsonString)) {
            MaoYanDayBoxOffice maoYanDayBoxOffice = new MaoYanDayBoxOffice();
            //
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            String success = HttpClientUtils.getString(jsonObject, "success");
            if ("true".equals(success)) {
                String dataJson = HttpClientUtils.getString(jsonObject, "data");
                JSONObject data = JSONObject.parseObject(dataJson);
                // 更新时间
                String updateInfo = HttpClientUtils.getString(data, "updateInfo");
                //  票房数据单位
                String totalBoxUnitInfo = HttpClientUtils.getString(data, "totalBoxUnitInfo");
                // 分账总票房
                double splitTotalBox = Double.valueOf(HttpClientUtils.getString(data, "splitTotalBox"));
                String serverTimestampStr = HttpClientUtils.getString(data, "serverTimestamp");
                // 更新时间 long
                Timestamp serverTimestamp = new Timestamp(new Long(serverTimestampStr));
                JSONObject crystal = data.getJSONObject("crystal");
                if (crystal != null) {
                    String maoyanViewInfo = HttpClientUtils.getString(crystal, "maoyanViewInfo");
                    if (!StringUtils.isEmpty(maoyanViewInfo)) {
                        // 猫眼电影票
                        double crystalMaoyanViewInfo = Double.valueOf(maoyanViewInfo);
                        maoYanDayBoxOffice.setCrystalMaoyanViewInfo(crystalMaoyanViewInfo);
                    }
                    String viewInfo = HttpClientUtils.getString(crystal, "viewInfo");
                    if (!StringUtils.isEmpty(maoyanViewInfo)) {
                        // 大盘总的电影票
                        double crystalViewInfo = Double.valueOf(viewInfo);
                        maoYanDayBoxOffice.setCrystalViewInfo(crystalViewInfo);
                    }
                    //  电影票单位
                    String crystalViewUnitInfo = HttpClientUtils.getString(crystal, "viewUnitInfo");
                    maoYanDayBoxOffice.setCrystalViewUnitInfo(crystalViewUnitInfo);
                }

                String totalBoxInfoStr = HttpClientUtils.getString(data, "totalBoxInfo");
                if (!StringUtils.isEmpty(totalBoxInfoStr)) {
                    //  综合总票房
                    double totalBoxInfo = Double.valueOf(totalBoxInfoStr);
                    maoYanDayBoxOffice.setTotalBoxInfo(totalBoxInfo);
                }

                //  综合总票房单位
                String totalBoxUnit = HttpClientUtils.getString(data, "totalBoxUnit");
                //  综合总票房
                String totalBox = HttpClientUtils.getString(data, "totalBox");
                //   综合总票房 单位
                String splitTotalBoxUnit = HttpClientUtils.getString(data, "splitTotalBoxUnit");
                //  查询的日期
                String queryDate = HttpClientUtils.getString(data, "queryDate");
                //  服务器时间
                String serverTime = HttpClientUtils.getString(data, "serverTime");
                //  分账票房单位
                String splitTotalBoxUnitInfo = HttpClientUtils.getString(data, "splitTotalBoxUnitInfo");
                //  分账票房
                String splitTotalBoxInfo = HttpClientUtils.getString(data, "splitTotalBoxInfo");

                maoYanDayBoxOffice.setYyyymmdd(yyyymmdd);
                maoYanDayBoxOffice.setQueryDate(queryDate);
                maoYanDayBoxOffice.setServerTime(serverTime);
                maoYanDayBoxOffice.setServerTimestamp(serverTimestamp);
                maoYanDayBoxOffice.setSplitTotalBox(splitTotalBox);
                maoYanDayBoxOffice.setSplitTotalBoxInfo(splitTotalBoxInfo);
                maoYanDayBoxOffice.setSplitTotalBoxUnit(splitTotalBoxUnit);
                maoYanDayBoxOffice.setSplitTotalBoxUnitInfo(splitTotalBoxUnitInfo);
                maoYanDayBoxOffice.setTotalBox(totalBox);
                maoYanDayBoxOffice.setTotalBoxUnit(totalBoxUnit);
                maoYanDayBoxOffice.setTotalBoxUnitInfo(totalBoxUnitInfo);
                maoYanDayBoxOffice.setUpdateInfo(updateInfo);

                //list数据
                JSONArray jsonArray = data.getJSONArray("list");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject listData = (JSONObject) jsonArray.get(i);
                        MaoYanDayBoxOfficeMovie piaoFangMovie = new MaoYanDayBoxOfficeMovie();
                        piaoFangMovie.setYyyymmdd(yyyymmdd);
                        // 平均上座率
                        String avgSeatView = HttpClientUtils.getString(listData, "avgSeatView");
                        // 平均上座人次
                        String avgShowView = HttpClientUtils.getString(listData, "avgShowView");
                        //  综合票价
                        String avgViewBox = HttpClientUtils.getString(listData, "avgViewBox");
                        // 今日票房
                        double boxInfo = Double.valueOf(HttpClientUtils.getString(listData, "boxInfo"));
                        // 今日票房占比
                        String boxRate = HttpClientUtils.getString(listData, "boxRate");
                        // 里程碑
                        JSONObject milestone = listData.getJSONObject("milestone");
                        if (milestone != null) {
                            String boxStr = HttpClientUtils.getString(milestone, "box");
                            if (!StringUtils.isEmpty(boxStr)) {
                                //  里程碑票房数据
                                long milestoneBox = new Long(boxStr);
                                piaoFangMovie.setMilestoneBox(milestoneBox);
                            }

                            //  里程碑票房数据 描述
                            String milestoneBoxCopy = HttpClientUtils.getString(milestone, "boxCopy");
                            //  里程碑票房数据 时间
                            String milestoneDateTimeStr = HttpClientUtils.getString(milestone, "dateTime");
                            if (!StringUtils.isEmpty(milestoneDateTimeStr)) {
                                Timestamp milestoneDateTime = new Timestamp(new Long(milestoneDateTimeStr));
                                piaoFangMovie.setMilestoneDateTime(milestoneDateTime);
                            }
                            //  里程碑票房数据 时间
                            String milestoneExpireTimestampStr = HttpClientUtils.getString(milestone, "expireTimestamp");
                            if (!StringUtils.isEmpty(milestoneExpireTimestampStr)) {
                                Timestamp milestoneExpireTimestamp = new Timestamp(new Long(milestoneExpireTimestampStr));
                                piaoFangMovie.setMilestoneExpireTimestamp(milestoneExpireTimestamp);
                            }
                            //  里程碑票房数据 图片
                            String milestoneImageUrl = HttpClientUtils.getString(milestone, "imageUrl");
                            //  里程碑票房数据 影片id
                            String milestoneMovieId = HttpClientUtils.getString(milestone, "movieId");
                            piaoFangMovie.setMilestoneBoxCopy(milestoneBoxCopy);
                            piaoFangMovie.setMilestoneImageUrl(milestoneImageUrl);
                            piaoFangMovie.setMilestoneMovieId(milestoneMovieId);
                        }

                        // 影片id
                        String movieId = HttpClientUtils.getString(listData, "movieId");
                        // 影片名称
                        String movieName = HttpClientUtils.getString(listData, "movieName");
                        // 猫眼退票人次
                        String myRefundNumInfo = HttpClientUtils.getString(listData, "myRefundNumInfo");
                        // 猫眼退票率
                        String myRefundRateInfo = HttpClientUtils.getString(listData, "onlineBoxRate");
                        // 网售占比
                        String onlineBoxRate = HttpClientUtils.getString(listData, "onlineBoxRate");
                        // 大盘退票人次
                        String refundViewInfo = HttpClientUtils.getString(listData, "refundViewInfo");
                        // 大盘退票占比
                        String refundViewRate = HttpClientUtils.getString(listData, "refundViewRate");
                        // 影片上映天数
                        String releaseInfo = HttpClientUtils.getString(listData, "releaseInfo");
                        // 影片上映天数 展示颜色
                        String releaseInfoColor = HttpClientUtils.getString(listData, "releaseInfoColor");
                        // 排座占比
                        String seatRate = HttpClientUtils.getString(listData, "seatRate");
                        String showInfoStr = HttpClientUtils.getString(listData, "showInfo");
                        if (!StringUtils.isEmpty(showInfoStr)) {
                            // 排片场次
                            double showInfo = Double.valueOf(showInfoStr);
                            piaoFangMovie.setShowInfo(showInfo);
                        }

                        // 排片占比
                        String showRate = HttpClientUtils.getString(listData, "showRate");
                        // 分账平均票价
                        String splitAvgViewBox = HttpClientUtils.getString(listData, "splitAvgViewBox");

                        String splitBoxInfoStr = HttpClientUtils.getString(listData, "splitBoxInfo");
                        if (!StringUtils.isEmpty(splitBoxInfoStr)) {
                            // 分账票房数
                            double splitBoxInfo = Double.valueOf(splitBoxInfoStr);
                            piaoFangMovie.setSplitBoxInfo(splitBoxInfo);

                        }

                        // 分账票房占比
                        String splitBoxRate = HttpClientUtils.getString(listData, "splitBoxRate");
                        // 电影分账总票房
                        String splitSumBoxInfo = HttpClientUtils.getString(listData, "splitSumBoxInfo");
                        // 电影总票房
                        String sumBoxInfo = HttpClientUtils.getString(listData, "sumBoxInfo");

                        String viewInfoStr = HttpClientUtils.getString(listData, "viewInfo");
                        if (!StringUtils.isEmpty(viewInfoStr)) {
                            // 人次
                            double viewInfo = Double.valueOf(viewInfoStr);
                            piaoFangMovie.setViewInfo(viewInfo);

                        }


                        //  人次包含单位
                        String viewInfoV2 = HttpClientUtils.getString(listData, "viewInfoV2");

                        piaoFangMovie.setAvgSeatView(avgSeatView);
                        piaoFangMovie.setAvgShowView(avgShowView);
                        piaoFangMovie.setAvgViewBox(avgViewBox);
                        piaoFangMovie.setBoxInfo(boxInfo);
                        piaoFangMovie.setBoxRate(boxRate);
                        piaoFangMovie.setMovieId(movieId);
                        piaoFangMovie.setMovieName(movieName);
                        piaoFangMovie.setMyRefundNumInfo(myRefundNumInfo);
                        piaoFangMovie.setMyRefundRateInfo(myRefundRateInfo);
                        piaoFangMovie.setOnlineBoxRate(onlineBoxRate);
                        piaoFangMovie.setRefundViewInfo(refundViewInfo);
                        piaoFangMovie.setRefundViewRate(refundViewRate);
                        piaoFangMovie.setReleaseInfo(releaseInfo);
                        piaoFangMovie.setReleaseInfoColor(releaseInfoColor);
                        piaoFangMovie.setSeatRate(seatRate);
                        piaoFangMovie.setShowRate(showRate);
                        piaoFangMovie.setSplitAvgViewBox(splitAvgViewBox);
                        piaoFangMovie.setSplitBoxRate(splitBoxRate);
                        piaoFangMovie.setSplitSumBoxInfo(splitSumBoxInfo);
                        piaoFangMovie.setSumBoxInfo(sumBoxInfo);
                        piaoFangMovie.setViewInfoV2(viewInfoV2);
                        piaoFangMovie.setServerTime(new Timestamp(System.currentTimeMillis()));
                        maoYanDayBoxOfficeMovies.add(piaoFangMovie);

                    }
                }
                info.setMaoYanDayBoxOffice(maoYanDayBoxOffice);
                info.setMaoYanDayBoxOfficeMovie(maoYanDayBoxOfficeMovies);
            }
        }

        return info;
    }

}
