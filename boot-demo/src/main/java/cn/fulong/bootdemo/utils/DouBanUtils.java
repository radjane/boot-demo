package cn.fulong.bootdemo.utils;

import cn.fulong.bootdemo.entity.DouBanMovieComment;
import cn.fulong.bootdemo.entity.DouBanMovieTop250;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:56 下午
 * @desc 豆瓣工具类
 */
public class DouBanUtils {
    /***
     * 分页的参数
     */
    public static final String[] NUMS = {"", "?start=25", "?start=50", "?start=75", "?start=100", "?start=125",
            "?start=150", "?start=175", "?start=200", "?start=225", "?start=250"
    };


    /***
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        DouBanUtils douBanUtils = new DouBanUtils();
        List<DouBanMovieComment> movieComments = new ArrayList<DouBanMovieComment>(0);
        movieComments = douBanUtils.getMovieComment("32659890", 20, null);
        for (DouBanMovieComment movieComment : movieComments) {
            System.out.println(movieComment.toString());
        }

        System.out.println(false && false);

    }


    /***
     * 获取数据
     * @param pageNum
     * @return
     * @throws IOException
     */
    public List<DouBanMovieTop250> getTop250(String pageNum) throws IOException {
        List<DouBanMovieTop250> movieTop250List = new ArrayList<DouBanMovieTop250>(0);
        Document doc = Jsoup.connect("https://movie.douban.com/top250" + pageNum)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .get();
        Elements elements = doc.body().getElementsByClass("grid_view");
        for (Element element : elements) {
            Elements lis = element.getElementsByTag("li");
            for (Element li : lis) {
                //    电影名称
                String movieName = li.getElementsByTag("div").get(0).getElementsByClass("pic").get(0).getElementsByTag("a").get(0).getElementsByTag("img").attr("alt");
                //    封面图片
                String momvieCoverUrl = li.getElementsByTag("div").get(0).getElementsByClass("pic").get(0).getElementsByTag("a").attr("href");
                //    电影评分
                String movieRating = li.getElementsByTag("div").get(0).getElementsByClass("info").get(0).getElementsByClass("bd").get(0).getElementsByTag("div").get(0).getElementsByClass("rating_num").text();
                //    电影评论人数
                String movieRatingNum = li.getElementsByTag("div").get(0).getElementsByClass("info").get(0).getElementsByClass("bd").get(0).getElementsByClass("star").get(0).getElementsByTag("span").get(3).text().replace("人评价", "");
                //    电影推荐语
                String movieRecommendation = li.getElementsByTag("div").get(0).getElementsByClass("info").get(0).getElementsByClass("bd").get(0).getElementsByTag("p").get(1).text();
                //    电影top250排名
                String movieIndex = li.getElementsByTag("div").get(0).getElementsByClass("pic").get(0).getElementsByTag("em").get(0).text();
                //    电影详情页
                String movieSubjectUrl = li.getElementsByTag("div").get(0).getElementsByClass("info").get(0).getElementsByClass("hd").get(0).getElementsByTag("a").attr("href");
                //    电影信息
                String movieContent = li.getElementsByTag("div").get(0).getElementsByClass("bd").get(0).getElementsByTag("p").html();
                //    电影ID
                String movieId = momvieCoverUrl.substring(32).replace("/", "");
                //    电影导演
                String movieDirector = movieContent.split("<br> ")[0].split("&nbsp;&nbsp;&nbsp;主")[0].replaceAll("导演: ", "");
                //    上映年份
                String movieYear = movieContent.split("<br> ")[1].substring(0, 4);
                //    实体类
                DouBanMovieTop250 movieTop250 = new DouBanMovieTop250();
                movieTop250.setMovieDirector(movieDirector);
                movieTop250.setMovieYear(movieYear);
                movieTop250.setMomvieCoverUrl(momvieCoverUrl);
                movieTop250.setMovieId(movieId);
                movieTop250.setMovieIndex(Long.valueOf(movieIndex));
                movieTop250.setMovieName(movieName);
                movieTop250.setMovieRating(movieRating);
                movieTop250.setMovieRatingNum(Long.valueOf(movieRatingNum));
                movieTop250.setMovieRecommendation(movieRecommendation);
                movieTop250.setMovieSubjectUrl(movieSubjectUrl);
                movieTop250List.add(movieTop250);
            }

        }
        //       返回电影数据
        return movieTop250List;
    }


    /***
     *获取电影评论信息数据
     * @param movieId  电影id
     * @param startNum 开始
     * @param sort 类型  默认： new_score（热门评论） time（最新拼了呢）
     * @return
     * @throws IOException
     */
    public List<DouBanMovieComment> getMovieComment(String movieId, int startNum, String sort) throws IOException {
        if (StringUtils.isEmpty(sort)) {
            sort = "new_score";
        }
        //  评论地址
        String url = "https://movie.douban.com/subject/" + movieId + "/comments?start=" + startNum + "&limit=20&sort=" + sort + "&status=P&comments_only=1";
        List<DouBanMovieComment> doubanMovieComments = new ArrayList<>();
        // 获取 请求数据
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String jsonString = httpClientUtils.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String html = jsonObject.get("html").toString();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.body().getElementsByClass("comment-item");
        if (elements.size() > 0) {
            for (Element element : elements) {
                DouBanMovieComment comment = new DouBanMovieComment();
                //   评论id
                String dataCid = element.attr("data-cid");
                //   评论人
                String commentPerson = element.getElementsByTag("div").get(0).getElementsByTag("a").attr("title");
                //   评论内容
                String commentShort = element.getElementsByClass("comment").get(0).getElementsByClass("short").get(0).text();
                //   网友点赞数
                String commentVote = element.getElementsByClass("comment").get(0).getElementsByClass("comment-vote").get(0).getElementsByClass("votes").text();
                //   评分allstar50 rating
                String ifWatched = element.getElementsByClass("comment").get(0).getElementsByTag("span").get(3).text();
                //   评分allstar50 rating
                String rating = element.getElementsByClass("comment").get(0).getElementsByTag("span").get(4).className().substring(7, 8);
                //   评论时间
                String commentTime = element.getElementsByClass("comment").get(0).getElementsByClass("comment-time").get(0).text();
                //数据放到实体类里
                comment.setDataCid(dataCid);
                comment.setMovieId(movieId);
                try {
                    comment.setRating(Integer.valueOf(rating));
                } catch (Exception e) {
//                未打分
                    comment.setIfRating(0);
                }
                if ("想看".equals(ifWatched)) {
                    comment.setIfWatched(0);
                } else {
//                看过
                    comment.setIfWatched(1);
                }
                comment.setCommentPerson(commentPerson);
                comment.setCommentShort(commentShort);
                comment.setCommentTime(commentTime);
                comment.setCommentVote(Integer.valueOf(commentVote));
                doubanMovieComments.add(comment);
            }
        }
        return doubanMovieComments;
    }

}
