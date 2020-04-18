package cn.fulong.bootdemo.utils;

import cn.fulong.bootdemo.entity.Kr36NewsFlash;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author:GHB
 * @Date:2019-10-03 豆瓣工具类
 */
public class Kr36Utils {


    public static void main(String[] args) throws ParseException {
//        Kr36Utils kr36Utils = new Kr36Utils();
//
//        List<Kr36NewsFlash> flashes = kr36Utils.getKr36NewsFlashs(0, 0);
//        for (Kr36NewsFlash flash : flashes) {
//            System.out.println(flash.toString());
//        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date publishedAt = sdf.parse("2019-10-12 12:00:03");

        String msg = "//    有网友发布动态称：“导航不显示路线，驾驶员发现乘客，乘客也找不到驾驶员” ，有滴滴用户发现，司机已经直接了其他导航软件。滴滴出行官方回应称，今天晚高峰时，滴滴APP内的导航功能出现故障，经技术小哥哥全力修复，目前正在陆续恢复中。(AI财经社）";
        System.out.println(getNewsSource("dzd", msg));
////        Sat Oct 12 12:00:03 CST 2019

//        String title = "。（中国证券网） 原文链接";
//        System.out.println(title.lastIndexOf("（"));
//        System.out.println(title.lastIndexOf("）"));
//        System.out.println(title.substring(title.lastIndexOf("（")).replaceAll("原文链接", ""));

    }


    public List<Kr36NewsFlash> getKr36NewsFlashes(int maxBid, int per_page) {
        String url = "https://36kr.com/pp/api/newsflash?b_id=" + (maxBid + per_page) + "&per_page=" + per_page;
        List<Kr36NewsFlash> kr36NewsFlashes = new ArrayList<>(0);
        HttpClientUtils httpUtils = new HttpClientUtils();
        String jsonString = httpUtils.doGet(url);
        if (!StringUtils.isEmpty(jsonString)) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            String data = HttpClientUtils.getString(jsonObject, "data");
            if (!"".equals(data)) {
                JSONObject jsonData = JSONObject.parseObject(data);
                JSONArray items = JSONObject.parseArray(jsonData.getString("items"));
                if (items.size() > 0) {
                    for (int i = 0; i < items.size(); i++) {
                        JSONObject item = (JSONObject) items.get(i);
                        Kr36NewsFlash kr36NewsFlash = new Kr36NewsFlash();
                        String bIdStr = item.getString("id");
                        int bId = Integer.parseInt(bIdStr);
                        // 如果有重复的数据就跳过
                        if (bId <= maxBid) {
                            break;
                        }
                        String title = item.getString("title");
                        String description = item.getString("description");
                        String newsUrl = item.getString("news_url");
                        String publishedAtStr = item.getString("published_at");
                        //   编辑人
                        String userName = item.getJSONObject("user").getString("name");
                        //   编辑人id
                        String userId = item.getJSONObject("user").getString("id");
                        //   编辑人头像
                        String userAvatarUrl = item.getJSONObject("user").getString("avatar_url");
                        //   新闻来源
                        String newsSource = getNewsSource(newsUrl, description);

                        if (!StringUtils.isBlank(publishedAtStr)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date publishedAt = sdf.parse(publishedAtStr);
                                kr36NewsFlash.setPublishedAt(publishedAt);
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(publishedAt);
                                int year = calendar.get(Calendar.YEAR);
                                int month = calendar.get(Calendar.MONTH) + 1;
                                int day = calendar.get(Calendar.DATE);
                                String monthStr = month > 9 ? (month + "") : ("0" + month);
                                String dayStr = day > 9 ? (day + "") : ("0" + day);
                                String yyyymmdd = year + monthStr + dayStr;
                                kr36NewsFlash.setYyyymmdd(yyyymmdd);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        kr36NewsFlash.setId(bId);
                        kr36NewsFlash.setTitle(title);
                        kr36NewsFlash.setTitleLength(title.length());
                        kr36NewsFlash.setDescription(description);
                        kr36NewsFlash.setNewsUrl(newsUrl);
                        kr36NewsFlash.setUserName(userName);
                        kr36NewsFlash.setUserId(userId);
                        kr36NewsFlash.setUserAvatarUrl(userAvatarUrl);
                        kr36NewsFlash.setServerTime(new Timestamp(System.currentTimeMillis()));
                        kr36NewsFlash.setNewsType(getNewsType(title));
                        kr36NewsFlash.setNewsSource(newsSource);
                        kr36NewsFlashes.add(kr36NewsFlash);
                    }
                }
            }
        }
        httpUtils = null;
        return kr36NewsFlashes;
    }


    public static String getNewsType(String title) {
        String type = "";
        if (title.indexOf("区块链") > -1) {
            type = "今日区块链";
        } else if (title.indexOf("5G") > -1) {
            type = "今日5G";
        } else if (title.indexOf("：") > -1) {
            type = "今日发声";
        } else {
            if (title.indexOf("融资") > -1) {
                type = "今日融资";
            } else {
                type = "今日资讯";
            }
        }
        return type;
    }


    public static String getNewsSource(String newsUrl, String description) {
        String newsSource = "";
        if (!StringUtils.isEmpty(newsUrl) && description.indexOf("36氪获悉") == -1 && description.indexOf("36氪讯") == -1) {
            try {
                int indexStart1 = (description.lastIndexOf("（") + 1);
                int indexStart2 = (description.lastIndexOf("(") + 1);
                int indexEnd1 = description.lastIndexOf("）");
                int indexEnd2 = description.lastIndexOf(")");
                int startIndex = indexStart1 > indexStart2 ? indexStart1 : indexStart2;
                int endIndex = indexEnd1 > indexEnd2 ? indexEnd1 : indexEnd2;
                newsSource = description.substring(startIndex, endIndex);
            } catch (Exception e) {
                newsSource = "网友消息";
            }
        } else {
            if (description.indexOf("36氪获悉") > -1 || description.indexOf("36氪讯") > -1) {
                newsSource = "36氪";
            } else {
                newsSource = "网友消息";
            }
        }
        return newsSource;
    }
}
