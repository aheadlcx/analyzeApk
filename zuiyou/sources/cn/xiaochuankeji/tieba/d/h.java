package cn.xiaochuankeji.tieba.d;

import com.umeng.analytics.a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class h {
    public static String a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(currentTimeMillis);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        boolean a = a(instance, instance2);
        boolean b = b(instance, instance2);
        boolean c = c(instance, instance2);
        StringBuilder stringBuilder = new StringBuilder("");
        if (a) {
            if (currentTimeMillis - j < 60000) {
                stringBuilder.append("刚刚");
            } else if (currentTimeMillis - j < a.j) {
                stringBuilder.append(((int) (((currentTimeMillis - j) / 1000) / 60)) + "分钟前");
            } else {
                stringBuilder.append(((int) ((((currentTimeMillis - j) / 1000) / 60) / 60)) + "小时前");
            }
        } else if (b) {
            r0 = new Date();
            r0.setTime(j);
            stringBuilder.append("昨天" + new SimpleDateFormat("HH:mm").format(r0));
        } else if (c) {
            r0 = new Date();
            r0.setTime(j);
            stringBuilder.append(new SimpleDateFormat("MM/dd").format(r0));
        } else {
            r0 = new Date();
            r0.setTime(j);
            stringBuilder.append(new SimpleDateFormat("yyyy/MM/dd").format(r0));
        }
        return stringBuilder.toString();
    }

    public static boolean a(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean a(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        if (instance.get(0) == instance2.get(0) && instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6)) {
            return true;
        }
        return false;
    }

    public static boolean b(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) - 1 == calendar2.get(6)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean c(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1)) {
            return true;
        } else {
            return false;
        }
    }
}
