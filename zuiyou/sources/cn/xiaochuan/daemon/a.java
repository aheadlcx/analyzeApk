package cn.xiaochuan.daemon;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class a {
    static Context a;
    static Class<? extends AbsWorkService> b;
    static boolean c;
    static final Map<Class<? extends Service>, ServiceConnection> d = new HashMap();
    private static int e = 300000;

    public static void a(@NonNull Context context, @NonNull Class<? extends AbsWorkService> cls, @Nullable Integer num) {
        a = context;
        b = cls;
        if (num != null) {
            e = num.intValue();
        }
        c = true;
    }

    public static void a(@NonNull final Class<? extends Service> cls) {
        if (c) {
            final Intent intent = new Intent(a, cls);
            a(intent);
            if (((ServiceConnection) d.get(cls)) == null) {
                a.bindService(intent, new ServiceConnection() {
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        a.d.put(cls, this);
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        a.d.remove(cls);
                        a.a(intent);
                        if (a.c) {
                            a.a.bindService(intent, this, 1);
                        }
                    }
                }, 1);
            }
        }
    }

    static void a(Intent intent) {
        if (c) {
            try {
                a.startService(intent);
            } catch (Exception e) {
            }
        }
    }

    static int a() {
        return Math.max(e, 180000);
    }

    public static boolean a(Context context) {
        return a(context, "android.permission.RECEIVE_BOOT_COMPLETED", 0);
    }

    private static boolean a(Context context, String str, int i) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            if (i >= 1 || !a(context.getApplicationContext(), str, i + 1)) {
                return false;
            }
            return true;
        }
    }
}
