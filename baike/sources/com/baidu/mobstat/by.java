package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Timer;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

class by {
    private static by a = new by();
    private boolean b = false;
    private int c = 0;
    private int d = 1;
    private SendStrategyEnum e = SendStrategyEnum.APP_START;
    private Timer f;
    private Handler g;

    public static by a() {
        return a;
    }

    private by() {
        HandlerThread handlerThread = new HandlerThread("LogSenderThread");
        handlerThread.start();
        this.g = new Handler(handlerThread.getLooper());
    }

    public void a(int i) {
        if (i >= 0 && i <= 30) {
            this.c = i;
        }
    }

    public void a(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (!sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.e = sendStrategyEnum;
            bj.a().a(context, this.e.ordinal());
            if (sendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                bj.a().b(context, 24);
            }
        } else if (i <= 0 || i > 24) {
            db.c("timeInterval is invalid, new strategy does not work");
        } else {
            this.d = i;
            this.e = SendStrategyEnum.SET_TIME_INTERVAL;
            bj.a().a(context, this.e.ordinal());
            bj.a().b(context, this.d);
        }
        this.b = z;
        bj.a().a(context, this.b);
        db.a("sstype is:" + this.e.name() + " And timeInterval is:" + this.d + " And mOnlyWifi:" + this.b);
    }

    public void a(Context context) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        if (context != null) {
            this.g.post(new bz(this, context));
        }
    }

    public void b(Context context) {
        Context applicationContext = context.getApplicationContext();
        long j = (long) (this.d * 3600000);
        this.f = new Timer();
        this.f.schedule(new cb(this, applicationContext), j, j);
    }

    private void c(Context context) {
        if (!this.b || de.n(context)) {
            this.g.post(new cc(this, context));
        }
    }

    private static void b(Context context, String str, String str2) {
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(str2);
        } catch (Exception e) {
        }
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get(Config.TRACE_PART);
                jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
            } catch (Exception e2) {
            }
            cu.a(context, str, jSONObject.toString(), false);
        }
    }

    public void a(Context context, String str) {
        cu.a(context, Config.PREFIX_SEND_DATA + System.currentTimeMillis(), str, false);
    }

    private boolean b(Context context, String str) {
        boolean z = false;
        if (!this.b || de.n(context)) {
            try {
                c(context, Config.LOG_SEND_URL, str);
                z = true;
            } catch (Throwable e) {
                db.c(e);
            }
            db.a("send log data over. result = " + z + "; data = " + str);
        }
        return z;
    }

    private String c(Context context, String str, String str2) {
        if (str.startsWith("https://")) {
            return d(context, str, str2);
        }
        return e(context, str, str2);
    }

    private String d(Context context, String str, String str2) {
        HttpURLConnection d = cu.d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty("Content-Type", "gzip");
        d.connect();
        db.a("AdUtil.httpPost connected");
        try {
            String readLine;
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(d.getOutputStream())));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            for (readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                stringBuilder.append(readLine);
            }
            int contentLength = d.getContentLength();
            if (d.getResponseCode() == 200 && contentLength == 0) {
                readLine = stringBuilder.toString();
                return readLine;
            }
            throw new IOException("http code = " + d.getResponseCode() + "; contentResponse = " + stringBuilder);
        } finally {
            d.disconnect();
        }
    }

    private String e(Context context, String str, String str2) {
        db.a("httpPostEncrypt");
        HttpURLConnection d = cu.d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty("Content-Type", "gzip");
        byte[] a = cs.a();
        byte[] b = cs.b();
        d.setRequestProperty(PayPWDUniversalActivity.KEY, dc.a(a));
        d.setRequestProperty("iv", dc.a(b));
        a = cs.a(a, b, str2.getBytes("utf-8"));
        d.connect();
        db.a("AdUtil.httpPost connected");
        try {
            String readLine;
            OutputStream gZIPOutputStream = new GZIPOutputStream(d.getOutputStream());
            gZIPOutputStream.write(a);
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            for (readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                stringBuilder.append(readLine);
            }
            int contentLength = d.getContentLength();
            if (d.getResponseCode() == 200 && contentLength == 0) {
                readLine = stringBuilder.toString();
                return readLine;
            }
            throw new IOException("http code = " + d.getResponseCode() + "; contentResponse = " + stringBuilder);
        } finally {
            d.disconnect();
        }
    }
}
