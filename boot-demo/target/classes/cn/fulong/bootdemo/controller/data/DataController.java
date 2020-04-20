package cn.fulong.bootdemo.controller.data;


import cn.fulong.bootdemo.entity.ResultBean;
import cn.fulong.bootdemo.service.HeroInfoService;
import cn.fulong.bootdemo.service.Kr36NewsFlashService;
import cn.fulong.bootdemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 * 数据中心
 */
@RestController
@RequestMapping("/data")
public class DataController {


    @Autowired
    private Kr36NewsFlashService kr36NewsFlashService;


    @Autowired
    private MovieService movieService;


    @Autowired
    private HeroInfoService heroInfoService;


    /***
     * 查询英雄列表 数据 带分页
     * @param pageNum
     * @param pageSize
     * @return
     */

    @GetMapping("/heroInfos")
    public ResultBean getHeroInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSie",defaultValue = "10") Integer pageSize) {

        Map data= heroInfoService.getHeronIfo(pageNum,pageSize);

        return new ResultBean(ResultBean.SUCCESS,data,"英雄列表数据");
    }

    /**
     * 获取英雄信息
     * @param heroInfoId
     * @return
     */
    @GetMapping("/heroInfo")
    public ResultBean getHeroInfo(@RequestParam(value = "heroInfoId") Integer heroInfoId) {
        Map data = heroInfoService.getHeroInfo(heroInfoId);
        return new ResultBean(ResultBean.SUCCESS,data,"英雄列表数据");
    }

    /***
     * 猫眼票房信息
     * @return
     */
    @RequestMapping("/saveMaoYanDayBoxOffice")
    public ResultBean saveMaoYanDayBoxOffice(String yyyymmdd, String yyyymm, String year) {
        String msg = movieService.saveMaoYanDayBoxOffice(yyyymmdd, yyyymm, year);
        return new ResultBean(ResultBean.SUCCESS, "success", msg);
    }


    /***
     * 36氪 快讯
     * @return
     */
    @RequestMapping("/save36KrNewsFlash")
    public ResultBean save36KrNewsFlash(Integer size) {
        String msg = kr36NewsFlashService.save36KrNewsFlash(size);
        return new ResultBean(ResultBean.SUCCESS, "success", msg);
    }



    /***
     * 36氪 快讯 分类
     * @return
     */
    @RequestMapping("/update36KrNewsFlash")
    public ResultBean update36KrNewsFlash(String yyyymmdd) {
        String msg = kr36NewsFlashService.update36KrNewsFlash(yyyymmdd);
        return new ResultBean(ResultBean.SUCCESS, "success", msg);
    }


}
