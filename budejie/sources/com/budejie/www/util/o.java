package com.budejie.www.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Process;
import cn.v6.sdk.sixrooms.coop.CrashHandler;
import com.ali.auth.third.login.LoginConstants;
import com.budejie.www.activity.image.c;
import com.budejie.www.activity.video.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class o implements UncaughtExceptionHandler {
    private static o b = new o();
    private UncaughtExceptionHandler a;
    private Context c;
    private Map<String, String> d = new HashMap();
    private DateFormat e = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private String f;

    private o() {
    }

    public static o a() {
        return b;
    }

    public void a(Context context) {
        this.c = context;
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f = "budejie";
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a(th) || this.a == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            Process.killProcess(Process.myPid());
            System.exit(1);
            return;
        }
        this.a.uncaughtException(thread, th);
    }

    private boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        b(this.c);
        b(th);
        return true;
    }

    public void b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                Object obj = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str = packageInfo.versionCode + "";
                this.d.put("versionName", obj);
                this.d.put("versionCode", str);
            }
        } catch (Exception e) {
        }
        try {
            Field[] declaredFields = Build.class.getDeclaredFields();
            if (declaredFields != null) {
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    this.d.put(field.getName(), field.get(null).toString());
                }
            }
        } catch (Exception e2) {
        }
    }

    private String b(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : this.d.entrySet()) {
            String str = (String) entry.getKey();
            stringBuffer.append(str + LoginConstants.EQUAL + ((String) entry.getValue()) + "\n");
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        try {
            String str2 = this.f + "-" + this.e.format(new Date()) + "-" + System.currentTimeMillis() + ".log";
            if (!a.a()) {
                return str2;
            }
            str = c.a() + "/-budejieCrash/";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str + str2);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            fileOutputStream.close();
            return str2;
        } catch (Exception e) {
            aa.e(CrashHandler.TAG, "an error occured while writing file...");
            return null;
        }
    }
}
