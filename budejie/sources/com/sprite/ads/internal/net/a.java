package com.sprite.ads.internal.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.webkit.WebView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.login.LoginConstants;
import com.sprite.ads.Constants;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.utils.NetUtil;
import com.sprite.ads.internal.utils.ViewUtil;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import okhttp3.c;
import okhttp3.d;
import okhttp3.e;
import okhttp3.f;
import okhttp3.r;
import okhttp3.w;
import okhttp3.z;

public class a {
    public static Map<String, String> a = new HashMap();
    private static Context b;
    private static c c;
    private static w d = new w();
    private static int e = 10485760;

    public static String a() {
        return (String) a.get("market");
    }

    public static String a(String str, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        if (!map.isEmpty()) {
            stringBuilder.append("?");
        }
        for (Entry entry : map.entrySet()) {
            stringBuilder.append(((String) entry.getKey()) + LoginConstants.EQUAL + ((String) entry.getValue()) + "&");
        }
        return stringBuilder.substring(0, stringBuilder.lastIndexOf("&")).toString();
    }

    public static void a(Context context) {
        a.put("sdk", Constants.SDK_VERSION);
        a.put("os", "android-" + VERSION.RELEASE);
        a.put("model", Build.MODEL);
        a.put(com.alipay.sdk.app.statistic.c.a, NetUtil.getNetType(context));
        a.put("client", com.sprite.ads.internal.utils.a.b(context));
        a.put("crack", com.sprite.ads.internal.utils.a.b());
        a.put(DeviceInfo.TAG_ANDROID_ID, com.sprite.ads.internal.utils.a.a(context));
        a.put("mac", com.sprite.ads.internal.utils.a.c(context));
        a.put("imei", com.sprite.ads.internal.utils.a.d(context));
        a.put("telcom", com.sprite.ads.internal.utils.a.e(context));
        a.put("density", ViewUtil.DENSITY + "");
        a.put("User-Agent", d(context));
    }

    public static void a(String str) {
        a.put("ver", str);
    }

    public static void a(String str, b bVar) {
        try {
            d.y().a().a(new okhttp3.y.a().a(str).a().a(new okhttp3.r.a().a("User-Agent", (String) a.get("User-Agent")).a()).b()).a(new a$1(bVar));
        } catch (Throwable e) {
            e.printStackTrace();
            bVar.a(new ADNetException(e));
        }
    }

    public static void a(String str, f fVar) {
        try {
            d.y().b(5000, TimeUnit.MILLISECONDS).c(5000, TimeUnit.MILLISECONDS).a(5000, TimeUnit.MILLISECONDS).a().a(new okhttp3.y.a().a(str).a().a(new okhttp3.r.a().a("User-Agent", (String) a.get("User-Agent")).a()).b()).a(fVar);
        } catch (Exception e) {
            e.printStackTrace();
            fVar.onFailure(null, null);
        }
    }

    public static void a(String str, z zVar, f fVar) {
        try {
            d.y().b(5000, TimeUnit.MILLISECONDS).c(5000, TimeUnit.MILLISECONDS).a(5000, TimeUnit.MILLISECONDS).a().a(new okhttp3.y.a().a(str).a(zVar).a(new okhttp3.r.a().a("User-Agent", (String) a.get("User-Agent")).a()).b()).a(fVar);
        } catch (Exception e) {
            e.printStackTrace();
            fVar.onFailure(null, null);
        }
    }

    public static void a(Map<String, String> map, b bVar) {
        try {
            d.y().a(c).a().a(new okhttp3.y.a().a(d.a).a(a("http://dspsdk.spriteapp.com/ad/report", (Map) map)).a(b()).b()).a(new a$3(bVar));
        } catch (Exception e) {
            e.printStackTrace();
            bVar.a(new ADNetException());
        }
    }

    public static e b(String str, b bVar) {
        try {
            e g = g("http://dspsdk.spriteapp.com/ad/get?ad=" + str);
            g.a(new a$2(bVar));
            return g;
        } catch (Throwable e) {
            e.printStackTrace();
            bVar.a(new ADNetException(e));
            return null;
        }
    }

    public static r b() {
        okhttp3.r.a aVar = new okhttp3.r.a();
        for (Entry entry : a.entrySet()) {
            aVar.a((String) entry.getKey(), (String) entry.getValue());
        }
        return aVar.a();
    }

    public static void b(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        c = new c(new File(externalCacheDir, "httpCache"), (long) e);
        b = context.getApplicationContext();
    }

    public static void b(String str) {
        a.put("key", str);
    }

    public static void c() {
        try {
            ADLog.d("clear sprite ad cache");
            c g = d.g();
            if (g != null) {
                g.a();
                ADLog.d("sprite cache has cleared");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SDK_NAME, 0);
        String string = sharedPreferences.getString("market", "");
        String a = a();
        if (!string.equals(a)) {
            new Thread(new a$4(a, sharedPreferences)).start();
        }
    }

    public static void c(String str) {
        a.put("screen", str);
    }

    public static String d(Context context) {
        return new WebView(context).getSettings().getUserAgentString() + "/" + Constants.SDK_NAME + Constants.SDK_VERSION;
    }

    public static void d(String str) {
        Object encode;
        try {
            encode = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encode = "";
        }
        a.put("market", encode);
        if (b != null) {
            c(b);
        }
    }

    public static void e(String str) {
        a.put(HistoryOpenHelper.COLUMN_UID, str);
    }

    public static void f(String str) {
        a.put("app", str);
    }

    private static e g(String str) {
        return d.y().a(c).b(3, TimeUnit.SECONDS).a(3, TimeUnit.SECONDS).c(3, TimeUnit.SECONDS).a().a(new okhttp3.y.a().a(str).a(d.b).a(b()).b());
    }
}
