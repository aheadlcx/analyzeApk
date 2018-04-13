package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.ad.AdManager;
import com.budejie.www.bean.UserItem;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.push.e;
import com.d.a;
import com.sprite.ads.SpriteAD;
import java.util.Date;
import org.apache.commons.httpclient.HttpState;

public class ai {
    public static SharedPreferences a;
    public static String b = "fans_last_updata";
    public static String c = "follow_last_updata";
    public static String d = "label_last_updata";

    public static int a(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getInt("theme", 0);
    }

    public static void a(Context context, int i) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putInt("theme", i).commit();
    }

    public static String b(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getString("id", "");
    }

    public static void a(Context context, String str, String str2) {
        aa.e("PrefrenceUtil", "........save uid ........ ");
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putString("id", str).putString("locallogin", str2).commit();
        if (!TextUtils.isEmpty(NetWorkUtil.b(context))) {
            BudejieApplication.a.a("cookie", NetWorkUtil.b(context));
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AdManager.openAd();
            b(context, "", "");
            a.a();
            com.b.a.c();
        } else {
            new com.budejie.www.activity.labelsubscription.a().a();
            m.a(context, str);
            UserItem g = an.g(context);
            a.a((Activity) context, g);
            com.b.a.a(g);
        }
        NetWorkUtil.c(context, str);
        SpriteAD.setUid(str);
        g(context, str);
    }

    public static void b(Context context, String str, String str2) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putString("vip_status", str).putString("rong_token", str2).commit();
    }

    public static boolean c(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(a.getString("vip_status", HttpState.PREEMPTIVE_DEFAULT))) {
            return true;
        }
        return false;
    }

    public static void a(Context context, String str) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putString("weixin_key", str).commit();
    }

    public static boolean d(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        if (a.getString("weixin_key", "").length() > 0) {
            return true;
        }
        return false;
    }

    public static void a(Context context, String str, int i) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putInt(str, i).commit();
    }

    public static void b(Context context, String str) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putString("addata", str).commit();
    }

    public static void a(Context context, long j) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putLong("adtime", j).commit();
    }

    public static void b(Context context, int i) {
        context.getSharedPreferences("weiboprefer", 0).edit().putInt("record_time", i).commit();
    }

    public static int e(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getInt("record_time", 0);
    }

    public static Long c(Context context, String str) {
        return Long.valueOf(context.getSharedPreferences("weiboprefer", 0).getLong(str, System.currentTimeMillis()));
    }

    public static void d(Context context, String str) {
        context.getSharedPreferences("weiboprefer", 0).edit().putLong(str, System.currentTimeMillis()).commit();
    }

    public static Long f(Context context) {
        return Long.valueOf(context.getSharedPreferences("weiboprefer", 0).getLong("last_use_app_time", 0));
    }

    public static void g(Context context) {
        context.getSharedPreferences("weiboprefer", 0).edit().putLong("last_use_app_time", System.currentTimeMillis()).commit();
    }

    public static void h(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        sharedPreferences.edit().putLong("picture_hot_upatetime", new Date(System.currentTimeMillis()).getTime()).commit();
    }

    public static long i(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        if (sharedPreferences.getLong("picture_hot_upatetime", 0) != 0) {
            return sharedPreferences.getLong("picture_hot_upatetime", 0);
        }
        h(context);
        return 0;
    }

    public static void j(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        sharedPreferences.edit().putLong("duanzi_hot_updatetime", new Date(System.currentTimeMillis()).getTime()).commit();
    }

    public static long k(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        if (sharedPreferences.getLong("duanzi_hot_updatetime", 0) != 0) {
            return sharedPreferences.getLong("duanzi_hot_updatetime", 0);
        }
        j(context);
        return 0;
    }

    public static void l(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        sharedPreferences.edit().putLong("voice_hot_updatetime", new Date(System.currentTimeMillis()).getTime()).commit();
    }

    public static long m(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("weiboprefer", 0);
        if (sharedPreferences.getLong("voice_hot_updatetime", 0) != 0) {
            return sharedPreferences.getLong("voice_hot_updatetime", 0);
        }
        l(context);
        return 0;
    }

    public static String n(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("channel_id", "");
    }

    public static String o(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("cloud_uid", "");
    }

    public static void e(Context context, String str) {
        context.getSharedPreferences("weiboprefer", 0).edit().putString("mi_regid", str).commit();
        c(context, 1);
    }

    public static String p(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("mi_regid", "");
    }

    public static void f(Context context, String str) {
        context.getSharedPreferences("weiboprefer", 0).edit().putString("push_result_extra", str).commit();
        c(context, 1);
    }

    public static String q(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("push_result_extra", "");
    }

    public static void c(Context context, int i) {
        context.getSharedPreferences("weiboprefer", 0).edit().putInt("push_type_enable", i).commit();
    }

    public static int r(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getInt("push_type_enable", 1);
    }

    public static void g(Context context, String str) {
        int r = r(context);
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "1";
        if (r == 0) {
            str2 = n(context);
            str3 = o(context);
        } else if (r == 1) {
            str4 = p(context);
            aa.a("push", "取出米push的注册id:" + str4);
        }
        e.a(context, str, str2, str3, str4, "2");
    }

    public static long s(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getLong("hot_update_all_time", 0);
    }

    public static void b(Context context, long j) {
        context.getSharedPreferences("weiboprefer", 0).edit().putLong("hot_update_all_time", j).commit();
    }

    public static String t(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getString("device_status", "0");
    }

    public static void h(Context context, String str) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putString("device_status", str).commit();
    }

    public static int i(Context context, String str) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getInt(str + "_guide_post_type", -1);
    }

    public static void b(Context context, String str, int i) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putInt(str + "_guide_post_type", i).commit();
    }

    public static boolean u(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getBoolean("net_not_wifi_tips", true);
    }

    public static void a(Context context, boolean z) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putBoolean("net_not_wifi_tips", z).commit();
    }

    public static int v(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getInt("push_ixintui_status", 0);
    }

    public static void d(Context context, int i) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putInt("push_ixintui_status", i).commit();
    }

    public static int w(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getInt("update_ignore_version_code", 0);
    }

    public static void e(Context context, int i) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putInt("update_ignore_version_code", i).commit();
    }

    public static boolean x(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        return a.getBoolean("show_horizontal_scrollable_tips", false);
    }

    public static void y(Context context) {
        a = context.getSharedPreferences("weiboprefer", 0);
        a.edit().putBoolean("show_horizontal_scrollable_tips", true).commit();
    }
}
