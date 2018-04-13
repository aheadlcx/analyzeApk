package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TbsLogClient {
    static TbsLogClient a = null;
    static File c = null;
    static String d = null;
    static byte[] e = null;
    TextView b;
    private SimpleDateFormat f = null;
    private Context g = null;
    private String h = "";

    private class a implements Runnable {
        String a = null;
        final /* synthetic */ TbsLogClient b;

        a(TbsLogClient tbsLogClient, String str) {
            this.b = tbsLogClient;
            this.a = str;
        }

        public void run() {
            if (this.b.b != null) {
                this.b.b.append(this.a + "\n");
            }
        }
    }

    public TbsLogClient(Context context) {
        try {
            this.g = context.getApplicationContext();
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception e) {
            this.f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    private void a() {
        try {
            if (c != null) {
                return;
            }
            if (Environment.getExternalStorageState().equals("mounted")) {
                String a = k.a(this.g, 6);
                if (a == null) {
                    c = null;
                    return;
                }
                c = new File(a, "tbslog.txt");
                d = LogFileUtils.createKey();
                e = LogFileUtils.createHeaderText(c.getName(), d);
                return;
            }
            c = null;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    public void d(String str, String str2) {
    }

    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    public void i(String str, String str2) {
    }

    public void setLogView(TextView textView) {
        this.b = textView;
    }

    public void showLog(String str) {
        if (this.b != null) {
            this.b.post(new a(this, str));
        }
    }

    public void v(String str, String str2) {
    }

    public void w(String str, String str2) {
    }

    public void writeLog(String str) {
        this.h += this.f.format(Long.valueOf(System.currentTimeMillis())) + " pid=" + Process.myPid() + " tid=" + Process.myTid() + str + "\n";
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            writeLogToDisk();
        }
    }

    public void writeLogToDisk() {
        a();
        if (c != null) {
            LogFileUtils.writeDataToStorage(c, d, e, this.h, true);
            this.h = "";
        }
    }
}
