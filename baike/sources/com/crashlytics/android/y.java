package com.crashlytics.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.v;

final class y {
    public final String a;
    public final int b;
    public final int c;
    public final int d;

    private y(String str, int i, int i2, int i3) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static y a(Context context, String str) {
        if (str != null) {
            try {
                int h = C0003ab.h(context);
                v.a().b().a(Crashlytics.TAG, "App icon resource ID is " + h);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), h, options);
                return new y(str, h, options.outWidth, options.outHeight);
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Failed to load icon", e);
            }
        }
        return null;
    }
}
