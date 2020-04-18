package cn.fulong.bootdemo.entity;

public class DouBanMovieTop250 {
    //电影ID
    String movieId;
    //    电影名称
    String movieName;
    //    电影导演
    String movieDirector;
    //    上映年份
    String movieYear;
    //    封面图片
    String momvieCoverUrl;
    //    电影评分
    String movieRating;
    //    电影评论人数
    long movieRatingNum;
    //    电影推荐语
    String movieRecommendation;
    //     电影top250排名
    long movieIndex;
//    电影详情页
    String movieSubjectUrl;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMomvieCoverUrl() {
        return momvieCoverUrl;
    }

    public void setMomvieCoverUrl(String momvieCoverUrl) {
        this.momvieCoverUrl = momvieCoverUrl;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public long getMovieRatingNum() {
        return movieRatingNum;
    }

    public void setMovieRatingNum(long movieRatingNum) {
        this.movieRatingNum = movieRatingNum;
    }

    public String getMovieRecommendation() {
        return movieRecommendation;
    }

    public void setMovieRecommendation(String movieRecommendation) {
        this.movieRecommendation = movieRecommendation;
    }

    public long getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(long movieIndex) {
        this.movieIndex = movieIndex;
    }

    public String getMovieSubjectUrl() {
        return movieSubjectUrl;
    }

    public void setMovieSubjectUrl(String movieSubjectUrl) {
        this.movieSubjectUrl = movieSubjectUrl;
    }
}
