package com.spriteapp.booklibrary.util;

import com.umeng.analytics.a;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static String friendlyTime(String str) {
        return friendlyTime(toDate(str));
    }

    public static String friendlyTime(long j) {
        return friendlyTime(new Date(j));
    }

    public static String friendlyTime(Date date) {
        if (date == null) {
            return "Unknown";
        }
        String str = "";
        Calendar instance = Calendar.getInstance();
        int timeInMillis;
        if (((SimpleDateFormat) dateFormater2.get()).format(instance.getTime()).equals(((SimpleDateFormat) dateFormater2.get()).format(date))) {
            timeInMillis = (int) ((instance.getTimeInMillis() - date.getTime()) / a.j);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            }
            return timeInMillis + "小时前";
        }
        timeInMillis = (int) ((instance.getTimeInMillis() / a.i) - (date.getTime() / a.i));
        if (timeInMillis == 0) {
            timeInMillis = (int) ((instance.getTimeInMillis() - date.getTime()) / a.j);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            }
            return timeInMillis + "小时前";
        } else if (timeInMillis == 1) {
            return "昨天";
        } else {
            if (timeInMillis == 2) {
                return "前天";
            }
            if (timeInMillis <= 2 || timeInMillis > 10) {
                return timeInMillis > 10 ? ((SimpleDateFormat) dateFormater2.get()).format(date) : str;
            } else {
                return timeInMillis + "天前";
            }
        }
    }

    public static Date toDate(String str) {
        try {
            return ((SimpleDateFormat) dateFormater2.get()).parse(str);
        } catch (ParseException e) {
            return null;
        }
    }
}
