package com.bdj.picture.edit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class c {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat b = new SimpleDateFormat("MM月dd日 HH:mm");
    private static SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");

    public static Date a(String str) {
        Date date = null;
        try {
            date = a.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static boolean a(Calendar calendar, Calendar calendar2) {
        if (calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) {
            return true;
        }
        return false;
    }

    public static boolean a(long j) {
        if (c.format(new Date(j)).equals(c.format(new Date(System.currentTimeMillis())))) {
            return true;
        }
        return false;
    }

    public static String a(Date date) {
        int i = 0;
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        String[] strArr = new String[]{"今天", "昨天", "前天"};
        while (i < strArr.length) {
            if (a(instance, instance2)) {
                simpleDateFormat = new SimpleDateFormat(strArr[i] + "HH:mm");
                break;
            }
            instance.add(6, 1);
            i++;
        }
        simpleDateFormat = null;
        if (simpleDateFormat == null && instance.get(1) == instance2.get(1)) {
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        }
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        }
        return simpleDateFormat.format(date);
    }

    public static String b(Date date) {
        int i = 0;
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        String[] strArr = new String[]{"今天 ", "昨天 ", "前天 "};
        while (i < strArr.length) {
            if (a(instance, instance2)) {
                simpleDateFormat = new SimpleDateFormat(strArr[i] + "HH:mm");
                break;
            }
            instance.add(6, 1);
            i++;
        }
        simpleDateFormat = null;
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
        }
        return simpleDateFormat.format(date);
    }
}
