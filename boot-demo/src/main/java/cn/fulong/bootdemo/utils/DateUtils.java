package cn.fulong.bootdemo.utils;

import java.sql.Timestamp;
import java.util.Calendar;

/***
 *
 */
public class DateUtils {




    /***
     * 获取 yyyymmdd  以当前日期为准
     * @param amount 偏移量
     * @return
     */
    public static String getYmd(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        return getYmdByCalendar(calendar);
    }


    /***
     *
     * @param ymd    以ymd日期为准
     * @param amount 偏移量
     * @return
     */
    public static String getYmd(String ymd, int amount) {
        String year = ymd.substring(0, 4);
        String month = ymd.substring(4, 6);
        String date = ymd.substring(6, 8);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        calendar.set(Calendar.DATE, Integer.valueOf(date));
        calendar.add(Calendar.DATE, amount);
        return getYmdByCalendar(calendar);
    }


    /***
     * 获取 yyyymmdd字符串
     * @param calendar
     * @return
     */
    public static String getYmdByCalendar(Calendar calendar) {
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowYear = calendar.get(Calendar.YEAR);
        int today = calendar.get(Calendar.DATE);
        String nowMonthStr = (nowMonth > 9 ? nowMonth + "" : ("0" + nowMonth));
        String todayStr = (today > 9 ? today + "" : ("0" + today));
        String startYmd = nowYear + nowMonthStr + todayStr + "";
        return startYmd;
    }


    public static void main(String[] args) {

        System.out.println(getYmd(0));
        String ymd="20191010";
        String year = ymd.substring(0, 4);
        String month = ymd.substring(4, 6);
        String date = ymd.substring(6, 8);
        System.out.println(year);
        System.out.println(month);
        System.out.println(date);
        System.out.println(new Timestamp(new Long("1570801294")));
    }

}
