package com.edmodo.cropper.a;

import android.content.Context;
import android.util.Pair;
import android.util.TypedValue;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.handle.Handle;

public class b {
    public static float a(Context context) {
        return TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
    }

    public static Handle a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (a(f, f2, f3, f4, f7)) {
            return Handle.TOP_LEFT;
        }
        if (a(f, f2, f5, f4, f7)) {
            return Handle.TOP_RIGHT;
        }
        if (a(f, f2, f3, f6, f7)) {
            return Handle.BOTTOM_LEFT;
        }
        if (a(f, f2, f5, f6, f7)) {
            return Handle.BOTTOM_RIGHT;
        }
        if (c(f, f2, f3, f4, f5, f6) && a()) {
            return Handle.CENTER;
        }
        if (a(f, f2, f3, f5, f4, f7)) {
            return Handle.TOP;
        }
        if (a(f, f2, f3, f5, f6, f7)) {
            return Handle.BOTTOM;
        }
        if (b(f, f2, f3, f4, f6, f7)) {
            return Handle.LEFT;
        }
        if (b(f, f2, f5, f4, f6, f7)) {
            return Handle.RIGHT;
        }
        return (!c(f, f2, f3, f4, f5, f6) || a()) ? null : Handle.CENTER;
    }

    public static Pair<Float, Float> a(Handle handle, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.0f;
        if (handle == null) {
            return null;
        }
        float f8;
        switch (handle) {
            case TOP_LEFT:
                f8 = f3 - f;
                f7 = f4 - f2;
                break;
            case TOP_RIGHT:
                f8 = f5 - f;
                f7 = f4 - f2;
                break;
            case BOTTOM_LEFT:
                f8 = f3 - f;
                f7 = f6 - f2;
                break;
            case BOTTOM_RIGHT:
                f8 = f5 - f;
                f7 = f6 - f2;
                break;
            case LEFT:
                f8 = f3 - f;
                break;
            case TOP:
                f8 = 0.0f;
                f7 = f4 - f2;
                break;
            case RIGHT:
                f8 = f5 - f;
                break;
            case BOTTOM:
                f8 = 0.0f;
                f7 = f6 - f2;
                break;
            case CENTER:
                f8 = ((f5 + f3) / 2.0f) - f;
                f7 = ((f4 + f6) / 2.0f) - f2;
                break;
            default:
                f8 = 0.0f;
                break;
        }
        return new Pair(Float.valueOf(f8), Float.valueOf(f7));
    }

    private static boolean a(float f, float f2, float f3, float f4, float f5) {
        if (Math.abs(f - f3) > f5 || Math.abs(f2 - f4) > f5) {
            return false;
        }
        return true;
    }

    private static boolean a(float f, float f2, float f3, float f4, float f5, float f6) {
        if (f <= f3 || f >= f4 || Math.abs(f2 - f5) > f6) {
            return false;
        }
        return true;
    }

    private static boolean b(float f, float f2, float f3, float f4, float f5, float f6) {
        if (Math.abs(f - f3) > f6 || f2 <= f4 || f2 >= f5) {
            return false;
        }
        return true;
    }

    private static boolean c(float f, float f2, float f3, float f4, float f5, float f6) {
        if (f <= f3 || f >= f5 || f2 <= f4 || f2 >= f6) {
            return false;
        }
        return true;
    }

    private static boolean a() {
        return !CropOverlayView.c();
    }
}
