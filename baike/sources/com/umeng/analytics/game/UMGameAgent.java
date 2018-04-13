package com.umeng.analytics.game;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.MLog;

public class UMGameAgent extends MobclickAgent {
    private static final b a = new b();
    private static Context b;

    public static void init(Context context) {
        try {
            if (b == null && context != null) {
                b = context.getApplicationContext();
            }
            a.a(b);
        } catch (Throwable th) {
            MLog.e("please check Context!");
        }
    }

    public static void setTraceSleepTime(boolean z) {
        a.a(z);
    }

    public static void setPlayerLevel(int i) {
        a.a(String.valueOf(i));
    }

    public static void startLevel(String str) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (str.length() > 64) {
            MLog.e("Input string must be less than 64 chars");
        } else {
            a.b(str);
        }
    }

    public static void finishLevel(String str) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (str.length() > 64) {
            MLog.e("Input string must be less than 64 chars");
        } else {
            a.c(str);
        }
    }

    public static void failLevel(String str) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (str.length() > 64) {
            MLog.e("Input string must be less than 64 chars");
        } else {
            a.d(str);
        }
    }

    public static void pay(double d, double d2, int i) {
        if (i <= 0 || i >= 100) {
            MLog.e("The int value for 'Pay Channels' ranges between 1 ~ 99 ");
        } else if (d < 0.0d || d2 < 0.0d) {
            MLog.e("Input value type is negative");
        } else {
            a.a(d, d2, i);
        }
    }

    public static void pay(double d, String str, int i, double d2, int i2) {
        if (i2 <= 0 || i2 >= 100) {
            MLog.e("The int value for 'Pay Channels' ranges between 1 ~ 99 ");
        } else if (d < 0.0d || i < 0 || d2 < 0.0d) {
            MLog.e("Input value type is negative");
        } else if (a(str)) {
            MLog.e("Input string is null or empty");
        } else {
            a.a(d, str, i, d2, i2);
        }
    }

    public static void exchange(double d, String str, double d2, int i, String str2) {
        if (d < 0.0d || d2 < 0.0d) {
            MLog.e("Input value type is negative");
        } else if (i <= 0 || i >= 100) {
            MLog.e("The int value for 'Pay Channels' ranges between 1 ~ 99 ");
        } else {
            a.a(d, str, d2, i, str2);
        }
    }

    public static void buy(String str, int i, double d) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (i < 0 || d < 0.0d) {
            MLog.e("Input value type is negative");
        } else {
            a.a(str, i, d);
        }
    }

    public static void use(String str, int i, double d) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (i < 0 || d < 0.0d) {
            MLog.e("Input value type is negative");
        } else {
            a.b(str, i, d);
        }
    }

    public static void bonus(double d, int i) {
        if (d < 0.0d) {
            MLog.e("Input value type is negative");
        } else if (i <= 0 || i >= 100) {
            MLog.e("The int value for 'Pay Channels' ranges between 1 ~ 99 ");
        } else {
            a.a(d, i);
        }
    }

    public static void bonus(String str, int i, double d, int i2) {
        if (a(str)) {
            MLog.e("Input string is null or empty");
        } else if (i < 0 || d < 0.0d) {
            MLog.e("Input value type is negative");
        } else if (i2 <= 0 || i2 >= 100) {
            MLog.e("The int value for 'Pay Channels' ranges between 1 ~ 99 ");
        } else {
            a.a(str, i, d, i2);
        }
    }

    private static boolean a(String str) {
        if (str != null && str.trim().length() > 0) {
            return false;
        }
        return true;
    }
}
