package com.iflytek.cloud.thirdparty;

import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class dk {
    private static dk a;
    private ds b;
    private dh c = null;
    private Context d;
    private dl e;
    private dp f;
    private di g = new dn(this);

    private dk(Context context) {
        this.b = new ds(context);
        this.d = context;
        this.f = new dp(context);
        this.e = new dl(this.b);
        dr.a(this.d, "load settings: pkgRun=" + this.e.g() + " periodRun=" + this.e.a() + " periodUpdate=" + this.e.b());
    }

    public static synchronized dk a(Context context) {
        dk dkVar;
        synchronized (dk.class) {
            if (a == null) {
                a = new dk(context);
            }
            dkVar = a;
        }
        return dkVar;
    }

    private void a(String str, int i) {
        if (i != 0 || str == null) {
            dr.a(this.d, "get config error:" + i);
        } else {
            this.e.c(System.currentTimeMillis());
            dr.a(this.d, "get config success");
            dr.a(this.d, str);
            b(str);
            dj.a(this.d);
        }
        this.c = null;
    }

    private void b(String str) {
        if (str == null || str.length() <= 0) {
            dr.a("LaunchSettings", "loadJson empty.");
            return;
        }
        try {
            String str2 = "";
            JSONObject jSONObject = new JSONObject(new JSONTokener(str));
            long j = jSONObject.getLong("updateperiod");
            jSONObject = jSONObject.getJSONObject("launch");
            JSONArray jSONArray = jSONObject.getJSONArray("runpackage");
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                strArr[i] = jSONArray.getString(i);
                str2 = str2 + strArr[i] + VoiceWakeuperAidl.PARAMS_SEPARATE;
            }
            long j2 = jSONObject.getLong("runperiod");
            this.e.a(strArr);
            this.e.a(j);
            this.e.b(j2);
            dr.a(this.d, "parse json updateperiod:" + j + " runperiod:" + j2 + " runpackage:" + str2);
        } catch (JSONException e) {
            dr.a(this.d, "parse json error:" + e.getMessage());
        }
    }

    private String d() {
        String f = f();
        String e = e();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?t=");
        stringBuilder.append(f);
        stringBuilder.append("&p=");
        stringBuilder.append(e);
        return stringBuilder.toString();
    }

    private String e() {
        String a = this.f.a();
        String b = this.f.b();
        String f = this.e.f();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("imei=");
        if (a == null || a.length() == 0) {
            a = "null";
        }
        stringBuilder.append(a);
        stringBuilder.append(",ua=");
        stringBuilder.append(b);
        stringBuilder.append(",appid=");
        a = (f == null || f.length() == 0) ? "null" : f;
        stringBuilder.append(a);
        stringBuilder.append(",sdkver=2.0");
        stringBuilder.append(",pkg=" + this.d.getPackageName());
        f = stringBuilder.toString();
        char[] toCharArray = dq.a(f.getBytes()).toCharArray();
        for (int i = 0; i < 8; i++) {
            char c = toCharArray[i];
            toCharArray[i] = toCharArray[(toCharArray.length - 8) + i];
            toCharArray[(toCharArray.length - 8) + i] = c;
        }
        stringBuilder = new StringBuilder();
        for (char append : toCharArray) {
            stringBuilder.append(append);
        }
        dr.a(this.d, f);
        return stringBuilder.toString();
    }

    private String f() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    public void a() {
        if (this.e.d() > System.currentTimeMillis()) {
            this.e.c(System.currentTimeMillis() - this.e.b());
        }
        long currentTimeMillis = System.currentTimeMillis() - this.e.d();
        long b = this.e.b();
        if (currentTimeMillis <= b) {
            dr.a(this.d, "check update interval=" + currentTimeMillis + " period=" + b + " ret=false");
        } else if (this.c == null) {
            String str = "http://hxqd.openspeech.cn/launchconfig" + d();
            this.c = new dh(str, this.g);
            this.c.start();
            dr.a(this.d, "check update start get config ");
            dr.a(this.d, str);
        } else {
            dr.a("LaunchSettings", "mGetThread running.");
        }
    }

    public void a(long j) {
        this.e.d(j);
    }

    public void a(String str, String str2) {
        if ("appid".equals(str)) {
            this.e.a(str2);
        } else {
            dr.a("LaunchSettings", "unkown key =" + str);
        }
    }

    public boolean a(String str) {
        if (this.e.c() == null || str == null) {
            return false;
        }
        for (Object equals : this.e.c()) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean b() {
        if (this.e.e() > System.currentTimeMillis()) {
            this.e.d(System.currentTimeMillis() - this.e.a());
        }
        boolean z = false;
        long currentTimeMillis = System.currentTimeMillis() - this.e.e();
        if (this.e.a() > 0 && currentTimeMillis > this.e.a()) {
            z = true;
        }
        dr.a(this.d, "check launch interval=" + currentTimeMillis + " period=" + this.e.a() + " ret=" + z);
        return z;
    }

    public long c() {
        return this.e.a();
    }
}
