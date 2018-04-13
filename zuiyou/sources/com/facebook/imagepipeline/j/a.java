package com.facebook.imagepipeline.j;

import android.graphics.Bitmap;
import com.facebook.cache.common.b;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.nativecode.NativeBlurFilter;
import java.util.Locale;
import javax.annotation.Nullable;

public class a extends com.facebook.imagepipeline.request.a {
    private final int b;
    private final int c;
    private b d;

    public a(int i) {
        this(3, i);
    }

    public a(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i2 <= 0) {
            z2 = false;
        }
        g.a(z2);
        this.b = i;
        this.c = i2;
    }

    public void a(Bitmap bitmap) {
        NativeBlurFilter.a(bitmap, this.b, this.c);
    }

    @Nullable
    public b a() {
        if (this.d == null) {
            this.d = new com.facebook.cache.common.g(String.format((Locale) null, "i%dr%d", new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c)}));
        }
        return this.d;
    }
}
