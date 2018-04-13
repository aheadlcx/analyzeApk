package cn.xiaochuan.daemon;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.annotation.Nullable;

public abstract class AbsWorkService extends Service {
    protected boolean a = true;

    public static class WorkNotificationService extends Service {
        public int onStartCommand(Intent intent, int i, int i2) {
            AbsWorkService.b((Service) this, 1, new Notification());
            stopSelf();
            return 1;
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    @Nullable
    public abstract IBinder a(Intent intent, Void voidR);

    public abstract Boolean a(Intent intent, int i, int i2);

    public abstract void a(Intent intent);

    public abstract void b(Intent intent, int i, int i2);

    public abstract void c(Intent intent, int i, int i2);

    public abstract Boolean d(Intent intent, int i, int i2);

    public static void a() {
        if (a.c) {
            a.a.sendBroadcast(new Intent("cn.xiaochuan.daemon.CANCEL_JOB_ALARM_SUB"));
        }
    }

    protected int e(Intent intent, int i, int i2) {
        a.a(WatchDogService.class);
        Boolean a = a(intent, i, i2);
        if (a != null) {
            if (a.booleanValue()) {
                g(intent, i, i2);
            } else {
                f(intent, i, i2);
            }
        }
        if (this.a) {
            this.a = false;
            if (VERSION.SDK_INT <= 24) {
                b((Service) this, 1, new Notification());
                if (VERSION.SDK_INT >= 18) {
                    a.a(new Intent(getApplication(), WorkNotificationService.class));
                }
            }
            getPackageManager().setComponentEnabledSetting(new ComponentName(getPackageName(), WatchDogService.class.getName()), 1, 1);
        }
        return 1;
    }

    void f(Intent intent, int i, int i2) {
        Boolean a = a(intent, i, i2);
        if (a == null || !a.booleanValue()) {
            a = d(intent, i, i2);
            if (a == null || !a.booleanValue()) {
                b(intent, i, i2);
            }
        }
    }

    void g(Intent intent, int i, int i2) {
        c(intent, i, i2);
        a();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return e(intent, i, i2);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        e(intent, 0, 0);
        return a(intent, null);
    }

    protected void b(Intent intent) {
        a(intent);
        if (a.c) {
            a.a(a.b);
            a.a(WatchDogService.class);
        }
    }

    public void onTaskRemoved(Intent intent) {
        b(intent);
    }

    public void onDestroy() {
        b(null);
    }

    private static void b(Service service, int i, Notification notification) {
        if (VERSION.SDK_INT <= 24) {
            service.startForeground(i, notification);
        }
    }
}
