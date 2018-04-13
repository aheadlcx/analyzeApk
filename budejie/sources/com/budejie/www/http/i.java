package com.budejie.www.http;

import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class i {
    public static void a(Context context, ListItemObject listItemObject) {
        BudejieApplication.a.a(RequstMethod.POST, "http://www.google-analytics.com/collect", j.a(context, listItemObject), new i$1());
    }

    public static void a(int i) {
        a(BudejieApplication.g.getString(i));
    }

    public static void a(String str) {
        EasyTracker instance = EasyTracker.getInstance(BudejieApplication.g);
        instance.set("&cd", str);
        instance.send(MapBuilder.createAppView().build());
    }

    public static void a() {
        EasyTracker.getInstance(BudejieApplication.g).set("&cd", null);
    }

    public static void a(String str, String str2, String str3, long j) {
        EasyTracker.getInstance(BudejieApplication.g).send(MapBuilder.createEvent(str, str2, str3, Long.valueOf(j)).build());
    }

    public static void a(String str, String str2, String str3) {
        EasyTracker.getInstance(BudejieApplication.g).send(MapBuilder.createEvent(str, str2, str3, null).build());
    }

    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    private static void a(Context context, int i, String str) {
        if (context == null) {
            context = BudejieApplication.g;
        }
        if (i != 0 && !TextUtils.isEmpty(((BudejieApplication) context.getApplicationContext()).i())) {
            BudejieApplication.a.a(RequstMethod.GET, "http://d.api.budejie.com/device/report/", j.a(context, i, str), new i$2());
        }
    }

    public static void a(Context context, int i) {
        a(context, i, "view");
    }

    public static void b(Context context, int i) {
        a(context, i, "play");
    }

    public static void c(Context context, int i) {
        a(context, i, "download-start");
    }

    public static void d(Context context, int i) {
        a(context, i, "download-end");
    }

    public static void e(Context context, int i) {
        a(context, i, "install");
    }
}
