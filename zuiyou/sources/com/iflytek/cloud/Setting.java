package com.iflytek.cloud;

import android.os.Environment;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.cb.a;
import java.util.Locale;

public class Setting {
    public static final String a = (Environment.getExternalStorageDirectory().getPath() + "/msc/msc.log");
    private static String b = a;
    private static boolean c = false;
    private static boolean d = true;

    public enum LOG_LEVEL {
        all,
        detail,
        normal,
        low,
        none
    }

    private Setting() {
    }

    public static boolean getLocationEnable() {
        return d;
    }

    public static LOG_LEVEL getLogLevel() {
        LOG_LEVEL log_level = LOG_LEVEL.none;
        try {
            return LOG_LEVEL.values()[cb.b().ordinal()];
        } catch (Throwable e) {
            cb.a(e);
            return log_level;
        }
    }

    public static String getLogPath() {
        return b;
    }

    public static boolean getSaveTestLog() {
        return c;
    }

    public static boolean getShowLog() {
        return cb.a();
    }

    public static void setLocale(Locale locale) {
        Resource.setUILanguage(locale);
    }

    public static void setLocationEnable(boolean z) {
        d = z;
    }

    public static void setLogLevel(LOG_LEVEL log_level) {
        if (log_level != null) {
            try {
                cb.a(a.values()[log_level.ordinal()]);
            } catch (Throwable e) {
                cb.a(e);
            }
        }
    }

    public static void setLogPath(String str) {
        b = str;
    }

    public static void setSaveTestLog(boolean z) {
        c = z;
    }

    public static void setShowLog(boolean z) {
        cb.a(z);
    }
}
