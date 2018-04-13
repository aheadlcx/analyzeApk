package cn.xiaochuan.daemon;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import com.izuiyou.a.a.a;
import java.util.concurrent.TimeUnit;
import rx.d;
import rx.j;
import rx.k;

public class WatchDogService extends Service {
    protected static k a;
    protected static PendingIntent b;

    public static class WatchDogNotificationService extends Service {
        public int onStartCommand(Intent intent, int i, int i2) {
            WatchDogService.b(this, 2, new Notification());
            stopSelf();
            return 1;
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    protected final int a(Intent intent, int i, int i2) {
        if (a.c && (a == null || a.isUnsubscribed())) {
            if (VERSION.SDK_INT <= 24) {
                b(this, 2, new Notification());
                if (VERSION.SDK_INT >= 18) {
                    a.a(new Intent(a.a, WatchDogNotificationService.class));
                }
            }
            if (VERSION.SDK_INT >= 21) {
                try {
                    Builder builder = new Builder(2, new ComponentName(a.a, JobSchedulerService.class));
                    if (VERSION.SDK_INT >= 24) {
                        builder.setPeriodic(JobInfo.getMinPeriodMillis(), JobInfo.getMinFlexMillis());
                    } else if (VERSION.SDK_INT >= 21) {
                        builder.setPeriodic((long) a.a());
                    }
                    builder.setPersisted(a.a(getApplicationContext()));
                    ((JobScheduler) getSystemService("jobscheduler")).schedule(builder.build());
                } catch (Throwable e) {
                    a.a(e);
                }
            } else {
                AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
                b = PendingIntent.getService(a.a, 2, new Intent(a.a, a.b), 134217728);
                alarmManager.setRepeating(0, System.currentTimeMillis() + ((long) a.a()), (long) a.a(), b);
            }
            a = d.a((long) a.a(), TimeUnit.MILLISECONDS).b(new j<Long>(this) {
                final /* synthetic */ WatchDogService a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Long) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                }

                public void a(Long l) {
                    a.a(a.b);
                }
            });
            getPackageManager().setComponentEnabledSetting(new ComponentName(getPackageName(), a.b.getName()), 1, 1);
        }
        return 1;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return a(intent, i, i2);
    }

    public final IBinder onBind(Intent intent) {
        a(intent, 0, 0);
        return null;
    }

    protected void a(Intent intent) {
        if (a.c) {
            a.a(a.b);
            a.a(WatchDogService.class);
        }
    }

    public void onTaskRemoved(Intent intent) {
        a(intent);
    }

    public void onDestroy() {
        a(null);
    }

    public static void a() {
        if (a.c) {
            if (VERSION.SDK_INT >= 21) {
                ((JobScheduler) a.a.getSystemService("jobscheduler")).cancel(2);
            } else {
                AlarmManager alarmManager = (AlarmManager) a.a.getSystemService(NotificationCompat.CATEGORY_ALARM);
                if (b != null) {
                    alarmManager.cancel(b);
                }
            }
            if (a != null) {
                a.unsubscribe();
            }
        }
    }

    private static void b(Service service, int i, Notification notification) {
        if (VERSION.SDK_INT <= 24) {
            service.startForeground(i, notification);
        }
    }
}
