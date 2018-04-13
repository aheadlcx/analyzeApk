package com.bumptech.glide.load.engine.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

public class j {
    private final int a;
    private final int b;
    private final Context c;

    interface b {
        int a();

        int b();
    }

    private static class a implements b {
        private final DisplayMetrics a;

        public a(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        public int a() {
            return this.a.widthPixels;
        }

        public int b() {
            return this.a.heightPixels;
        }
    }

    public j(Context context) {
        this(context, (ActivityManager) context.getSystemService("activity"), new a(context.getResources().getDisplayMetrics()));
    }

    j(Context context, ActivityManager activityManager, b bVar) {
        this.c = context;
        int a = a(activityManager);
        int a2 = (bVar.a() * bVar.b()) * 4;
        int i = a2 * 4;
        a2 *= 2;
        if (a2 + i <= a) {
            this.b = a2;
            this.a = i;
        } else {
            int round = Math.round(((float) a) / 6.0f);
            this.b = round * 2;
            this.a = round * 4;
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            Log.d("MemorySizeCalculator", "Calculated memory cache size: " + a(this.b) + " pool size: " + a(this.a) + " memory class limited? " + (a2 + i > a) + " max size: " + a(a) + " memoryClass: " + activityManager.getMemoryClass() + " isLowMemoryDevice: " + b(activityManager));
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    private static int a(ActivityManager activityManager) {
        return Math.round((b(activityManager) ? 0.33f : 0.4f) * ((float) ((activityManager.getMemoryClass() * 1024) * 1024)));
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.c, (long) i);
    }

    @TargetApi(19)
    private static boolean b(ActivityManager activityManager) {
        if (VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return VERSION.SDK_INT < 11;
    }
}
