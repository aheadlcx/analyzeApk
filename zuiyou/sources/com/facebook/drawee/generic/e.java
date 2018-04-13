package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.common.internal.g;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.c;
import com.facebook.drawee.drawable.h;
import com.facebook.drawee.drawable.j;
import com.facebook.drawee.drawable.k;
import com.facebook.drawee.drawable.l;
import com.facebook.drawee.drawable.m;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.generic.RoundingParams.RoundingMethod;
import javax.annotation.Nullable;

public class e {
    private static final Drawable a = new ColorDrawable(0);

    @Nullable
    static Drawable a(@Nullable Drawable drawable, @Nullable n$b n_b) {
        return a(drawable, n_b, null);
    }

    @Nullable
    static Drawable a(@Nullable Drawable drawable, @Nullable n$b n_b, @Nullable PointF pointF) {
        if (drawable == null || n_b == null) {
            return drawable;
        }
        Drawable mVar = new m(drawable, n_b);
        if (pointF != null) {
            mVar.a(pointF);
        }
        return mVar;
    }

    @Nullable
    static Drawable a(@Nullable Drawable drawable, @Nullable Matrix matrix) {
        return (drawable == null || matrix == null) ? drawable : new h(drawable, matrix);
    }

    static m a(c cVar, n$b n_b) {
        Drawable a = a(cVar.a(a), n_b);
        cVar.a(a);
        g.a(a, "Parent has no child drawable!");
        return (m) a;
    }

    static Drawable a(@Nullable Drawable drawable, @Nullable RoundingParams roundingParams) {
        if (drawable == null || roundingParams == null || roundingParams.c() != RoundingMethod.OVERLAY_COLOR) {
            return drawable;
        }
        j roundedCornersDrawable = new RoundedCornersDrawable(drawable);
        a(roundedCornersDrawable, roundingParams);
        roundedCornersDrawable.a(roundingParams.d());
        return roundedCornersDrawable;
    }

    static Drawable a(@Nullable Drawable drawable, @Nullable RoundingParams roundingParams, Resources resources) {
        if (drawable == null || roundingParams == null || roundingParams.c() != RoundingMethod.BITMAP_ONLY) {
            return drawable;
        }
        if (!(drawable instanceof com.facebook.drawee.drawable.g)) {
            return b(drawable, roundingParams, resources);
        }
        c a = a((com.facebook.drawee.drawable.g) drawable);
        a.a(b(a.a(a), roundingParams, resources));
        return drawable;
    }

    private static Drawable b(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            j kVar = new k(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            a(kVar, roundingParams);
            return kVar;
        } else if (!(drawable instanceof ColorDrawable) || VERSION.SDK_INT < 11) {
            return drawable;
        } else {
            j a = l.a((ColorDrawable) drawable);
            a(a, roundingParams);
            return a;
        }
    }

    static void a(j jVar, RoundingParams roundingParams) {
        jVar.a(roundingParams.a());
        jVar.a(roundingParams.b());
        jVar.a(roundingParams.f(), roundingParams.e());
        jVar.a(roundingParams.g());
    }

    static c a(c cVar) {
        while (true) {
            Drawable a = r2.a();
            if (a == r2 || !(a instanceof c)) {
                return r2;
            }
            Object obj = (c) a;
        }
        return r2;
    }
}
