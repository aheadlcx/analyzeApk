package qsbk.app.core.utils;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.core.R;

public class DateUtil {
    public static String getToday(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(date.getTime()));
    }

    public static String getDateStr(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String getSpecifiedDayBefore(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, instance.get(5) - 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
    }

    public static String getSpecifiedDayAfter(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, instance.get(5) + 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
    }

    public static String getTimePostStr(long j) {
        return getAccuracyTimePostStr(1000 * j);
    }

    public static String getAccuracyTimePostStr(long j) {
        long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
        Context appContext = AppUtils.getInstance().getAppContext();
        int i;
        if (currentTimeMillis > 86400) {
            i = ((int) currentTimeMillis) / ACache.TIME_DAY;
            return String.format(appContext.getString(R.string.nearby_days_before), new Object[]{Integer.valueOf(i)});
        } else if (currentTimeMillis > 3600) {
            i = ((int) currentTimeMillis) / ACache.TIME_HOUR;
            return String.format(appContext.getString(R.string.nearby_hours_before), new Object[]{Integer.valueOf(i)});
        } else if (currentTimeMillis <= 60) {
            return appContext.getString(R.string.seconds_before);
        } else {
            i = ((int) currentTimeMillis) / 60;
            return String.format(appContext.getString(R.string.nearby_minutes_before), new Object[]{Integer.valueOf(i)});
        }
    }

    public static StringBuffer getDiffDateInfo(Context context, long j, int i) {
        int timeInMillis;
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        if (i > 0) {
            timeInMillis = ((int) (j - instance.getTimeInMillis())) / 1000;
        } else {
            timeInMillis = ((int) (instance.getTimeInMillis() - j)) / 1000;
        }
        if (timeInMillis > 2592000) {
            stringBuffer.append(getDateStr(j));
        } else if (timeInMillis > ACache.TIME_DAY) {
            timeInMillis /= ACache.TIME_DAY;
            stringBuffer.append(String.format(context.getString(R.string.days_before), new Object[]{Integer.valueOf(timeInMillis)}));
        } else if (timeInMillis > ACache.TIME_HOUR) {
            timeInMillis /= ACache.TIME_HOUR;
            stringBuffer.append(String.format(context.getString(R.string.hours_before), new Object[]{Integer.valueOf(timeInMillis)}));
        } else if (timeInMillis > 60) {
            timeInMillis /= 60;
            stringBuffer.append(String.format(context.getString(R.string.minutes_before), new Object[]{Integer.valueOf(timeInMillis)}));
        } else if (timeInMillis > 0) {
            stringBuffer.append(context.getString(R.string.seconds_before));
        } else {
            stringBuffer.append(context.getString(R.string.seconds_before));
        }
        return stringBuffer;
    }

    public static Date strToDateLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    public static String getTimeOrYestody(String str) {
        String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        Date strToDateLong = strToDateLong(str);
        Date date = new Date();
        if (split.length != 2) {
            return "";
        }
        if (strToDateLong.getYear() == date.getYear() && strToDateLong.getMonth() == date.getMonth() && strToDateLong.getDay() == date.getDay()) {
            return split[1];
        }
        return "昨天 " + split[1];
    }
}
