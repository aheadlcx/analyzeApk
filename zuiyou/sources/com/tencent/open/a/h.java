package com.tencent.open.a;

import android.support.media.ExifInterface;
import android.text.format.Time;
import android.util.Log;

public final class h {
    public static final h a = new h();

    public final String a(int i) {
        switch (i) {
            case 1:
                return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
            case 2:
                return "D";
            case 4:
                return "I";
            case 8:
                return ExifInterface.LONGITUDE_WEST;
            case 16:
                return ExifInterface.LONGITUDE_EAST;
            case 32:
                return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
            default:
                return "-";
        }
    }

    public String a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        long j2 = j % 1000;
        Time time = new Time();
        time.set(j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a(i)).append('/').append(time.format("%Y-%m-%d %H:%M:%S")).append('.');
        if (j2 < 10) {
            stringBuilder.append("00");
        } else if (j2 < 100) {
            stringBuilder.append('0');
        }
        stringBuilder.append(j2).append(' ').append('[');
        if (thread == null) {
            stringBuilder.append("N/A");
        } else {
            stringBuilder.append(thread.getName());
        }
        stringBuilder.append(']').append('[').append(str).append(']').append(' ').append(str2).append('\n');
        if (th != null) {
            stringBuilder.append("* Exception : \n").append(Log.getStackTraceString(th)).append('\n');
        }
        return stringBuilder.toString();
    }
}
