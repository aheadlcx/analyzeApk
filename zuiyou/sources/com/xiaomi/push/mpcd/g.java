package com.xiaomi.push.mpcd;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.misc.h;
import com.xiaomi.push.mpcd.job.a;
import com.xiaomi.push.mpcd.job.b;
import com.xiaomi.push.mpcd.job.c;
import com.xiaomi.push.mpcd.job.d;
import com.xiaomi.push.mpcd.job.e;
import com.xiaomi.push.mpcd.job.i;
import com.xiaomi.push.mpcd.job.j;
import com.xiaomi.push.mpcd.job.k;
import com.xiaomi.push.mpcd.job.m;
import com.xiaomi.push.service.am;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class g {
    private static volatile g a;
    private Context b;

    private g(Context context) {
        this.b = context;
    }

    private int a(int i) {
        return Math.max(60, i);
    }

    public static g a(Context context) {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g(context);
                }
            }
        }
        return a;
    }

    private void b() {
        h a = h.a(this.b);
        am a2 = am.a(this.b);
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("first_try_ts", currentTimeMillis);
        if (j == currentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", currentTimeMillis).commit();
        }
        if (Math.abs(currentTimeMillis - j) >= 172800000) {
            int a3;
            int a4;
            boolean a5 = a2.a(com.xiaomi.xmpush.thrift.g.ScreenSizeCollectionSwitch.a(), true);
            boolean a6 = a2.a(com.xiaomi.xmpush.thrift.g.AndroidVnCollectionSwitch.a(), true);
            boolean a7 = a2.a(com.xiaomi.xmpush.thrift.g.AndroidVcCollectionSwitch.a(), true);
            boolean a8 = a2.a(com.xiaomi.xmpush.thrift.g.AndroidIdCollectionSwitch.a(), true);
            boolean a9 = a2.a(com.xiaomi.xmpush.thrift.g.OperatorSwitch.a(), true);
            if (a5 || a6 || a7 || a8 || a9) {
                a3 = a(a2.a(com.xiaomi.xmpush.thrift.g.DeviceInfoCollectionFrequency.a(), 1209600));
                a.a(new com.xiaomi.push.mpcd.job.h(this.b, a3, a5, a6, a7, a8, a9), a3, 30);
            }
            a5 = a2.a(com.xiaomi.xmpush.thrift.g.MacCollectionSwitch.a(), true);
            a6 = a2.a(com.xiaomi.xmpush.thrift.g.IMSICollectionSwitch.a(), true);
            a7 = a2.a(com.xiaomi.xmpush.thrift.g.IccidCollectionSwitch.a(), true);
            a8 = a2.a(com.xiaomi.xmpush.thrift.g.DeviceIdSwitch.a(), true);
            if (a5 || a6 || a7 || a8) {
                a3 = a(a2.a(com.xiaomi.xmpush.thrift.g.DeviceBaseInfoCollectionFrequency.a(), 1209600));
                a.a(new com.xiaomi.push.mpcd.job.g(this.b, a3, a5, a6, a7, a8), a3, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.AppInstallListCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.AppInstallListCollectionFrequency.a(), 86400));
                a.a(new c(this.b, a4), a4, 30);
            }
            if (VERSION.SDK_INT < 21 && a2.a(com.xiaomi.xmpush.thrift.g.AppActiveListCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.AppActiveListCollectionFrequency.a(), (int) IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR));
                a.a(new b(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.BluetoothCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.BluetoothCollectionFrequency.a(), 10800));
                a.a(new d(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.AccountCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.AccountCollectionFrequency.a(), 604800));
                a.a(new a(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.WifiCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.WifiCollectionFrequency.a(), (int) IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR));
                a.a(new k(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.TopAppCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.TopAppCollectionFrequency.a(), 300));
                a.a(new i(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.BroadcastActionCollectionSwitch.a(), true)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.BroadcastActionCollectionFrequency.a(), (int) IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR));
                a.a(new e(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.WifiDevicesMacCollectionSwitch.a(), false)) {
                a4 = a(a2.a(com.xiaomi.xmpush.thrift.g.WifiDevicesMacCollectionFrequency.a(), (int) IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR));
                a.a(new m(this.b, a4), a4, 30);
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.ActivityTSSwitch.a(), false)) {
                c();
            }
            if (a2.a(com.xiaomi.xmpush.thrift.g.UploadSwitch.a(), true)) {
                a.a(new j(this.b), a(a2.a(com.xiaomi.xmpush.thrift.g.UploadFrequency.a(), 86400)), 60);
            }
        }
    }

    private boolean c() {
        if (VERSION.SDK_INT < 14) {
            return false;
        }
        try {
            (this.b instanceof Application ? (Application) this.b : (Application) this.b.getApplicationContext()).registerActivityLifecycleCallbacks(new a(this.b, String.valueOf(System.currentTimeMillis() / 1000)));
            return true;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return false;
        }
    }

    public void a() {
        h.a(this.b).a(new h(this), 30);
    }
}
