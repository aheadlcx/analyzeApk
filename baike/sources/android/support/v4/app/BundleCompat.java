package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat {

    static class a {
        private static Method a;
        private static boolean b;
        private static Method c;
        private static boolean d;

        public static IBinder getBinder(Bundle bundle, String str) {
            if (!b) {
                try {
                    a = Bundle.class.getMethod("getIBinder", new Class[]{String.class});
                    a.setAccessible(true);
                } catch (Throwable e) {
                    Throwable e2;
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", e2);
                }
                b = true;
            }
            if (a != null) {
                try {
                    return (IBinder) a.invoke(bundle, new Object[]{str});
                } catch (InvocationTargetException e3) {
                    e2 = e3;
                } catch (IllegalAccessException e4) {
                    e2 = e4;
                } catch (IllegalArgumentException e5) {
                    e2 = e5;
                }
            }
            return null;
            Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", e2);
            a = null;
            return null;
        }

        public static void putBinder(Bundle bundle, String str, IBinder iBinder) {
            if (!d) {
                try {
                    c = Bundle.class.getMethod("putIBinder", new Class[]{String.class, IBinder.class});
                    c.setAccessible(true);
                } catch (Throwable e) {
                    Throwable e2;
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", e2);
                }
                d = true;
            }
            if (c != null) {
                try {
                    c.invoke(bundle, new Object[]{str, iBinder});
                    return;
                } catch (InvocationTargetException e3) {
                    e2 = e3;
                } catch (IllegalAccessException e4) {
                    e2 = e4;
                } catch (IllegalArgumentException e5) {
                    e2 = e5;
                }
            } else {
                return;
            }
            Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", e2);
            c = null;
        }
    }

    private BundleCompat() {
    }

    @Nullable
    public static IBinder getBinder(@NonNull Bundle bundle, @Nullable String str) {
        if (VERSION.SDK_INT >= 18) {
            return bundle.getBinder(str);
        }
        return a.getBinder(bundle, str);
    }

    public static void putBinder(@NonNull Bundle bundle, @Nullable String str, @Nullable IBinder iBinder) {
        if (VERSION.SDK_INT >= 18) {
            bundle.putBinder(str, iBinder);
        } else {
            a.putBinder(bundle, str, iBinder);
        }
    }
}
