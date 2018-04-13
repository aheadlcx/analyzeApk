package android.support.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class bz {
    static final Property<View, Float> a = new ca(Float.class, "translationAlpha");
    static final Property<View, Rect> b = new cb(Rect.class, "clipBounds");
    private static final ch c;
    private static Field d;
    private static boolean e;

    static {
        if (VERSION.SDK_INT >= 22) {
            c = new cg();
        } else if (VERSION.SDK_INT >= 21) {
            c = new cf();
        } else if (VERSION.SDK_INT >= 19) {
            c = new ce();
        } else if (VERSION.SDK_INT >= 18) {
            c = new cd();
        } else {
            c = new cc();
        }
    }

    static by a(@NonNull View view) {
        return c.getOverlay(view);
    }

    static cl b(@NonNull View view) {
        return c.getWindowId(view);
    }

    static void a(@NonNull View view, float f) {
        c.setTransitionAlpha(view, f);
    }

    static float c(@NonNull View view) {
        return c.getTransitionAlpha(view);
    }

    static void d(@NonNull View view) {
        c.saveNonTransitionAlpha(view);
    }

    static void e(@NonNull View view) {
        c.clearNonTransitionAlpha(view);
    }

    static void a(@NonNull View view, int i) {
        a();
        if (d != null) {
            try {
                d.setInt(view, (d.getInt(view) & -13) | i);
            } catch (IllegalAccessException e) {
            }
        }
    }

    static void a(@NonNull View view, @NonNull Matrix matrix) {
        c.transformMatrixToGlobal(view, matrix);
    }

    static void b(@NonNull View view, @NonNull Matrix matrix) {
        c.transformMatrixToLocal(view, matrix);
    }

    static void c(@NonNull View view, @Nullable Matrix matrix) {
        c.setAnimationMatrix(view, matrix);
    }

    static void a(@NonNull View view, int i, int i2, int i3, int i4) {
        c.setLeftTopRightBottom(view, i, i2, i3, i4);
    }

    private static void a() {
        if (!e) {
            try {
                d = View.class.getDeclaredField("mViewFlags");
                d.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.i("ViewUtils", "fetchViewFlagsField: ");
            }
            e = true;
        }
    }
}
