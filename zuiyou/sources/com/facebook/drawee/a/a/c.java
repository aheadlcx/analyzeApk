package com.facebook.drawee.a.a;

import android.content.Context;
import com.facebook.common.c.a;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.d.g;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.d.j;
import javax.annotation.Nullable;

public class c {
    private static final Class<?> a = c.class;
    private static f b;
    private static volatile boolean c = false;

    private c() {
    }

    public static void a(Context context) {
        a(context, null, null);
    }

    public static void a(Context context, @Nullable h hVar) {
        a(context, hVar, null);
    }

    public static void a(Context context, @Nullable h hVar, @Nullable b bVar) {
        if (c) {
            a.d(a, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
        } else {
            c = true;
        }
        Context applicationContext = context.getApplicationContext();
        if (hVar == null) {
            j.a(applicationContext);
        } else {
            j.a(hVar);
        }
        a(applicationContext, bVar);
    }

    private static void a(Context context, @Nullable b bVar) {
        b = new f(context, bVar);
        SimpleDraweeView.a(b);
    }

    public static e a() {
        return b.a();
    }

    public static j b() {
        return j.a();
    }

    public static g c() {
        return b().i();
    }

    public static boolean d() {
        return c;
    }
}
