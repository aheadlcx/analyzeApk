package qsbk.app.utils;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.ClipboardManager;
import cz.msebera.android.httpclient.message.TokenParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import qsbk.app.im.TimeUtils;

public class StringUtils {
    private static final Pattern a = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String str) {
        try {
            return b.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    @TargetApi(11)
    public static void copyToClipboard(String str, Context context) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
        } else if (VERSION.SDK_INT >= 11) {
            ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str, str));
        }
    }

    public static String friendly_time(String str) {
        Date toDate = toDate(str);
        if (toDate == null) {
            return "Unknown";
        }
        String str2 = "";
        Calendar instance = Calendar.getInstance();
        int timeInMillis;
        if (c.format(instance.getTime()).equals(c.format(toDate))) {
            timeInMillis = (int) ((instance.getTimeInMillis() - toDate.getTime()) / TimeUtils.HOUR);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - toDate.getTime()) / 60000, 1) + "分钟前";
            }
            return timeInMillis + "小时前";
        }
        int timeInMillis2 = (int) ((instance.getTimeInMillis() / 86400000) - (toDate.getTime() / 86400000));
        if (timeInMillis2 == 0) {
            timeInMillis = (int) ((instance.getTimeInMillis() - toDate.getTime()) / TimeUtils.HOUR);
            if (timeInMillis == 0) {
                return Math.max((instance.getTimeInMillis() - toDate.getTime()) / 60000, 1) + "分钟前";
            }
            return timeInMillis + "小时前";
        } else if (timeInMillis2 == 1) {
            return "昨天";
        } else {
            if (timeInMillis2 == 2) {
                return "前天";
            }
            if (timeInMillis2 > 2 && timeInMillis2 <= 10) {
                return timeInMillis2 + "天前";
            }
            if (timeInMillis2 > 10) {
                return c.format(toDate);
            }
            return str2;
        }
    }

    public static boolean isToday(String str) {
        Date toDate = toDate(str);
        Date date = new Date();
        if (toDate == null || !c.format(date).equals(c.format(toDate))) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != TokenParser.SP && charAt != '\t' && charAt != TokenParser.CR && charAt != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return a.matcher(str).matches();
    }

    public static int toInt(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return i;
    }

    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    public static long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean toBool(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return false;
        }
    }

    public static String replaceHtml(String str) {
        return Pattern.compile("<.+?>|\t|\r|\n{2,}").matcher(str).replaceAll("");
    }
}
