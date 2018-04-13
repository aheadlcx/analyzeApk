package com.budejie.www.activity.video.barrage.danmaku.c;

import android.app.ActivityManager;
import android.content.Context;

public class a {
    public static int a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }
}
