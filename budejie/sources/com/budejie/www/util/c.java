package com.budejie.www.util;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;

public class c {
    @SuppressLint({"NewApi"})
    public static void a(View view) {
        if (VERSION.SDK_INT > 11) {
            view.setAlpha(com.budejie.www.h.c.a().c());
        }
    }
}
