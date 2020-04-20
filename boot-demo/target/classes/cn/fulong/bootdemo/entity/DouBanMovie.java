package cn.fulong.bootdemo.entity;

import lombok.Data;
/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:29 下午
 * @desc 豆瓣电影
 */
@Data
public class DouBanMovie {

    //   主键
    int id;
    //    评分
    String rating;
    // 序号
    String ranks;
    // 封面图片链接
    String coverUrl;
    //  是否可以播放
    String isPlayable;
    //                    影片ID
    String movieId;
    //                    类型
    String types;
    //                    来源
    String regions;
    //                    影片
    String title;
    //                    详情页
    String url;
    //                    上映时间
    String releaseDate;
    //                    演员个数
    String actorCount;
    //                    投票数
    String voteCount;
    //                    评分
    String score;
    //                    演员列表
    String actors;
    /**
     * 用户是否看过
     */
    String isWatched;


}
