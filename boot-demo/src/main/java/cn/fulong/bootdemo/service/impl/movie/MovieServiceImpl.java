package cn.fulong.bootdemo.service.impl.movie;

import cn.fulong.bootdemo.dao.MovieDao;
import cn.fulong.bootdemo.entity.*;
import cn.fulong.bootdemo.service.MovieService;
import cn.fulong.bootdemo.utils.DateUtils;
import cn.fulong.bootdemo.utils.DouBanUtils;
import cn.fulong.bootdemo.utils.HttpClientUtils;
import cn.fulong.bootdemo.utils.MaoYanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


/**
 * @author black猫
 * @date 2020/4/12
 * @time 1:34 下午
 * @desc 电影的业务层
 */
@Service
public class MovieServiceImpl implements MovieService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MovieDao movieDao;

    @Override
    public boolean saveDouBanAllMovies(String requestUrl) {
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String[] array = {
                "纪录片&type=1&interval_id=",
                "传记&type=2&interval_id=",
                "犯罪&type=3&interval_id=",
                "历史&type=4&interval_id=",
                "动作&type=5&interval_id=",
                "情色&type=6&interval_id=",
                "歌舞&type=7&interval_id=",
                "儿童&type=8&interval_id=",
                "悬疑&type=10&interval_id=",
                "剧情&type=11&interval_id=",
                "灾难&type=12&interval_id=",
                "爱情&type=13&interval_id=",
                "音乐&type=14&interval_id=",
                "冒险&type=15&interval_id=",
                "奇幻&type=16&interval_id=",
                "科幻&type=17&interval_id=",
                "运动&type=18&interval_id=",
                "惊悚&type=19&interval_id=",
                "恐怖&type=20&interval_id=",
                "战争&type=22&interval_id=",
                "短片&type=23&interval_id=",
                "喜剧&type=24&interval_id=",
                "动画&type=25&interval_id=",
                "同性&type=26&interval_id=",
                "西部&type=27&interval_id=",
                "家庭&type=28&interval_id=",
                "武侠&type=29&interval_id=",
                "古装&type=30&interval_id=",
                "黑色电影&type=31&interval_id="
        };
        StringBuffer url = new StringBuffer("https://movie.douban.com/j/chart/top_list?type_name=");
        for (int j = 0; j < array.length; j++) {
            for (int i = 10; i > 0; i--) {
                url.append(array[j]).append(i * 10 + ":" + (i - 1) * 10).append("&action=&start=0&limit=10000");
                System.out.println("url->" + url);
                List<DouBanMovie> douBanMovies = httpClientUtils.doGetReturnMovies(url.toString());
                if (douBanMovies.size() > 0) {
                    movieDao.insertDouBanMovies(douBanMovies);
                    url = new StringBuffer("https://movie.douban.com/j/chart/top_list?type_name=");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean saveDouBanTop250Movies() {
        DouBanUtils douBanUtils = new DouBanUtils();
        boolean flag = true;
        for (int i = 0; i < DouBanUtils.NUMS.length; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                List<DouBanMovieTop250> movies = douBanUtils.getTop250(DouBanUtils.NUMS[i]);
                if (!CollectionUtils.isEmpty(movies)) {
                    flag = movieDao.insertDouBanMoviesTop250(movies);
                }
            } catch (IOException e) {
                flag = flag && false;
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean saveDouBanMoviesComment(String movieId, int start, int size) {
        DouBanUtils douBanUtils = new DouBanUtils();
        boolean flag = true;
        for (int i = start; i <= size; ) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                List<DouBanMovieComment> douBanMovieComments = douBanUtils.getMovieComment(movieId, i, null);
                if (!CollectionUtils.isEmpty(douBanMovieComments)) {
                    flag = movieDao.insertDouBanMovieComments(douBanMovieComments);
                }
            } catch (IOException e) {
                flag = flag && false;
                e.printStackTrace();
            }
            i += 20;
        }
        return flag;
    }

    @Override
    public boolean saveDouBanTop250MovieComments() {
        DouBanUtils douBanUtils = new DouBanUtils();
        boolean flag = true;
        String sql = "select * from douban_movie_top250 t";
        List<DouBanMovieTop250> movies = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DouBanMovieTop250.class));
        for (int i = 0; i < movies.size(); i++) {
            DouBanMovieTop250 movie = movies.get(i);
            for (int j = 0; j <= 100; ) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    List<DouBanMovieComment> douBanMovieComments = douBanUtils.getMovieComment(movie.getMovieId(), j, null);
                    if (!CollectionUtils.isEmpty(douBanMovieComments)) {
                        flag = movieDao.insertDouBanMovieComments(douBanMovieComments);
                    }
                } catch (IOException e) {
                    flag = flag && false;
                    e.printStackTrace();
                }
                j += 20;
            }
        }
        return flag;
    }

    @Override
    public String saveMaoYanDayBoxOffice(String yyyymmdd, String yyyymm, String year) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        String nowYMD = DateUtils.getYmd(0);
        MaoYanUtils maoYanUtils = new MaoYanUtils();
        StringBuffer msg = new StringBuffer("");
        String sqlDay = "delete from mao_yan_day_box_office where yyyymmdd=?";
        String sqlMovie = "delete from mao_yan_day_box_office_movie where yyyymmdd=?";

        int ymd = Integer.valueOf(nowYMD);

// 如果都为空 采集当天的数据
        if (StringUtils.isEmpty(yyyymmdd) && StringUtils.isEmpty(yyyymm) && StringUtils.isEmpty(year)) {
            msg.append(insertData(sqlDay, sqlMovie, nowYMD, maoYanUtils));
        }
//日
        if (!StringUtils.isEmpty(yyyymmdd) && ymd >= Integer.valueOf(yyyymmdd)) {
            msg.append(insertData(sqlDay, sqlMovie, yyyymmdd, maoYanUtils));
        }
//月
        if (!StringUtils.isEmpty(yyyymm)) {
            int yearArgs = Integer.parseInt(yyyymm.substring(0, 3));
            int monthArgs = Integer.parseInt(yyyymm.substring(4, 5));
            int days = getDaysByYearAndMonth(yearArgs, monthArgs);
            for (int i = 1; i < days; i++) {
                if (i < 10) {
                    yyyymmdd = yyyymm + "0" + i;
                } else {
                    yyyymmdd = yyyymm + i;
                }
                if (ymd >= Integer.valueOf(yyyymmdd)) {
                    msg.append(insertData(sqlDay, sqlMovie, yyyymmdd, maoYanUtils));
                }
            }
        }

//年
        if (!StringUtils.isEmpty(year) && Integer.valueOf(year) <= nowYear) {
            int yy = Integer.valueOf(year);
            for (; yy <= nowYear; yy++) {
                for (int i = 1; i <= 12; i++) {
                    String mm = i > 9 ? i + "" : "0" + i;
                    int days = getDaysByYearAndMonth(Integer.valueOf(yy), i);
                    for (int j = 1; j <= days; j++) {
                        String dd = j > 9 ? j + "" : "0" + j;
                        yyyymmdd = yy + mm + dd;
                        if (ymd >= Integer.valueOf(yyyymmdd)) {
                            msg.append(insertData(sqlDay, sqlMovie, yyyymmdd, maoYanUtils));

                        }
                    }
                }
            }
        }
        maoYanUtils = null;
        return msg.toString();
    }

    @Override
    public List<MaoYanDayBoxOfficeMovie> getMaoYanDayBoxOfficeMovies(String startYmd) {
        /***
         * 默认查询 今天的数据
         */
        if (org.apache.commons.lang3.StringUtils.isEmpty(startYmd)) {
            startYmd = DateUtils.getYmd(0);
        }
        List<MaoYanDayBoxOfficeMovie> movies = movieDao.selectMaoYanDayBoxOfficeMovieEqYmd(startYmd);
        String nowYMD = DateUtils.getYmd(0);
        if (CollectionUtils.isEmpty(movies) && startYmd.equals(nowYMD)) {
            saveMaoYanPiaoFangEveryHour();
            movies = movieDao.selectMaoYanDayBoxOfficeMovieEqYmd(startYmd);
        }
        return movies;
    }

    @Override
    public List<MaoYanDayBoxOffice> getMaoYanDayBoxOffice(Integer days) {
        /***
         * 默认查询30天的数据
         */
        if (days == null) {
            days = 30;
        }
        String startYmd = DateUtils.getYmd(-days);
        return movieDao.selectMaoYanDayBoxOfficesGteYmd(startYmd);
    }

    @Override
    public MaoYanDayBoxOffice getMaoYanDayBoxOffice(String startYmd) {
        MaoYanDayBoxOffice piaoFangDay = movieDao.selectMaoYanDayBoxOfficeEqYmd(startYmd);
        String nowYMD = DateUtils.getYmd(0);
        if (piaoFangDay == null && startYmd.equals(nowYMD)) {
            piaoFangDay = movieDao.selectMaoYanDayBoxOfficeEqYmd(startYmd);
        }
        return piaoFangDay;
    }


    /****
     * cron表达式
     * second（秒） , minute（分）, hour（时）, day of month（日）, month （月） , week （周几）.
     *
     * 字段	允许值	允许的特殊字符
     * 秒	0-59	, - * /
     * 分	0-59	, - * /
     * 小时	0-23	, - * /
     * 日期	1-31	, - * ? / L W C
     * 月份	1-12	, - * /
     * 星期	0-7或SUN-SAT 0,7是SUN	, - * ? / L C #
     *
     *
     *
     *特殊字符	代表含义
     * ,	枚举
     * -	区间
     * *	任意
     * /	步长
     * ?	日/星期冲突匹配
     * L	最后
     * W	工作日
     * C	和calendar联系后计算过的值
     * #	星期，4#2，第2个星期四
     *
     *
     *
     */

    /***
     * 每天 0：5 执行
     * 更新昨天的数据
     */
    @Scheduled(cron = "0 5 0 * * ?")
    public void saveMaoYanPiaoFangEveryDay() {
        String nowYMD = DateUtils.getYmd(-1);
        String sqlDay = "delete from mao_yan_day_box_office where yyyymmdd=?";
        String sqlMovie = "delete from mao_yan_day_box_office_movie where yyyymmdd=?";
        MaoYanUtils maoYanUtils = new MaoYanUtils();
        insertData(sqlDay, sqlMovie, nowYMD, maoYanUtils);
        maoYanUtils = null;
    }

    /***
     * 每整点 执行
     * 更新当天的数据 和昨天的数据
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void saveMaoYanPiaoFangEveryHour() {
        String nowYMD = DateUtils.getYmd(0);
        String yesterday = DateUtils.getYmd(-1);
        String sqlDay = "delete from mao_yan_day_box_office where yyyymmdd=?";
        String sqlMovie = "delete from mao_yan_day_box_office_movie where yyyymmdd=?";
        MaoYanUtils maoYanUtils = new MaoYanUtils();
        insertData(sqlDay, sqlMovie, nowYMD, maoYanUtils);
        insertData(sqlDay, sqlMovie, yesterday, maoYanUtils);
        maoYanUtils = null;
    }

    /****
     *
     * @param year 4位年
     * @param month 月
     * @return 根据年月 获取天数
     */
    int getDaysByYearAndMonth(int year, int month) {
        int result;
        if (2 == month) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                result = 29;
            } else {
                result = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            result = 30;
        } else {
            result = 31;
        }
        return result;

    }

    /***
     * 插入票房数据
     * @param sqlDay
     * @param sqlMovie
     * @param yyyymmdd
     * @param maoYanUtils
     * @return
     */
    public String insertData(String sqlDay, String sqlMovie, String yyyymmdd, MaoYanUtils maoYanUtils) {
        boolean flagDay;
        boolean flagMovies;
        //   删除之前的数据
        jdbcTemplate.update(sqlDay, yyyymmdd);
        jdbcTemplate.update(sqlMovie, yyyymmdd);
        MaoYanDayBoxOfficeInfo infos = maoYanUtils.getBoxOfficeData(yyyymmdd);
        if (infos != null) {
            MaoYanDayBoxOffice maoYanDayBoxOffice = infos.getMaoYanDayBoxOffice();
            List<MaoYanDayBoxOfficeMovie> movies = infos.getMaoYanDayBoxOfficeMovie();
            if (maoYanDayBoxOffice != null) {
                flagDay = movieDao.insertMaoYanDayBoxOffice(maoYanDayBoxOffice);
                if (flagDay && movies.size() > 0) {
                    flagMovies = movieDao.insertMaoYanDayBoxOfficeMovies(movies);
                    if (flagMovies) {
                        return yyyymmdd + ":票房数据已采集。";
                    } else {
                        return yyyymmdd + ":票房数据day已采集,movie采集失败。";
                    }
                }
            }
        }
        return yyyymmdd + ":票房数据采集失败。";

    }


}
