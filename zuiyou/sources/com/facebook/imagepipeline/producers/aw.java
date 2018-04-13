package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;

public final class aw {
    public static boolean a(int i, int i2, c cVar) {
        if (cVar == null) {
            if (((float) a(i)) < 2048.0f || a(i2) < 2048) {
                return false;
            }
            return true;
        } else if (a(i) < cVar.a || a(i2) < cVar.b) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean a(e eVar, c cVar) {
        if (eVar == null) {
            return false;
        }
        switch (eVar.f()) {
            case 90:
            case 270:
                return a(eVar.h(), eVar.g(), cVar);
            default:
                return a(eVar.g(), eVar.h(), cVar);
        }
    }

    public static int a(int i) {
        return (int) (((float) i) * 1.3333334f);
    }
}
