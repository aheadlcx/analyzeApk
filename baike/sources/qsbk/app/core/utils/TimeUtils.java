package qsbk.app.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static boolean isArrivedTime(String str) {
        try {
            if (System.currentTimeMillis() >= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(str).getTime()) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String secToHour(long j) {
        return new SimpleDateFormat("mm:ss").format(new Date(j));
    }
}
