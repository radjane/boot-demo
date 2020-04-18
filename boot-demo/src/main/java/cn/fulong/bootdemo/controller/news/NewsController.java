package cn.fulong.bootdemo.controller.news;


import cn.fulong.bootdemo.entity.Kr36NewsFlash;
import cn.fulong.bootdemo.service.Kr36NewsFlashService;
import cn.fulong.bootdemo.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Author:GHB
 * @Date:2019-10-12 新闻
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private Kr36NewsFlashService kr36NewsFlashService;


    /****
     * 36氪快讯表格列表
     * @param model
     * @param startYmd
     * @return
     */
    @RequestMapping("/kr36NewsFlashes1")
    public String kr36NewsFlashes1(Model model, String startYmd) {
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        List<Kr36NewsFlash> kr36NewsFlashes = kr36NewsFlashService.getKr36NewsFlashes1(startYmd);
        // 36氪快讯列表数据
        model.addAttribute("kr36NewsFlashes", kr36NewsFlashes);
        // 日期参数
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("lastDay", DateUtils.getYmd(startYmd, -1));
        model.addAttribute("nextDay", DateUtils.getYmd(startYmd, 1));
        return "news/kr36_news_flashes1";
    }


    /****
     * 36氪快讯时间节点列表
     * @param model
     * @param startYmd
     * @return
     */
    @RequestMapping("/kr36NewsFlashes2")
    public String kr36NewsFlashes2(Model model, String startYmd) {
        if (StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        List<Kr36NewsFlash> kr36NewsFlashes = kr36NewsFlashService.getKr36NewsFlashes2(startYmd);
        // 36氪快讯列表数据
        model.addAttribute("kr36NewsFlashes", kr36NewsFlashes);
        // 日期参数
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("lastDay", DateUtils.getYmd(startYmd, -1));
        model.addAttribute("nextDay", DateUtils.getYmd(startYmd, 1));

        return "news/kr36_news_flashes2";
    }

    /****
     * 36氪快讯时间节点 选择
     * @param model
     * @param startYmd
     * @return
     */
    @RequestMapping("/kr36NewsFlashes3")
    public String kr36NewsFlashes3(Model model, String startYmd, String endYmd) {
        if (StringUtils.isEmpty(startYmd) || StringUtils.isEmpty(endYmd)) {
            startYmd = DateUtils.getYmd(0);
            endYmd = DateUtils.getYmd(0);
        }
        List<Kr36NewsFlash> kr36NewsFlashes = kr36NewsFlashService.getKr36NewsFlashes3(startYmd, endYmd);
        // 36氪快讯列表数据
        model.addAttribute("kr36NewsFlashes", kr36NewsFlashes);
        // 日期参数
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("endYmd", endYmd);
        return "news/kr36_news_flashes3";
    }


}
