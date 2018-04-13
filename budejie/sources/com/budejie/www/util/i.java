package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.widget.FrameLayout.LayoutParams;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.phonenumber.l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class i {
    private static i b;
    public boolean a = false;
    private ac c;
    private List<Activity> d = new ArrayList();
    private l e;
    private String f;
    private String g;
    private String h;

    private i() {
    }

    public static i a() {
        if (b == null) {
            synchronized (i.class) {
                if (b == null) {
                    b = new i();
                }
            }
        }
        return b;
    }

    public void a(Activity activity) {
        a().d.add(activity);
    }

    public void b(Activity activity) {
        if (!activity.isFinishing()) {
            a().d.remove(activity);
        }
    }

    public void b() {
        for (Activity finish : a().d) {
            finish.finish();
        }
        a().d.clear();
    }

    public ac c() {
        return this.c;
    }

    public int a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public float b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        return context.getResources().getDisplayMetrics().density;
    }

    public String d() {
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        aa.a(AppLinkConstants.TIME, "当前时间-->" + format);
        return format;
    }

    public void a(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.finish();
    }

    public LayoutParams c(Context context) {
        int a = a(context);
        return new LayoutParams(a, (R$styleable.Theme_Custom_history_post_content_text_color * a) / 640);
    }

    public l e() {
        return this.e;
    }

    public void a(l lVar) {
        this.e = lVar;
    }

    public String f() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public void i() {
        this.f = null;
        this.g = null;
    }
}
