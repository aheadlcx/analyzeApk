package com.iflytek.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import com.iflytek.cloud.thirdparty.dj;
import com.iflytek.cloud.thirdparty.dk;
import com.iflytek.cloud.thirdparty.dr;

public class LaunchService extends Service {
    private dk a;
    private long b = 0;
    private BroadcastReceiver c = new b(this);

    private void a() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getPackageName() + "_LAUNCH_ALARM_LAUNCH");
            registerReceiver(this.c, intentFilter);
        } catch (Throwable e) {
            dr.b("LaunchService", "", e);
        }
    }

    private synchronized void b() {
        long c = this.a.c();
        long currentTimeMillis = c + System.currentTimeMillis();
        long j = currentTimeMillis - this.b;
        if (j <= 0 || j >= c) {
            try {
                AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
                PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getPackageName() + "_LAUNCH_ALARM_LAUNCH"), 134217728);
                if (c > 0) {
                    this.b = currentTimeMillis;
                    alarmManager.cancel(broadcast);
                    alarmManager.set(0, this.b, broadcast);
                    dr.a((Context) this, "reg next alarm: " + dr.a(this.b));
                } else {
                    dr.a((Context) this, "not reg alarm,periodrun = 0.");
                }
            } catch (Throwable e) {
                dr.b("LaunchService", "", e);
            }
        } else {
            dr.a((Context) this, "reg next alarm too short:" + j);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        dr.a((Context) this, "LaunchService onCreate pid=" + Process.myTid());
        this.a = dk.a(getApplicationContext());
        a();
        dj.a(this);
        b();
    }

    public void onDestroy() {
        super.onDestroy();
        dr.a((Context) this, "LaunchService onDestroy");
        try {
            unregisterReceiver(this.c);
        } catch (Throwable e) {
            dr.b("LaunchService", "", e);
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        dr.a((Context) this, "LaunchService onStart lastalarm=" + this.b + " periodrun=" + this.a.c());
        if (this.b == 0 && this.a.c() > 0) {
            b();
        }
    }
}
