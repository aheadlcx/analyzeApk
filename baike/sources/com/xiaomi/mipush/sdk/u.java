package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.channel.commonutils.string.c;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.ao;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.r;
import java.util.ArrayList;
import java.util.Iterator;

public class u {
    private static u b;
    private static final ArrayList<a> e = new ArrayList();
    private boolean a = false;
    private Context c;
    private String d;
    private Handler f = null;
    private Intent g = null;
    private Integer h = null;

    static class a<T extends org.apache.thrift.a<T, ?>> {
        T a;
        com.xiaomi.xmpush.thrift.a b;
        boolean c;

        a() {
        }
    }

    private u(Context context) {
        this.c = context.getApplicationContext();
        this.d = null;
        this.a = h();
        this.f = new v(this, Looper.getMainLooper());
    }

    public static u a(Context context) {
        if (b == null) {
            b = new u(context);
        }
        return b;
    }

    private void a(Intent intent) {
        try {
            this.c.startService(intent);
        } catch (Throwable e) {
            b.a(e);
        }
    }

    private final void a(String str, boolean z) {
        int i = 0;
        if (a.a(this.c).b() && d.d(this.c)) {
            Object a;
            Object aeVar;
            ae aeVar2 = new ae();
            Intent i2 = i();
            if (TextUtils.isEmpty(str)) {
                a = MiPushClient.a();
                aeVar2.a((String) a);
                aeVar = new ae(a, true);
                synchronized (p.class) {
                    p.a(this.c).a((String) a);
                }
            } else {
                aeVar2.a(str);
                aeVar = new ae(str, true);
            }
            if (z) {
                aeVar2.c(o.DisablePushMessage.N);
                aeVar.c(o.DisablePushMessage.N);
                i2.setAction("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE");
            } else {
                aeVar2.c(o.EnablePushMessage.N);
                aeVar.c(o.EnablePushMessage.N);
                i2.setAction("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE");
            }
            aeVar2.b(a.a(this.c).c());
            aeVar2.d(this.c.getPackageName());
            a(aeVar2, com.xiaomi.xmpush.thrift.a.Notification, false, null);
            aeVar.b(a.a(this.c).c());
            aeVar.d(this.c.getPackageName());
            byte[] a2 = aq.a(q.a(this.c, aeVar, com.xiaomi.xmpush.thrift.a.Notification, false, this.c.getPackageName(), a.a(this.c).c()));
            if (a2 != null) {
                i2.putExtra("mipush_payload", a2);
                i2.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                i2.putExtra("mipush_app_id", a.a(this.c).c());
                i2.putExtra("mipush_app_token", a.a(this.c).d());
                a(i2);
            }
            Message obtain = Message.obtain();
            if (z) {
                i = 1;
            }
            obtain.obj = a;
            obtain.arg1 = i;
            this.f.sendMessageDelayed(obtain, Config.BPLUS_DELAY_TIME);
        }
    }

    private boolean h() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Exception e) {
            return false;
        }
    }

    private Intent i() {
        Intent intent = new Intent();
        String packageName = this.c.getPackageName();
        if (!c() || "com.xiaomi.xmsf".equals(packageName)) {
            l();
            intent.setComponent(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"));
            intent.putExtra("mipush_app_package", packageName);
        } else {
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", j());
            intent.putExtra("mipush_app_package", packageName);
            k();
        }
        return intent;
    }

    private String j() {
        try {
            if (this.c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception e) {
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    private void k() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), 2, 1);
        } catch (Throwable th) {
        }
    }

    private void l() {
        try {
            this.c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.c, "com.xiaomi.push.service.XMPushService"), 1, 1);
        } catch (Throwable th) {
        }
    }

    private boolean m() {
        String packageName = this.c.getPackageName();
        return packageName.contains("miui") || packageName.contains("xiaomi") || (this.c.getApplicationInfo().flags & 1) != 0;
    }

    public void a() {
        a(i());
    }

    public void a(int i) {
        Intent i2 = i();
        i2.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        i2.putExtra(am.y, this.c.getPackageName());
        i2.putExtra(am.z, i);
        a(i2);
    }

    public final void a(af afVar, boolean z) {
        this.g = null;
        Intent i = i();
        byte[] a = aq.a(q.a(this.c, afVar, com.xiaomi.xmpush.thrift.a.Registration));
        if (a == null) {
            b.a("register fail, because msgBytes is null.");
            return;
        }
        i.setAction("com.xiaomi.mipush.REGISTER_APP");
        i.putExtra("mipush_app_id", a.a(this.c).c());
        i.putExtra("mipush_payload", a);
        i.putExtra("mipush_session", this.d);
        i.putExtra("mipush_env_chanage", z);
        i.putExtra("mipush_env_type", a.a(this.c).m());
        if (d.d(this.c) && g()) {
            a(i);
        } else {
            this.g = i;
        }
    }

    public final void a(com.xiaomi.xmpush.thrift.am amVar) {
        Intent i = i();
        byte[] a = aq.a(q.a(this.c, amVar, com.xiaomi.xmpush.thrift.a.UnRegistration));
        if (a == null) {
            b.a("unregister fail, because msgBytes is null.");
            return;
        }
        i.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        i.putExtra("mipush_app_id", a.a(this.c).c());
        i.putExtra("mipush_payload", a);
        a(i);
    }

    public void a(String str, String str2) {
        Intent i = i();
        i.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        i.putExtra(am.y, this.c.getPackageName());
        i.putExtra(am.D, str);
        i.putExtra(am.E, str2);
        a(i);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, r rVar) {
        a(t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), rVar);
    }

    public <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        a aVar2 = new a();
        aVar2.a = t;
        aVar2.b = aVar;
        aVar2.c = z;
        synchronized (e) {
            e.add(aVar2);
            if (e.size() > 10) {
                e.remove(0);
            }
        }
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, r rVar) {
        a(t, aVar, z, true, rVar, true);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, r rVar, boolean z2) {
        a(t, aVar, z, true, rVar, z2);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, r rVar, boolean z3) {
        a(t, aVar, z, z2, rVar, z3, this.c.getPackageName(), a.a(this.c).c());
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, r rVar, boolean z3, String str, String str2) {
        if (a.a(this.c).i()) {
            Intent i = i();
            org.apache.thrift.a a = q.a(this.c, t, aVar, z, str, str2);
            if (rVar != null) {
                a.a(rVar);
            }
            byte[] a2 = aq.a(a);
            if (a2 == null) {
                b.a("send message fail, because msgBytes is null.");
                return;
            }
            i.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            i.putExtra("mipush_payload", a2);
            i.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
            a(i);
        } else if (z2) {
            a((org.apache.thrift.a) t, aVar, z);
        } else {
            b.a("drop the message before initialization.");
        }
    }

    public final void a(boolean z) {
        a(z, null);
    }

    public final void a(boolean z, String str) {
        if (z) {
            p.a(this.c).f("disable_syncing");
        } else {
            p.a(this.c).f("enable_syncing");
        }
        a(str, z);
    }

    public final void b() {
        Intent i = i();
        i.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        a(i);
    }

    public void b(int i) {
        Intent i2 = i();
        i2.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        i2.putExtra(am.y, this.c.getPackageName());
        i2.putExtra(am.A, i);
        i2.putExtra(am.C, c.b(this.c.getPackageName() + i));
        a(i2);
    }

    public boolean c() {
        return this.a && 1 == a.a(this.c).m();
    }

    public void d() {
        if (this.g != null) {
            a(this.g);
            this.g = null;
        }
    }

    public void e() {
        synchronized (e) {
            Iterator it = e.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                a(aVar.a, aVar.b, aVar.c, false, null, true);
            }
            e.clear();
        }
    }

    public void f() {
        Intent i = i();
        i.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        i.putExtra(am.y, this.c.getPackageName());
        i.putExtra(am.C, c.b(this.c.getPackageName()));
        a(i);
    }

    public boolean g() {
        if (!c() || !m()) {
            return true;
        }
        if (this.h == null) {
            this.h = Integer.valueOf(ao.a(this.c).b());
            if (this.h.intValue() == 0) {
                this.c.getContentResolver().registerContentObserver(ao.a(this.c).c(), false, new w(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.h.intValue() != 0;
    }
}
