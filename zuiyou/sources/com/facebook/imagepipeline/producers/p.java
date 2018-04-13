package com.facebook.imagepipeline.producers;

import com.facebook.c.b;
import com.facebook.common.c.a;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;

public class p {
    public static int a(ImageRequest imageRequest, e eVar) {
        if (!e.c(eVar)) {
            return 1;
        }
        int b;
        float b2 = b(imageRequest, eVar);
        if (eVar.e() == b.a) {
            b = b(b2);
        } else {
            b = a(b2);
        }
        int max = Math.max(eVar.h(), eVar.g());
        c g = imageRequest.g();
        float f = g != null ? g.c : 2048.0f;
        while (((float) (max / b)) > f) {
            if (eVar.e() == b.a) {
                b *= 2;
            } else {
                b++;
            }
        }
        return b;
    }

    static float b(ImageRequest imageRequest, e eVar) {
        g.a(e.c(eVar));
        c g = imageRequest.g();
        if (g == null || g.b <= 0 || g.a <= 0 || eVar.g() == 0 || eVar.h() == 0) {
            return 1.0f;
        }
        int c = c(imageRequest, eVar);
        int i = (c == 90 || c == 270) ? 1 : 0;
        a.a("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(g.a), Integer.valueOf(g.b), Integer.valueOf(i != 0 ? eVar.h() : eVar.g()), Integer.valueOf(i != 0 ? eVar.g() : eVar.h()), Float.valueOf(((float) g.a) / ((float) (i != 0 ? eVar.h() : eVar.g()))), Float.valueOf(((float) g.b) / ((float) (i != 0 ? eVar.g() : eVar.h()))), Float.valueOf(Math.max(((float) g.a) / ((float) (i != 0 ? eVar.h() : eVar.g())), ((float) g.b) / ((float) (i != 0 ? eVar.g() : eVar.h())))), imageRequest.b().toString());
        return Math.max(((float) g.a) / ((float) (i != 0 ? eVar.h() : eVar.g())), ((float) g.b) / ((float) (i != 0 ? eVar.g() : eVar.h())));
    }

    static int a(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            if (((1.0d / (Math.pow((double) i, 2.0d) - ((double) i))) * 0.3333333432674408d) + (1.0d / ((double) i)) <= ((double) f)) {
                return i - 1;
            }
            i++;
        }
    }

    static int b(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            if (((1.0d / ((double) (i * 2))) * 0.3333333432674408d) + (1.0d / ((double) (i * 2))) <= ((double) f)) {
                return i;
            }
            i *= 2;
        }
    }

    private static int c(ImageRequest imageRequest, e eVar) {
        boolean z = false;
        if (!imageRequest.h().d()) {
            return 0;
        }
        int f = eVar.f();
        if (f == 0 || f == 90 || f == 180 || f == 270) {
            z = true;
        }
        g.a(z);
        return f;
    }
}
