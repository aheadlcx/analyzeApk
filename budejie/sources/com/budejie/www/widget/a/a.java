package com.budejie.www.widget.a;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class a {
    private static final ThreadLocal<SimpleDateFormat> a = new a$1();

    public static String a(long j) {
        return a(new Date(j));
    }

    public static String a(Date date) {
        if (date == null) {
            return "Unknown";
        }
        String str = "";
        Calendar instance = Calendar.getInstance();
        int timeInMillis;
        if (((SimpleDateFormat) a.get()).format(instance.getTime()).equals(((SimpleDateFormat) a.get()).format(date))) {
            timeInMillis = (int) ((instance.getTimeInMillis() - date.getTime()) / com.umeng.analytics.a.j);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            }
            return timeInMillis + "小时前";
        }
        timeInMillis = (int) ((instance.getTimeInMillis() / com.umeng.analytics.a.i) - (date.getTime() / com.umeng.analytics.a.i));
        if (timeInMillis == 0) {
            timeInMillis = (int) ((instance.getTimeInMillis() - date.getTime()) / com.umeng.analytics.a.j);
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
                return timeInMillis > 10 ? ((SimpleDateFormat) a.get()).format(date) : str;
            } else {
                return timeInMillis + "天前";
            }
        }
    }
}
