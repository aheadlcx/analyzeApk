package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class dr {
    protected static boolean a = false;

    public static int a(String str, String str2) {
        return a ? Log.d("LaunchLib_" + str, str2) : 0;
    }

    public static int a(String str, String str2, Throwable th) {
        return a ? Log.d("LaunchLib_" + str, str2, th) : 0;
    }

    public static String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.CHINESE).format(new Date(j));
    }

    public static synchronized void a(Context context, String str) {
        synchronized (dr.class) {
            if (a) {
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    stringBuffer.append(a(System.currentTimeMillis()));
                    stringBuffer.append("(" + Process.myPid() + ") ");
                    stringBuffer.append(str);
                    stringBuffer.append('\n');
                } catch (Throwable e) {
                    a("", "", e);
                }
                a("", stringBuffer.toString());
                try {
                    if (VERSION.SDK_INT > 7) {
                        File file = new File(context.getExternalCacheDir(), "launch.log");
                        if (!file.exists()) {
                            file.createNewFile();
                            a("LaunchLib_", "logfile:" + file.getAbsolutePath());
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        if (randomAccessFile.length() > 1048576) {
                            randomAccessFile.setLength(0);
                        }
                        randomAccessFile.seek(randomAccessFile.length());
                        randomAccessFile.write(stringBuffer.toString().getBytes());
                        randomAccessFile.close();
                    }
                } catch (Throwable e2) {
                    a("", "", e2);
                }
            }
        }
    }

    public static void a(boolean z) {
        a = z;
    }

    public static int b(String str, String str2) {
        return a ? Log.e("LaunchLib_" + str, str2) : 0;
    }

    public static int b(String str, String str2, Throwable th) {
        return a ? Log.e("LaunchLib_" + str, str2, th) : 0;
    }
}
