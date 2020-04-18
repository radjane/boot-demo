package cn.fulong.bootdemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class BiyovMovieUtils {

    @Autowired
    private  WebClient webClient;


    private static String AJAX_URL="https://watch.biyov.com/wp-admin/admin-ajax.php";

    public static void main(String[] args) {
        try {
//            Document doc = getDocument("https://watch.biyov.com/genre/action-adventure/page/1");
//            Elements elements = doc.getElementsByTag("article");
//            for (Element ele : elements) {
//                Elements article = ele.getElementsByTag("article");
//                Elements posterDivs = article.get(0).getElementsByClass("poster");
//                for (Element posterDiv : posterDivs) {
//                    // 影片图片 和 影片标题
//                    Element element = posterDiv.getElementsByTag("noscript").first().getElementsByTag("img").first();
//                    // moviePicUrl
//                    String moviePicUrl = element.attr("src");
//                    // movieTitle
//                    String movieTitle = element.attr("alt");
//                    String movieChineseTitle, movieEnlishTitle;
//                    if (movieTitle.indexOf(".") > -1) {
//                        movieChineseTitle = movieTitle.split("\\.")[0];
//                        movieEnlishTitle = movieTitle.split("\\.")[1];
//                    } else {
//                        movieChineseTitle = movieTitle;
//                        movieEnlishTitle = movieTitle;
//                    }
//
//                    Element dataDiv = posterDiv.nextElementSibling().getElementsByClass("data").first();
//                    // 上映时间
//                    String showTime = dataDiv.getElementsByTag("span").first().text();
//
//                    Element mepoDiv = posterDiv.getElementsByClass("mepo").first();
//                    String movieQuality = "";
//                    if (mepoDiv != null && mepoDiv.getElementsByClass("quality") != null) {
//                        // 电影分辨率
//                        movieQuality = mepoDiv.getElementsByClass("quality").text();
//                    }
//
//                    // 真正的播放地址
//                    String realShowUrl = posterDiv.getElementsByTag("a").first().attr("href");
//
//
//                    Element dtinfoDiv = dataDiv.nextElementSibling().getElementsByClass("dtinfo").first();
//                    Element metadataDiv = dtinfoDiv.getElementsByClass("metadata").first();
//                    Elements spans = metadataDiv.getElementsByTag("span");
//                    String movieImdb = "";
//                    String movieTime = "";
//                    String showYear = "";
//                    String movieVisit = "";
//
//                    if (spans.eq(0) != null) {
//                        //  imdb 评分
//                        movieImdb = metadataDiv.getElementsByTag("span").eq(0).text();
//                    }
//                    if (spans.eq(1) != null) {
//                        //  上映年份
//                        showYear = metadataDiv.getElementsByTag("span").eq(1).text();
//                    }
//                    if (spans.eq(2) != null) {
//                        //  电影时长
//                        movieTime = metadataDiv.getElementsByTag("span").eq(2).text();
//                    }
//                    if (spans.eq(3) != null) {
//                        //  浏览次数
//                        movieVisit = metadataDiv.getElementsByTag("span").eq(3).text();
//                    }
//
//                    Element textoDiv = dtinfoDiv.getElementsByClass("texto").first();
//                    //  电影简介
//                    String movieText ="";
//                    if(textoDiv!=null){
//                        movieText = textoDiv.text();
//                    }
//                    Elements mtaAs = dtinfoDiv.getElementsByClass("genres").first().getElementsByClass("mta").first().getElementsByTag("a");
//
//                    StringBuilder movieTypeSb = new StringBuilder("");
//                    for (Element mtaA : mtaAs) {
//                        movieTypeSb.append(mtaA.text());
//                        movieTypeSb.append("|");
//                    }
//                    String movieType = movieTypeSb.substring(0, movieTypeSb.length() - 1);
//
//                    System.out.println(moviePicUrl);
//                    System.out.println(movieChineseTitle);
//                    System.out.println(movieEnlishTitle);
//                    System.out.println(showTime);
//                    System.out.println(movieImdb);
//                    System.out.println(showYear);
//                    System.out.println(movieTime);
//                    System.out.println(movieVisit);
//                    System.out.println(movieText);
//                    System.out.println(movieType);
//                    System.out.println(movieQuality);
//                    System.out.println(realShowUrl);
//                    System.out.println("------------------------------------------------\n");
//
//
//                    // 详情页
//                    Document movieDoc=   getDocument(realShowUrl);

//                    // 如果是 剧集
//                    if("tvshows".indexOf(realShowUrl)>-1){
//
//                    }else{
//
//                    }


//                }
          getTvShowDetails("https://watch.biyov.com/tvshows/fullmetal-alchemist-brotherhood/");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }


    private static Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .get();
        return doc;
    }


    public static void getTvShowDetails(String url) throws IOException, InterruptedException {

        Document docTvShow = getDocument("https://watch.biyov.com/episodes/game-of-thrones/");
        Element infoDiv= docTvShow.getElementById("info");
        //   剧情介绍
        String titleH2=infoDiv.getElementsByTag("h2").text();
        Elements infoPs=infoDiv.getElementsByClass("wp-content").first().getElementsByTag("p");
        //   电影中文介绍
        String movieChineseContent="";
        //   电影英文介绍
        String movieEnlishContent="";
        if(infoPs.eq(0)!=null){
            movieChineseContent=infoPs.eq(0).text();
        }
        if(infoPs.eq(1)!=null){
            movieEnlishContent=infoPs.eq(1).text();
        }

//        季
        Elements secS= docTvShow.getElementById("seasons").getElementsByClass("se-c");

        for (Element sec:secS ) {
            Elements lis= sec.getElementsByClass("se-a").first().getElementsByClass("episodios").first().getElementsByTag("li");
            for (Element li:lis){
                // 当前剧集 的图片地址
                String seasonPicUrl= li.getElementsByClass("imagen").first().getElementsByTag("img").first().attr("src");
                // 当前剧集  的数字
                String seasonNumber=li.getElementsByClass("numerando").first().text();
                // 当前剧集 标题
                Element el=li.getElementsByClass("episodiotitle").first();

                String seasonTitle=el.getElementsByTag("a").text();
                // 当前剧集 biyov 比优 视频播放地址
                String seasonBiyovShowUrl=el.getElementsByTag("a").first().attr("href");
                // 当前剧集 上映时间
                String seasonShowTime=el.getElementsByTag("span").first().text();
                String seasonRealShowUrl="";

                if(!StringUtils.isEmpty(seasonBiyovShowUrl)){
                    Document docRealShow= getDocument(seasonBiyovShowUrl);

//                  https://watch.biyov.com/wp-admin/admin-ajax.php
//                    Element element=docRealShow.getElementsByClass("pframe").first();
//                    seasonRealShowUrl=docRealShow.getElementsByClass("pframe").first().getElementsByTag("iframe").first().attr("src");
                    Elements descDivs= docRealShow.getElementById("info").getElementsByClass("description").first().getElementsByTag("p");
                    String seasonChineseContent="";
                    String seasonEnlishContent="";
                    String seasonPicUrl2="";
                    if(descDivs.eq(0)!=null){
                        seasonChineseContent=descDivs.eq(0).text();
                    }
                    if(descDivs.eq(1)!=null){
                        seasonEnlishContent=descDivs.eq(1).text();
                    }
                    Element elementItem= descDivs.first().getElementById("dt_galery").getElementsByClass("g-item").first();
                    String seasonName= elementItem.getElementsByTag("a").text();
                    seasonPicUrl2= elementItem.getElementsByTag("noscript").first().getElementsByTag("img").first().attr("src");
                    if(seasonPicUrl2.indexOf("\\?")>-1){
                        seasonPicUrl2=seasonPicUrl2.split("\\?")[0];
                    }

                    System.out.println(seasonChineseContent);
                    System.out.println(seasonEnlishContent);
                    System.out.println(seasonName);
                    System.out.println(seasonPicUrl2);
                    System.out.println(seasonRealShowUrl);
                    System.out.println("--------------------------------------------\n");
                }



            }
        }



        System.out.println(movieChineseContent);
        System.out.println(movieEnlishContent);



    }


}
