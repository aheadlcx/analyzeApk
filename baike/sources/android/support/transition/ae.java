package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.GhostViewImpl.Creator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ae implements GhostViewImpl {
    private static Class<?> a;
    private static boolean b;
    private static Method c;
    private static boolean d;
    private static Method e;
    private static boolean f;
    private final View g;

    static class a implements Creator {
        a() {
        }

        public GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
            ae.f();
            if (ae.c != null) {
                try {
                    return new ae((View) ae.c.invoke(null, new Object[]{view, viewGroup, matrix}));
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
            return null;
        }

        public void removeGhost(View view) {
            ae.g();
            if (ae.e != null) {
                try {
                    ae.e.invoke(null, new Object[]{view});
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }
    }

    private ae(@NonNull View view) {
        this.g = view;
    }

    public void setVisibility(int i) {
        this.g.setVisibility(i);
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
    }

    private static void e() {
        if (!b) {
            try {
                a = Class.forName("android.view.GhostView");
            } catch (Throwable e) {
                Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e);
            }
            b = true;
        }
    }

    private static void f() {
        if (!d) {
            try {
                e();
                c = a.getDeclaredMethod("addGhost", new Class[]{View.class, ViewGroup.class, Matrix.class});
                c.setAccessible(true);
            } catch (Throwable e) {
                Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e);
            }
            d = true;
        }
    }

    private static void g() {
        if (!f) {
            try {
                e();
                e = a.getDeclaredMethod("removeGhost", new Class[]{View.class});
                e.setAccessible(true);
            } catch (Throwable e) {
                Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e);
            }
            f = true;
        }
    }
}
