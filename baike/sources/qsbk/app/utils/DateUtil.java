package qsbk.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.utils.ACache;

public class DateUtil {
    public static SimpleDateFormat FORMAT_HHMM = new SimpleDateFormat("HH:mm");
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm";
    public static SimpleDateFormat FORMAT_MMDDHHMM = new SimpleDateFormat("MM-dd HH:mm");
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    public static SimpleDateFormat FORMAT_YYYYMMDDHHMM = new SimpleDateFormat(FORMAT_LONG);

    public static String getTody(Date date) {
        return new SimpleDateFormat(FORMAT_LONG).format(Long.valueOf(date.getTime()));
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

    public static String getTimePostStr(int i) {
        Context context = QsbkApp.mContext;
        int i2;
        if (i > ACache.TIME_DAY) {
            i2 = i / ACache.TIME_DAY;
            return String.format(context.getString(R.string.nearby_days_before), new Object[]{Integer.valueOf(i2)});
        } else if (i > ACache.TIME_HOUR) {
            i2 = i / ACache.TIME_HOUR;
            return String.format(context.getString(R.string.nearby_hours_before), new Object[]{Integer.valueOf(i2)});
        } else if (i <= 60) {
            return context.getString(R.string.seconds_before);
        } else {
            i2 = i / 60;
            return String.format(context.getString(R.string.nearby_minutes_before), new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String getNearByTimePostStr(int i) {
        Context context = QsbkApp.mContext;
        int i2;
        if (i > ACache.TIME_DAY) {
            i2 = i / ACache.TIME_DAY;
            return String.format(context.getString(R.string.new_nearby_days_before), new Object[]{Integer.valueOf(i2)});
        } else if (i > ACache.TIME_HOUR) {
            i2 = i / ACache.TIME_HOUR;
            return String.format(context.getString(R.string.new_nearby_hours_before), new Object[]{Integer.valueOf(i2)});
        } else if (i <= 60) {
            return context.getString(R.string.seconds_before);
        } else {
            i2 = i / 60;
            return String.format(context.getString(R.string.new_nearby_minutes_before), new Object[]{Integer.valueOf(i2)});
        }
    }

    public static StringBuffer Get_DiffDate_Info(Context context, long j, int i) {
        int timeInMillis;
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        if (i > 0) {
            timeInMillis = (int) (j - (instance.getTimeInMillis() / 1000));
        } else {
            timeInMillis = (int) ((instance.getTimeInMillis() / 1000) - j);
        }
        if (timeInMillis > ACache.TIME_DAY) {
            stringBuffer.append(getTody(new Date(j * 1000)));
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

    public static StringBuffer Get_DiffDate_Info_Release(Context context, long j, int i) {
        int timeInMillis;
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        if (i > 0) {
            timeInMillis = (int) (j - (instance.getTimeInMillis() / 1000));
        } else {
            timeInMillis = (int) ((instance.getTimeInMillis() / 1000) - j);
        }
        if (timeInMillis > 2592000) {
            stringBuffer.append("" + getTody(new Date(j * 1000)));
        } else if (timeInMillis > ACache.TIME_DAY) {
            stringBuffer.append(String.format("%d天前", new Object[]{Integer.valueOf(timeInMillis / ACache.TIME_DAY)}));
        } else if (timeInMillis > ACache.TIME_HOUR) {
            stringBuffer.append(String.format("%d小时前", new Object[]{Integer.valueOf(timeInMillis / ACache.TIME_HOUR)}));
        } else if (timeInMillis > 60) {
            stringBuffer.append(String.format("%d分钟前", new Object[]{Integer.valueOf(timeInMillis / 60)}));
        } else if (timeInMillis > 0) {
            stringBuffer.append(context.getString(R.string.seconds_before));
        } else {
            stringBuffer.append(context.getString(R.string.seconds_before));
        }
        return stringBuffer;
    }

    public static StringBuffer Get_Last_Date(Context context, long j) {
        StringBuffer stringBuffer = new StringBuffer();
        int timeInMillis = (int) ((Calendar.getInstance().getTimeInMillis() / 1000) - j);
        if (timeInMillis >= 129600) {
            stringBuffer.append("0分钟");
        } else if (126000 < timeInMillis && timeInMillis < 129600) {
            stringBuffer.append((60 - ((timeInMillis - 126000) / 60)) + "分钟");
        } else if (timeInMillis >= 0 && timeInMillis <= 126000) {
            stringBuffer.append((36 - (timeInMillis / ACache.TIME_HOUR)) + "小时");
        } else if (timeInMillis < 0) {
            stringBuffer.append("");
        }
        return stringBuffer;
    }

    public static boolean isDuringDate(String str, String str2) {
        return isDuringDate(str, str2, new SimpleDateFormat(FORMAT_SHORT, Locale.CHINA).format(Long.valueOf(new Date().getTime())));
    }

    public static boolean isDuringDate(String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_SHORT, Locale.CHINA);
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str2);
            Date parse3 = simpleDateFormat.parse(str3);
            if (parse3.getTime() < parse.getTime() || parse3.getTime() > parse2.getTime()) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String timeStamp2Date(int i, String str) {
        return new SimpleDateFormat(str).format(new Date(((long) i) * 1000));
    }

    public static String getCurrentYear(String str) {
        return str.substring(0, 4);
    }

    public static String getCurrentMonth(String str) {
        return str.substring(5, 7);
    }

    public static String getHHMM(Calendar calendar) {
        return FORMAT_HHMM.format(calendar.getTime());
    }

    public static String getMMddHHmm(Calendar calendar) {
        return FORMAT_MMDDHHMM.format(calendar.getTime());
    }

    public static String getYYYYMMddHHmm(Calendar calendar) {
        return FORMAT_YYYYMMDDHHMM.format(calendar.getTime());
    }
}
