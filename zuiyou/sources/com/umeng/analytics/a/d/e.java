package com.umeng.analytics.a.d;

import com.umeng.analytics.a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class e {
    public static final int a = 1;
    private static final int b = 1000;
    private static final int c = 1001;
    private static final int d = 1002;

    public static String a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.valueOf(((long) ((((instance.get(12) / 6) + 1) + (instance.get(11) * 10)) - 1)) + (b(j) * 240));
    }

    public static long b(long j) {
        long j2 = 0;
        try {
            long time = new SimpleDateFormat("yyyy", Locale.getDefault()).parse("1970").getTime();
            long j3 = (j - time) / a.i;
            if ((j - time) % a.i > 0) {
                j2 = 1;
            }
            return j2 + j3;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static long c(long j) {
        return a(j, 1001);
    }

    public static long d(long j) {
        return a(j, 1002);
    }

    private static long a(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i2 = ((instance.get(12) / 6) + 1) + (instance.get(11) * 10);
        int i3 = instance.get(13);
        int i4 = 0;
        if (i == 1002) {
            i4 = 360 - (((instance.get(12) % 6) * 60) + i3);
        } else if (i == 1001) {
            i4 = 60 - (i3 % 60);
            if (i2 % 6 == 0) {
                i4 += 60;
            }
        }
        return (long) (i4 * 1000);
    }

    public static boolean a(long j, long j2) {
        if (e(j) == e(j2)) {
            return true;
        }
        return false;
    }

    private static int e(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(5);
    }
}
