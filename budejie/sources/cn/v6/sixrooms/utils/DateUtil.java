package cn.v6.sixrooms.utils;

import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    public static String getStringDateChinese() {
        String substring = getStringDate().substring(0, 10);
        return substring.substring(0, 4) + "年" + substring.substring(5, 7) + "月" + substring.substring(8, 10) + "日";
    }

    public static String getHourDe() {
        String hour = getHour();
        if (hour.length() == 2) {
            return hour.substring(0, 1);
        }
        return "0";
    }

    public static String getHourUnit() {
        String hour = getHour();
        if (hour.length() == 2) {
            return hour.substring(1, 2);
        }
        return hour;
    }

    public static String getMinuteDe() {
        String time = getTime();
        if (time.length() == 2) {
            return time.substring(0, 1);
        }
        return "0";
    }

    public static String getMinuteUnit() {
        String time = getTime();
        if (time.length() == 2) {
            return time.substring(1, 2);
        }
        return time;
    }

    public static String getStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getStringDates() {
        return new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date());
    }

    public static String getStringDateShort() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static Date strToDateLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    public static String dateToStrLong(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String dateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0));
    }

    public static Date getNow() {
        return new Date();
    }

    public static String getStringToday() {
        return new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
    }

    public static String getHour() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(11, 13);
    }

    public static String getHourMinuteCurr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(11, 16);
    }

    public static String getHourTime(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(Long.parseLong(str))).substring(11);
    }

    public static String time2str(String str) {
        long parseLong = Long.parseLong(str) * 1000;
        DateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(parseLong);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String TimeStamp2Date(String str) {
        return str;
    }

    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(14, 16);
    }

    public static String getWeek(String str) {
        Date strToDate = strToDate(str);
        Calendar instance = Calendar.getInstance();
        instance.setTime(strToDate);
        return new SimpleDateFormat("EEEE").format(instance.getTime());
    }

    public static String getWeekStr(String str) {
        String week = getWeek(str);
        if ("1".equals(week)) {
            return "星期日";
        }
        if ("2".equals(week)) {
            return "星期一";
        }
        if ("3".equals(week)) {
            return "星期二";
        }
        if ("4".equals(week)) {
            return "星期三";
        }
        if ("5".equals(week)) {
            return "星期四";
        }
        if ("6".equals(week)) {
            return "星期五";
        }
        if (AlibcJsResult.CLOSED.equals(week)) {
            return "星期六";
        }
        return week;
    }

    public static String dayForWeek() throws Exception {
        int i;
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        if (instance.get(7) == 1) {
            i = 7;
        } else {
            i = instance.get(7) - 1;
        }
        if (7 == i) {
            return "星期日";
        }
        if (1 == i) {
            return "星期一";
        }
        if (2 == i) {
            return "星期二";
        }
        if (3 == i) {
            return "星期三";
        }
        if (4 == i) {
            return "星期四";
        }
        if (5 == i) {
            return "星期五";
        }
        if (6 == i) {
            return "星期六";
        }
        return null;
    }

    public static long getMilliSeconds(long j) {
        if (j > 9999999999999L) {
            return j / 1000;
        }
        if (j < 1000000000000L) {
            return j * 1000;
        }
        return j;
    }

    public static String getTimeInfo(long j) {
        long milliSeconds = getMilliSeconds(j);
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.setTime(new Date());
        instance.add(5, -1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
        instance2.setTime(new Date());
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        Calendar instance3 = Calendar.getInstance(TimeZone.getDefault());
        instance3.setTimeInMillis(milliSeconds);
        if (instance3.after(instance2)) {
            return getStringTime(milliSeconds).substring(11, 16);
        }
        if (instance3.before(instance)) {
            return getStringTime(milliSeconds).substring(5, 10);
        }
        return "昨天 ";
    }

    public static String getTimeInfoInChat(long j) {
        long milliSeconds = getMilliSeconds(j);
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.setTime(new Date());
        instance.add(5, -1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
        instance2.setTime(new Date());
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        Calendar instance3 = Calendar.getInstance(TimeZone.getDefault());
        instance3.setTimeInMillis(milliSeconds);
        if (instance3.after(instance2)) {
            return getStringTime(milliSeconds).substring(11, 16);
        }
        if (instance3.before(instance)) {
            return getStringTime(milliSeconds).substring(5, 16);
        }
        return "昨天 " + getStringTime(milliSeconds).substring(11, 16);
    }

    public static boolean isInAnotherDay(long j, long j2) {
        long milliSeconds = getMilliSeconds(j);
        long milliSeconds2 = getMilliSeconds(j2);
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.setTime(new Date(milliSeconds));
        int i = instance.get(5);
        Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
        instance2.setTime(new Date(milliSeconds2));
        return i != instance2.get(5);
    }

    public static boolean longThan2minutes(long j, long j2) {
        return Math.abs(j - j2) > 120000;
    }

    public static String getStringTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(getMilliSeconds(j)));
    }

    public static String getyMdTime(long j) {
        long milliSeconds = getMilliSeconds(j);
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyy-MM-dd").format(new Date(milliSeconds)) + "日");
        stringBuffer.replace(4, 5, "年");
        stringBuffer.replace(7, 8, "月");
        return stringBuffer.toString();
    }

    public static String getHourFromMillisecond(long j) {
        if (j <= 0) {
            return "00:00:00";
        }
        String valueOf;
        String valueOf2;
        String valueOf3;
        long j2 = ((j / 1000) / 60) / 60;
        long j3 = ((j / 1000) / 60) % 60;
        long j4 = (j / 1000) % 60;
        if (j2 >= 10) {
            valueOf = String.valueOf(j2);
        } else {
            valueOf = "0" + String.valueOf(j2);
        }
        if (j3 >= 10) {
            valueOf2 = String.valueOf(j3);
        } else {
            valueOf2 = "0" + String.valueOf(j3);
        }
        if (j4 >= 10) {
            valueOf3 = String.valueOf(j4);
        } else {
            valueOf3 = "0" + String.valueOf(j4);
        }
        return valueOf + ":" + valueOf2 + ":" + valueOf3;
    }

    public static String getMinuteFromMillisecond(long j) {
        if (j <= 0) {
            return "00:00";
        }
        String valueOf;
        String valueOf2;
        long j2 = (j / 1000) / 60;
        long j3 = (j / 1000) % 60;
        if (j2 >= 10) {
            valueOf = String.valueOf(j2);
        } else {
            valueOf = "0" + String.valueOf(j2);
        }
        if (j3 >= 10) {
            valueOf2 = String.valueOf(j3);
        } else {
            valueOf2 = "0" + String.valueOf(j3);
        }
        return valueOf + ":" + valueOf2;
    }

    public static String millisToString(long j) {
        return a(j, false);
    }

    public static String millisToText(long j) {
        return a(j, true);
    }

    private static String a(long j, boolean z) {
        Object obj;
        if (j < 0) {
            obj = 1;
        } else {
            obj = null;
        }
        long abs = Math.abs(j) / 1000;
        int i = (int) (abs % 60);
        abs /= 60;
        int i2 = (int) (abs % 60);
        abs /= 60;
        int i3 = (int) abs;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("00");
        if (z) {
            if (abs > 0) {
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                if (obj != null) {
                    str = "-";
                } else {
                    str = "";
                }
                return stringBuilder.append(str).append(i3).append(IXAdRequestInfo.HEIGHT).append(decimalFormat.format((long) i2)).append("min").toString();
            } else if (i2 > 0) {
                return (obj != null ? "-" : "") + i2 + "min";
            } else {
                return (obj != null ? "-" : "") + i + "s";
            }
        } else if (abs > 0) {
            return (obj != null ? "-" : "") + i3 + ":" + decimalFormat.format((long) i2) + ":" + decimalFormat.format((long) i);
        } else {
            return (obj != null ? "-" : "") + i2 + ":" + decimalFormat.format((long) i);
        }
    }

    public static String getDateDetailForFigure(long j) {
        return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(1000 * j));
    }
}
