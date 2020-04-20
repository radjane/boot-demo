package cn.fulong.bootdemo.utils;

import cn.fulong.bootdemo.entity.Kr36NewsFlash;
import cn.fulong.bootdemo.vo.Kr36Param;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:GHB
 * @Date:2019-10-03 豆瓣工具类
 */
public class Kr36Utils {

    @Autowired

    public static void main(String[] args) throws ParseException {
//        Kr36Utils kr36Utils = new Kr36Utils();
        String url = "https://gateway.36kr.com/api/mis/nav/newsflash/flow";

        System.out.println(System.currentTimeMillis());
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以什么方式提交，自行选择，一般使用json，或者表单
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map params=new HashMap(0);
        Kr36Param param = new Kr36Param();
        param.setPageSize(30);
        param.setPageEvent(0);
        param.setPageCallback("eyJmaXJzdElkIjo2NzIxNTY5OTc0NzQzMTEsImxhc3RJZCI6NjcxNjI3NzM5MjQwNzA0LCJmaXJzdENyZWF0ZVRpbWUiOjE1ODcyOTcyMDczNjUsImxhc3RDcmVhdGVUaW1lIjoxNTg3MjY0OTA0MDA2fQ");
        param.setPlatformId(2);
        param.setSiteId(1);
        params.put("param",JSONObject.toJSONString(param));
        params.put("timestamp", System.currentTimeMillis());
        params.put("partner_id","web");


String par="        { \t\"partner_id\": \"web\", \t\"timestamp\": 1587304987324, \t\"param\": { \t\t\"pageSize\": 3000, \t\t\"pageEvent\": 0, \t\t\"pageCallback\": \"eyJmaXJzdElkIjo2NzIxNTY5OTc0NzQzMTEsImxhc3RJZCI6NjcxNjI3NzM5MjQwNzA0LCJmaXJzdENyZWF0ZVRpbWUiOjE1ODcyOTcyMDczNjUsImxhc3RDcmVhdGVUaW1lIjoxNTg3MjY0OTA0MDA2fQ\", \t\t\"siteId\": 1, \t\t\"platformId\": 2 \t} }\n";
        //将请求头部和参数合成一个请求
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        System.out.println(requestEntity.toString());
        //执行HTTP请求，将返回的结构使用Response类格式化
        ResponseEntity<Response> response = client.exchange(url, method, requestEntity, Response.class);

        System.out.println( response.getBody());

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


    public String getKr36NewsFlashesNew(int pageSize) {

        return "";
    }
}
