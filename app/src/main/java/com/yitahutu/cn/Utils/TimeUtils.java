package com.yitahutu.cn.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lenovo on 2016/3/17.
 */
public class TimeUtils {
    //yyyy年MM月dd日    HH:mm:ss
    public static String getTime1() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    //yyyy-MM-dd    hh:mm:ss
    public static String getTime2() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getTime3(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = sDateFormat.format(time);

        return date;
    }

    public static String getTime4(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(time);

        return date;
    }

    public static String getTime5(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(time);

        return date;
    }
    public static String getTime6(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(time);

        return date;
    }
    public static String getTime7(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String str = formatter.format(time);
        return str;
    }

    public static int getTimeZoneRawOffset(String timeZoneId) {
        return TimeZone.getTimeZone(timeZoneId).getRawOffset();
    }

    public static long comepareDate(Date date, Date date2) {
        return date.getTime() - date2.getTime();
    }

    public static long time2long(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d = dateFormat.parse(time);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
