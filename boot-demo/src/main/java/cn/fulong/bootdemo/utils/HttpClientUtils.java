package cn.fulong.bootdemo.utils;


import cn.fulong.bootdemo.entity.DouBanMovie;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GHB
 * @Date:2019-07-08
 */
public class HttpClientUtils {

    private static String maoyan_piaofang = "http://piaofang.maoyan.com/second-box";
    private static String kr36_url = "https://36kr.com/pp/api/newsflash?b_id=20&per_page=10";

    /***
     * 获取豆瓣电影数据
     * @param requestUrl
     * @return
     */
    public List<DouBanMovie> doGetReturnMovies(String requestUrl) {
        List<DouBanMovie> douBanMovies = new ArrayList<>(0);
        String content = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(requestUrl);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                content = EntityUtils.toString(responseEntity);
                JSONArray jsonArray = null;
                System.out.println("响应内容为:" + content);
                if (!StringUtils.isEmpty(content)) {
                    try {
                        jsonArray = JSONObject.parseArray(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (jsonArray.size() > 0) {
                    System.out.println(jsonArray.size() + "部");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                    评分
                        String rating = getString(jsonObject, "rating");
//                    序号
                        String rank = getString(jsonObject, "rank");
//                    封面图片链接
                        String cover_url = getString(jsonObject, "cover_url");
//                    是否可以播放
                        String is_playable = getString(jsonObject, "is_playable");
//                    影片ID
                        String id = getString(jsonObject, "id");
//                    类型
                        String types = getString(jsonObject, "types");
//                    来源
                        String regions = getString(jsonObject, "regions");
//                    影片
                        String title = getString(jsonObject, "title");
//                    详情页
                        String url = getString(jsonObject, "url");
//                    上映时间
                        String release_date = getString(jsonObject, "release_date");
//                    演员个数
                        String actor_count = getString(jsonObject, "actor_count");
//                    投票数
                        String vote_count = getString(jsonObject, "vote_count");
//                    评分
                        String score = getString(jsonObject, "score");
//                    演员列表
                        String actors = getString(jsonObject, "actors");
//                    用户是否看过
                        String is_watched = getString(jsonObject, "is_watched");

//                   System.out.println(rank + "|" + i + "、" + title + "|ID:" + id + "|上映时间:" + release_date);
                        DouBanMovie doubanMovie = new DouBanMovie();
                        doubanMovie.setActorCount(actor_count);
                        doubanMovie.setActors(actors);
                        doubanMovie.setCoverUrl(cover_url);
                        doubanMovie.setMovieId(id);
                        doubanMovie.setIsPlayable(is_playable);
                        doubanMovie.setIsWatched(is_watched);
                        doubanMovie.setRanks(rank);
                        doubanMovie.setRating(rating);
                        doubanMovie.setRegions(regions);
                        doubanMovie.setReleaseDate(release_date);
                        doubanMovie.setScore(score);
                        doubanMovie.setTitle(title);
                        doubanMovie.setTypes(types);
                        doubanMovie.setUrl(url);
                        doubanMovie.setVoteCount(vote_count);
                        douBanMovies.add(doubanMovie);
                    }
//                System.out.println("响应内容为:" + content);
                }
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return douBanMovies;
    }


    public static void main(String[] args) {
        HttpClientUtils clientUtil = new HttpClientUtils();
        String requestUrl = "http://piaofang.maoyan.com/second-box";
        System.out.println(clientUtil.doGet(requestUrl));
//

    }


    /***
     * get 请求
     * @param url  参数拼接在后面，中文及特殊符号注意编码 URLEncoder.encode("我是中文", "utf-8")
     * @return
     */
    public String doGet(String url) {
        //我们可以使用一个Builder来设置UA字段，然后再创建HttpClient对象
        HttpClientBuilder builder = HttpClients.custom();
        //对照UA字串的标准格式理解一下每部分的意思
        //模拟是浏览器
        builder.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        System.out.println("请求的地址为:" + url);
        String content = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = builder.build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(60000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(60000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(60000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                content = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }


    /***
     * json 判空
     * @param jsonObject
     * @param msg
     * @return
     */
    public static String getString(JSONObject jsonObject, String msg) {
        if (jsonObject != null && jsonObject.get(msg) != null) {
            return jsonObject.get(msg).toString();
        }
        return "";
    }


}