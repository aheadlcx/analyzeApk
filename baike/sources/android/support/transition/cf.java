package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class cf extends ce {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;
    private static Method e;
    private static boolean f;

    cf() {
    }

    public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        a();
        if (a != null) {
            try {
                a.invoke(view, new Object[]{matrix});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        b();
        if (c != null) {
            try {
                c.invoke(view, new Object[]{matrix});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
        c();
        if (e != null) {
            try {
                e.invoke(view, new Object[]{matrix});
            } catch (InvocationTargetException e) {
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    private void a() {
        if (!b) {
            try {
                a = View.class.getDeclaredMethod("transformMatrixToGlobal", new Class[]{Matrix.class});
                a.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", e);
            }
            b = true;
        }
    }

    private void b() {
        if (!d) {
            try {
                c = View.class.getDeclaredMethod("transformMatrixToLocal", new Class[]{Matrix.class});
                c.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", e);
            }
            d = true;
        }
    }

    private void c() {
        if (!f) {
            try {
                e = View.class.getDeclaredMethod("setAnimationMatrix", new Class[]{Matrix.class});
                e.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", e);
            }
            f = true;
        }
    }
}
