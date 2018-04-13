package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build.VERSION;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import org.json.JSONArray;
import org.json.JSONObject;

class bl implements UncaughtExceptionHandler {
    private static final bl a = new bl();
    private UncaughtExceptionHandler b = null;
    private Context c = null;
    private bu d = new bu();

    public static bl a() {
        return a;
    }

    private bl() {
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
        if (this.c == null) {
            this.c = context.getApplicationContext();
        }
        this.d.a(this.c);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String th2 = th.toString();
        String str = "";
        if (!(th2 == null || th2.equals(""))) {
            try {
                String str2;
                String[] split = th2.split(Config.TRACE_TODAY_VISIT_SPLIT);
                if (th2.length() > 1) {
                    str2 = split[0];
                } else {
                    str2 = th2;
                }
                str = str2;
            } catch (Throwable e) {
                db.c(e);
                str = "";
            }
        }
        if (str == null || str.equals("")) {
            str = th2;
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        db.a(obj);
        a(System.currentTimeMillis(), obj, str, 0);
        if (!this.b.equals(this)) {
            this.b.uncaughtException(thread, th);
        }
    }

    public void a(long j, String str, String str2, int i) {
        ch.a().b(this.c, System.currentTimeMillis());
        if (this.c != null && str != null && !str.trim().equals("")) {
            try {
                String appVersionName = CooperService.a().getAppVersionName(this.c);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("t", j);
                jSONObject.put("c", str);
                jSONObject.put("y", str2);
                jSONObject.put("v", appVersionName);
                jSONObject.put(Config.EXCEPTION_CRASH_TYPE, i);
                jSONObject.put(Config.EXCEPTION_MEMORY, c());
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                jSONObject = new JSONObject();
                this.d.a(this.c, jSONObject);
                jSONObject.put(Config.SESSION_STARTTIME, 0);
                jSONObject.put(Config.SEQUENCE_INDEX, 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Config.HEADER_PART, jSONObject);
                jSONObject2.put(Config.PRINCIPAL_PART, new JSONArray());
                jSONObject2.put(Config.EVENT_PART, new JSONArray());
                jSONObject2.put(Config.EXCEPTION_PART, jSONArray);
                jSONObject2.put(Config.TRACE_PART, b());
                cu.a(this.c, Config.PREFIX_SEND_DATA + System.currentTimeMillis(), jSONObject2.toString(), false);
                db.a("Dump exception successlly");
            } catch (Throwable e) {
                db.b(e);
            }
        }
    }

    private JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.TRACE_APPLICATION_SESSION, 0);
        } catch (Exception e) {
        }
        try {
            jSONObject.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    @SuppressLint({"NewApi"})
    private JSONObject c() {
        ActivityManager activityManager = (ActivityManager) this.c.getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            if (VERSION.SDK_INT >= 16) {
                jSONObject.put("total", memoryInfo.totalMem);
            }
            jSONObject.put(Config.EXCEPTION_MEMORY_FREE, memoryInfo.availMem);
            jSONObject.put(Config.EXCEPTION_MEMORY_LOW, memoryInfo.lowMemory ? 1 : 0);
            return jSONObject;
        } catch (Exception e) {
            return jSONObject;
        }
    }
}
