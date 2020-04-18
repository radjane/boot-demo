package cn.fulong.bootdemo.entity;

import lombok.Data;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:46 下午
 * @desc 豆瓣电影评论
 */
@Data
public class DouBanMovieComment {
    /***
     * 主键
     */
    int id;
    /***
     * 数据id
     */
    String dataCid;
    /***
     * 电影id
     */
    String movieId;
    /***
     * 评论人
     */
    String commentPerson;
    /***
     * 评论内容
     */
    String commentShort;
    /***
     * 网友点赞数
     */
    int commentVote;
    /***
     * 评分 allstar50 rating
     */
    int rating;
    //  评论时间
    /***
     * 评论时间
     */
    String commentTime;
    /***
     * 是否打分 0未打分 1已打分
     */
    int ifRating;
    /***
     * 是否看过 0想看 1看过
     */
    int ifWatched;

}
