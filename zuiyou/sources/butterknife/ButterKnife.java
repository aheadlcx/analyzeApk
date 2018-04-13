package butterknife;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ButterKnife {
    @VisibleForTesting
    static final Map<Class<?>, Constructor<? extends Unbinder>> a = new LinkedHashMap();
    private static boolean b = false;

    public interface Action<T extends View> {
    }

    public interface Setter<T extends View, V> {
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    @UiThread
    @NonNull
    public static Unbinder a(@NonNull Activity activity) {
        return b(activity, activity.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder a(@NonNull Dialog dialog) {
        return b(dialog, dialog.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder a(@NonNull Object obj, @NonNull View view) {
        return b(obj, view);
    }

    private static Unbinder b(@NonNull Object obj, @NonNull View view) {
        Throwable e;
        Class cls = obj.getClass();
        if (b) {
            Log.d("ButterKnife", "Looking up binding for " + cls.getName());
        }
        Constructor a = a(cls);
        if (a == null) {
            return Unbinder.a;
        }
        try {
            return (Unbinder) a.newInstance(new Object[]{obj, view});
        } catch (Throwable e2) {
            throw new RuntimeException("Unable to invoke " + a, e2);
        } catch (Throwable e22) {
            throw new RuntimeException("Unable to invoke " + a, e22);
        } catch (InvocationTargetException e3) {
            e22 = e3.getCause();
            if (e22 instanceof RuntimeException) {
                throw ((RuntimeException) e22);
            } else if (e22 instanceof Error) {
                throw ((Error) e22);
            } else {
                throw new RuntimeException("Unable to create binding instance.", e22);
            }
        }
    }

    @UiThread
    @CheckResult
    @Nullable
    private static Constructor<? extends Unbinder> a(Class<?> cls) {
        Constructor<? extends Unbinder> constructor = (Constructor) a.get(cls);
        if (constructor == null) {
            String name = cls.getName();
            if (name.startsWith("android.") || name.startsWith("java.")) {
                if (b) {
                    Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
                }
                return null;
            }
            try {
                constructor = cls.getClassLoader().loadClass(name + "_ViewBinding").getConstructor(new Class[]{cls, View.class});
                if (b) {
                    Log.d("ButterKnife", "HIT: Loaded binding class and constructor.");
                }
            } catch (ClassNotFoundException e) {
                if (b) {
                    Log.d("ButterKnife", "Not found. Trying superclass " + cls.getSuperclass().getName());
                }
                constructor = a(cls.getSuperclass());
            } catch (Throwable e2) {
                throw new RuntimeException("Unable to find binding constructor for " + name, e2);
            }
            a.put(cls, constructor);
            return constructor;
        } else if (!b) {
            return constructor;
        } else {
            Log.d("ButterKnife", "HIT: Cached in binding map.");
            return constructor;
        }
    }
}
