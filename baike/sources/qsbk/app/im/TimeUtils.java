package qsbk.app.im;

import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.utils.DateUtil;

public class TimeUtils {
    public static final long DAY = 86400000;
    public static final long HOUR = 3600000;
    public static final long MINUTE = 60000;
    public static final long UTC_START = 22960284;
    public static final long WEEK = 604800000;
    public static final String[] WEEK_DAY = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static Calendar a = Calendar.getInstance();

    public static String getLastLoginStr(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        StringBuffer stringBuffer = new StringBuffer();
        if (currentTimeMillis > 604800000) {
            if (((int) (currentTimeMillis / 604800000)) > 1) {
                stringBuffer.append(DateFormat.format("MM-dd", j));
            } else {
                stringBuffer.append(((int) (currentTimeMillis / 604800000)) + "周前");
            }
        } else if (currentTimeMillis > 86400000) {
            stringBuffer.append(((int) (currentTimeMillis / 86400000)) + "天前");
        } else if (currentTimeMillis > HOUR) {
            stringBuffer.append(((int) (currentTimeMillis / HOUR)) + "小时前");
        } else if (currentTimeMillis > 60000) {
            stringBuffer.append(((int) (currentTimeMillis / 60000)) + "分钟前");
        } else {
            stringBuffer.append("刚刚");
        }
        return stringBuffer.toString();
    }

    public static String getLastLiveStr(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (isSameDay(instance, instance2)) {
            stringBuffer.append(DateUtil.FORMAT_HHMM.format(new Date(j)));
        } else if (isYesterDay(instance2)) {
            Date date = new Date(j);
            stringBuffer.append("昨天");
            stringBuffer.append(DateUtil.FORMAT_HHMM.format(date));
        } else if (currentTimeMillis < 2592000000L) {
            stringBuffer.append(((int) (currentTimeMillis / 86400000)) + "天前");
        } else {
            stringBuffer.append("1月前");
        }
        stringBuffer.append("结束");
        return stringBuffer.toString();
    }

    public static String formatTime(long j) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        a.setTimeInMillis(currentTimeMillis);
        a.set(11, 0);
        a.set(12, 0);
        a.set(13, 0);
        a.set(14, 0);
        if (j > currentTimeMillis) {
            str = "HH:mm";
        } else {
            a.set(6, 0);
            if (j > a.getTimeInMillis()) {
                str = "MM月dd日";
            } else {
                str = "yyyy年MM月dd日";
            }
        }
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar.get(5) == calendar2.get(5) && calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1)) {
            return true;
        }
        return false;
    }

    public static boolean isYesterDay(Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.add(5, -1);
        if (instance.get(1) == calendar.get(1) && instance.get(2) == calendar.get(2) && instance.get(5) == calendar.get(5)) {
            return true;
        }
        return false;
    }

    public static boolean isYesterDay(Calendar calendar, Calendar calendar2) {
        a.setTimeInMillis(System.currentTimeMillis());
        a.add(5, -1);
        if (a.get(1) == calendar2.get(1) && a.get(2) == calendar2.get(2) && a.get(5) == calendar2.get(5)) {
            return true;
        }
        return false;
    }

    public static boolean isAWeekAgo(Calendar calendar, Calendar calendar2) {
        calendar.add(5, -7);
        return calendar2.before(calendar);
    }

    public static boolean isAWeekAgo(Calendar calendar) {
        return isAWeekAgo(Calendar.getInstance(), calendar);
    }

    public static boolean isSameYear(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1);
    }

    public static String getTimeSpanOfDay(int i) {
        if (i >= 0 && i < 5) {
            return "凌晨";
        }
        if (i >= 5 && i < 12) {
            return "上午";
        }
        if (i >= 12 && i < 13) {
            return "中午";
        }
        if (i < 13 || i >= 18) {
            return "晚上";
        }
        return "下午";
    }

    public static boolean inSameWeek(Calendar calendar, Calendar calendar2) {
        int i = calendar.get(1) - calendar2.get(1);
        if (i == 0) {
            if (calendar.get(3) == calendar2.get(3)) {
                return true;
            }
            return false;
        } else if (i == 1 || calendar2.get(2) == 11) {
            if (calendar.get(3) != calendar2.get(3)) {
                return false;
            }
            return true;
        } else if (i != -1 && calendar.get(2) != 11) {
            return false;
        } else {
            if (calendar.get(3) != calendar2.get(3)) {
                return false;
            }
            return true;
        }
    }

    public static String getDayOfWeek(Calendar calendar) {
        int i = calendar.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return WEEK_DAY[i];
    }
}
