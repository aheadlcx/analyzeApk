package com.android.a.a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.view.Window;
import cn.xiaochuan.a.a;

public class c {
    static final a a;

    static {
        if (VERSION.SDK_INT >= 23) {
            a = new e();
        } else if (VERSION.SDK_INT >= 21) {
            a = new d();
        } else {
            a = new a() {
                public void a(Window window, @ColorInt int i) {
                }
            };
        }
    }

    public static void a(Activity activity, @ColorInt int i, boolean z) {
        a(activity.getWindow(), i, z);
    }

    public static void a(Window window, @ColorInt int i, boolean z) {
        if ((window.getAttributes().flags & 1024) <= 0) {
            a.a(window, i);
            b.a(window, z);
        }
    }

    public static void a(Window window, boolean z) {
        if ((window.getAttributes().flags & 1024) <= 0) {
            if (a()) {
                a.a(window, -14342604);
            } else {
                b.a(window, z);
            }
        }
    }

    public static boolean a() {
        return (a.c() || a.b() || (VERSION.SDK_INT != 21 && VERSION.SDK_INT != 22)) ? false : true;
    }
}
