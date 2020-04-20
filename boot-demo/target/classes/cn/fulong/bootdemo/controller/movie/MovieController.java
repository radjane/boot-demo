package cn.fulong.bootdemo.controller.movie;


import cn.fulong.bootdemo.entity.MaoYanDayBoxOffice;
import cn.fulong.bootdemo.entity.MaoYanDayBoxOfficeMovie;
import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.service.MovieService;
import cn.fulong.bootdemo.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author:GHB
 * @Date:2019-10-03 影片
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /***
     * 豆瓣类型分类列表电影
     * @return
     */
    @ResponseBody
    @PostMapping("/saveDouBanList")
    public ResultBean saveDouBanAllMovies(String requestUrl) {
        boolean flag = movieService.saveDouBanAllMovies(requestUrl);
        if (flag) {
            return new ResultBean(ResultBean.SUCCESS, "success", "豆瓣类型分类列表电影：添加影片成功");
        } else {
            return new ResultBean(ResultBean.ERROR, "error", "豆瓣类型分类列表电影：添加影片失败");
        }
    }


    /***
     * 豆瓣top250电影
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDouBanTop250List")
    public ResultBean saveDouBanTop250List() {
        boolean flag = movieService.saveDouBanTop250Movies();
        if (flag) {
            return new ResultBean(ResultBean.SUCCESS, "success", "豆瓣top250电影：添加影片成功");
        } else {
            return new ResultBean(ResultBean.ERROR, "error", "豆瓣top250电影：添加影片失败");
        }
    }

    /***
     * 豆瓣电影评论
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDouBanMoviesCommentList")
    public ResultBean saveDouBanMoviesCommentList(int movieId, int start, int size) {
        boolean flag = movieService.saveDouBanMoviesComment(movieId + "", start, size);
        if (flag) {
            return new ResultBean(ResultBean.SUCCESS, "success", "豆瓣电影评论：抓取评论成功");
        } else {
            return new ResultBean(ResultBean.ERROR, "error", "豆瓣电影评论：抓取评论失败");
        }
    }


    /***
     * 豆瓣top250电影列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDouBanTop250MovieCommentList")
    public ResultBean saveTop250MovieComments() {
        boolean flag = movieService.saveDouBanTop250MovieComments();
        if (flag) {
            return new ResultBean(ResultBean.SUCCESS, "success", "豆瓣top250电影评论：添加影片评论成功");
        } else {
            return new ResultBean(ResultBean.ERROR, "error", "豆瓣top250电影评论：添加影片评论失败");
        }
    }


    /***
     * 猫眼票房信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveMaoYanDayBoxOffice")
    public ResultBean saveMaoYanDayBoxOffice(String yyyymmdd, String yyyymm, String year) {
        String msg = movieService.saveMaoYanDayBoxOffice(yyyymmdd, yyyymm, year);
        return new ResultBean(ResultBean.SUCCESS, "success", msg);
    }


    /***
     * 猫眼票房信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMaoYanDayBoxOffice")
    public ResultBean getMaoYanDayBoxOffice(String yyyymmdd, int amount) {
        yyyymmdd = DateUtils.getYmd(yyyymmdd, amount);
        List<MaoYanDayBoxOfficeMovie> movies = movieService.getMaoYanDayBoxOfficeMovies(yyyymmdd);
        return new ResultBean(ResultBean.SUCCESS, movies, "");
    }


    /****
     * 日票房影片
     * @param model
     * @param startYmd
     * @return
     */
    @RequestMapping("/boxMovies")
    public String boxMovies(Model model, String startYmd) {
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        List<MaoYanDayBoxOfficeMovie> movies = movieService.getMaoYanDayBoxOfficeMovies(startYmd);
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        MaoYanDayBoxOffice dayBoxOfficeData = movieService.getMaoYanDayBoxOffice(startYmd);
        //  票房影片数据
        model.addAttribute("movies", movies);
        //  票房数据
        model.addAttribute("dayBoxOfficeData", dayBoxOfficeData);
        //  日期参数
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("lastDay", DateUtils.getYmd(startYmd, -1));
        model.addAttribute("nextDay", DateUtils.getYmd(startYmd, 1));
        return "movies/box_movies";
    }


    /****
     * 日票房数据
     * @param model
     * @param days
     * @return
     */
    @RequestMapping("/dayBoxOfficeData")
    public String dayBoxOfficeData(Model model, Integer days) {
        List<MaoYanDayBoxOffice> boxDatas = movieService.getMaoYanDayBoxOffice(days);
        // 票房列表数据
        model.addAttribute("boxDatas", boxDatas);
        // 日期参数
        model.addAttribute("days", days);
        return "movies/box_data";
    }


}
