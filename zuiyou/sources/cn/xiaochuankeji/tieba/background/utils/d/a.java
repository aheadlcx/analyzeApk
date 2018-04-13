package cn.xiaochuankeji.tieba.background.utils.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.text.TextUtils;
import cn.htjyb.c.e;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static String a = "http://video.izuiyou.com/";
    public static String b = "/smartdns/get";
    public static String c = "http://diagnosis.izuiyou.com/diagnosis/profiling";
    public static String d = "http://api.izuiyou.com/diagnosis/app_report";
    public static String e = "http://api.izuiyou.com/diagnosis/app_report";
    public static String f = null;
    private static String g = "api.izuiyou.com";
    private static String h = "file.izuiyou.com";
    private static String i = "h5.izuiyou.com";
    private static String j = "share.izuiyou.com";
    private static String k;

    public static int a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getState() == State.CONNECTED) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    return 1;
                }
                if (type == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (subtype == 4 || subtype == 1 || subtype == 2) {
                        return 2;
                    }
                    if (subtype == 3 || subtype == 8 || subtype == 6 || subtype == 5 || subtype == 12) {
                        return 2;
                    }
                    if (subtype == 13) {
                        return 4;
                    }
                }
            }
        }
        return 9;
    }

    public static void a(JSONObject jSONObject) {
        if (f == null) {
            f = e.a(BaseApplication.getAppContext());
        }
        int i = VERSION.SDK_INT;
        try {
            jSONObject.put("h_av", f);
            jSONObject.put("h_dt", 0);
            jSONObject.put("h_os", i);
            jSONObject.put("h_app", "zuiyou");
            jSONObject.put("h_model", Build.MODEL);
            jSONObject.put("h_did", AppController.instance().deviceID());
            jSONObject.put("h_nt", a(BaseApplication.getAppContext()));
            jSONObject.put("h_m", cn.xiaochuankeji.tieba.background.a.g().c());
            jSONObject.put("h_ch", AppController.instance().packageChannel());
            jSONObject.put("h_ts", System.currentTimeMillis());
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            if (TextUtils.isEmpty(k)) {
                k = System.getString(BaseApplication.getAppContext().getContentResolver(), "android_id");
            }
            jSONObject.put("android_id", k);
        } catch (JSONException e) {
        }
    }

    public static String a() {
        JSONObject jSONObject = new JSONObject();
        if (f == null) {
            f = e.a(BaseApplication.getAppContext());
        }
        int i = VERSION.SDK_INT;
        try {
            jSONObject.put("h_av", f);
            jSONObject.put("h_dt", 0);
            jSONObject.put("h_os", i);
            jSONObject.put("h_model", Build.MODEL);
            jSONObject.put("h_did", AppController.instance().deviceID());
            jSONObject.put("h_nt", a(BaseApplication.getAppContext()));
            jSONObject.put("h_m", cn.xiaochuankeji.tieba.background.a.g().c());
            jSONObject.put("h_ch", AppController.instance().packageChannel());
            jSONObject.put("h_ts", System.currentTimeMillis());
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            if (TextUtils.isEmpty(k)) {
                k = System.getString(BaseApplication.getAppContext().getContentResolver(), "android_id");
            }
            jSONObject.put("android_id", k);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        return jSONObject;
    }

    public static String a(String str, long j, String str2) {
        String str3 = "http://" + c() + str + j;
        if (str2 == null || TextUtils.isEmpty(str2)) {
            return str3;
        }
        return str3 + str2;
    }

    public static String c() {
        return h;
    }

    public static void d() {
        g = "api.izuiyou.com";
        h = "file.izuiyou.com";
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            g = str;
        }
    }

    public static String b(String str) {
        String e = e();
        String str2 = "http";
        cn.xiaochuankeji.tieba.background.utils.c.a.c().b(e);
        if (!cn.xiaochuankeji.tieba.background.utils.c.a.c().F()) {
            str2 = "http";
        } else if (str.contains("/upload/img") || str.contains("/zyapi/upload/blockdata") || str.contains("/upload/audio")) {
            str2 = "http";
        } else {
            str2 = "https";
        }
        return str2 + "://" + e + str;
    }

    public static String e() {
        return g;
    }

    public static void c(String str) {
        i = str;
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return str.replace("$$", i);
    }

    public static void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            j = str;
        }
    }

    public static String f(String str) {
        return a(str, j);
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return str.replace("$$", str2);
    }

    public static String a(Post post) {
        String str = "https://$$/detail/";
        String str2 = null;
        if (!TextUtils.isEmpty(post.campaignId)) {
            str2 = cn.xiaochuankeji.tieba.background.utils.c.a.c().a(post.campaignId);
        }
        if (TextUtils.isEmpty(str2)) {
            return f(str) + post._ID;
        }
        return a(str, str2) + post.campaignId + "/" + post._ID;
    }

    public static String a(long j, long j2) {
        return f("https://$$/review/" + j + "/" + j2);
    }

    public static String a(long j) {
        return f("https://$$/topic/" + j);
    }

    public static String b(long j) {
        return f("https://$$/theme/" + j);
    }

    public static String c(long j) {
        return f("https://$$/article/" + j);
    }

    public static String b(long j, long j2) {
        String str;
        if (0 == j2) {
            str = "https://$$/ugc/" + j;
        } else {
            str = "https://$$/ugc/" + j + "/" + j2;
        }
        return f(str);
    }

    public static String d(long j) {
        return f("https://$$/shengkong/" + j);
    }

    public static String f() {
        return d("https://$$/home");
    }
}
