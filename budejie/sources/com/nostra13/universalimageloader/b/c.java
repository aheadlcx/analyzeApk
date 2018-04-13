package com.nostra13.universalimageloader.b;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.c.a;

public final class c {
    private static com.nostra13.universalimageloader.core.assist.c a;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        a = new com.nostra13.universalimageloader.core.assist.c(max, max);
    }

    public static com.nostra13.universalimageloader.core.assist.c a(a aVar, com.nostra13.universalimageloader.core.assist.c cVar) {
        int a = aVar.a();
        if (a <= 0) {
            a = cVar.a();
        }
        int b = aVar.b();
        if (b <= 0) {
            b = cVar.b();
        }
        return new com.nostra13.universalimageloader.core.assist.c(a, b);
    }

    public static int a(com.nostra13.universalimageloader.core.assist.c cVar, com.nostra13.universalimageloader.core.assist.c cVar2, ViewScaleType viewScaleType, boolean z) {
        int a = cVar.a();
        int b = cVar.b();
        int a2 = cVar2.a();
        int b2 = cVar2.b();
        int i;
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!z) {
                    a = Math.max(a / a2, b / b2);
                    break;
                }
                i = a / 2;
                b /= 2;
                a = 1;
                while (true) {
                    if (i / a <= a2 && b / a <= b2) {
                        break;
                    }
                    a *= 2;
                }
                break;
            case CROP:
                if (!z) {
                    a = Math.min(a / a2, b / b2);
                    break;
                }
                i = a / 2;
                b /= 2;
                a = 1;
                while (i / a > a2 && b / a > b2) {
                    a *= 2;
                }
                break;
            default:
                a = 1;
                break;
        }
        if (a < 1) {
            return 1;
        }
        return a;
    }

    public static int a(com.nostra13.universalimageloader.core.assist.c cVar) {
        int a = cVar.a();
        int b = cVar.b();
        return Math.max((int) Math.ceil((double) (((float) a) / ((float) a.a()))), (int) Math.ceil((double) (((float) b) / ((float) a.b()))));
    }

    public static float b(com.nostra13.universalimageloader.core.assist.c cVar, com.nostra13.universalimageloader.core.assist.c cVar2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int a = cVar.a();
        int b = cVar.b();
        int a2 = cVar2.a();
        int b2 = cVar2.b();
        float f = ((float) a) / ((float) a2);
        float f2 = ((float) b) / ((float) b2);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (((float) a) / f2);
            a2 = b2;
        } else {
            i = a2;
            a2 = (int) (((float) b) / f);
        }
        if ((z || i >= a || r1 >= b) && (!z || i == a || r1 == b)) {
            return 1.0f;
        }
        return ((float) i) / ((float) a);
    }
}
