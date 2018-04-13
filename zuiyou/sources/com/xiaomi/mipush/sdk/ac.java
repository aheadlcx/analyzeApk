package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.channel.commonutils.string.c;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.as;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.u;
import com.zhihu.matisse.internal.utils.Platform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ac {
    public static volatile boolean a = false;
    private static ac c;
    private static String d;
    private static String e;
    private static boolean n = false;
    private static final ArrayList<a> o = new ArrayList();
    private boolean b = false;
    private final Object f = new Object();
    private final int g = 5000;
    private boolean h;
    private boolean i;
    private Context j;
    private String k;
    private Messenger l;
    private Messenger m;
    private final LinkedList<b> p = new LinkedList();
    private Handler q = null;
    private List<Message> r = new ArrayList();
    private boolean s = false;
    private Thread t = new Thread(new ad(this));
    private Intent u = null;
    private Integer v = null;

    static class a<T extends org.apache.thrift.a<T, ?>> {
        T a;
        com.xiaomi.xmpush.thrift.a b;
        boolean c;

        a() {
        }
    }

    static class b {
        int a;
        HashMap<String, Object> b;

        b() {
        }
    }

    private ac(Context context) {
        this.j = context.getApplicationContext();
        this.k = null;
        e = com.xiaomi.push.service.a.a(context).a();
        boolean z = f.a() && com.xiaomi.channel.commonutils.android.a.b(context, "com.xiaomi.xmsf") >= 109;
        this.i = z;
        this.h = false;
        this.b = n();
        n = y();
        this.q = new ae(this, Looper.getMainLooper());
        Intent p = p();
        if (p != null) {
            a(p);
            a = true;
        }
    }

    private boolean A() {
        String packageName = this.j.getPackageName();
        return packageName.contains(Platform.MIUI) || packageName.contains("xiaomi") || (this.j.getApplicationInfo().flags & 1) != 0;
    }

    private void B() {
        Intent intent = new Intent();
        intent.setClassName("com.xiaomi.xmsf", t());
        this.j.bindService(intent, new ah(this), 1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T extends org.apache.thrift.a<T, ?>> void C() {
        /*
        r10 = this;
        r1 = r10.p;
        if (r1 == 0) goto L_0x000c;
    L_0x0004:
        r1 = r10.p;
        r1 = r1.size();
        if (r1 != 0) goto L_0x0012;
    L_0x000c:
        return;
    L_0x000d:
        r1 = r9.a;	 Catch:{ Throwable -> 0x002d }
        switch(r1) {
            case 1: goto L_0x0029;
            case 2: goto L_0x002f;
            case 3: goto L_0x0033;
            case 4: goto L_0x0048;
            case 5: goto L_0x0066;
            case 6: goto L_0x006a;
            case 7: goto L_0x008a;
            case 8: goto L_0x00c3;
            case 9: goto L_0x0137;
            case 10: goto L_0x0147;
            case 11: goto L_0x015d;
            default: goto L_0x0012;
        };
    L_0x0012:
        r2 = r10.p;
        monitor-enter(r2);
        r1 = r10.p;	 Catch:{ all -> 0x0026 }
        r1 = r1.poll();	 Catch:{ all -> 0x0026 }
        r0 = r1;
        r0 = (com.xiaomi.mipush.sdk.ac.b) r0;	 Catch:{ all -> 0x0026 }
        r9 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        if (r9 != 0) goto L_0x000d;
    L_0x0022:
        r1 = 1;
        a = r1;
        goto L_0x000c;
    L_0x0026:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        throw r1;
    L_0x0029:
        r10.l();	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x002d:
        r1 = move-exception;
        goto L_0x0012;
    L_0x002f:
        r10.z();	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x0033:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "notifyId";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002d }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x002d }
        r10.e(r1);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x0048:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "title";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x002d }
        r2 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r3 = "description";
        r2 = r2.get(r3);	 Catch:{ Throwable -> 0x002d }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Throwable -> 0x002d }
        r10.b(r1, r2);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x0066:
        r10.m();	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x006a:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "XmPushActionRegistration";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = (com.xiaomi.xmpush.thrift.aj) r1;	 Catch:{ Throwable -> 0x002d }
        r2 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r3 = "isEnvChanage";
        r2 = r2.get(r3);	 Catch:{ Throwable -> 0x002d }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x002d }
        r2 = java.lang.Boolean.parseBoolean(r2);	 Catch:{ Throwable -> 0x002d }
        r10.b(r1, r2);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x008a:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "messageId";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r3 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "type";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = (com.xiaomi.mipush.sdk.aj) r1;	 Catch:{ Throwable -> 0x002d }
        r2 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r4 = "expand";
        r2 = r2.get(r4);	 Catch:{ Throwable -> 0x002d }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x002d }
        r4 = java.lang.Boolean.parseBoolean(r2);	 Catch:{ Throwable -> 0x002d }
        r2 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r5 = "extra";
        r2 = r2.get(r5);	 Catch:{ Throwable -> 0x002d }
        r2 = (java.util.HashMap) r2;	 Catch:{ Throwable -> 0x002d }
        r10.b(r3, r1, r4, r2);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x00c3:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "packet";
        r2 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r2 = (org.apache.thrift.a) r2;	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r3 = "actionType";
        r3 = r1.get(r3);	 Catch:{ Throwable -> 0x002d }
        r3 = (com.xiaomi.xmpush.thrift.a) r3;	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r4 = "encrypt";
        r1 = r1.get(r4);	 Catch:{ Throwable -> 0x002d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002d }
        r4 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r5 = "pendingIfNecessary";
        r1 = r1.get(r5);	 Catch:{ Throwable -> 0x002d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002d }
        r5 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r6 = "metaInfo";
        r6 = r1.get(r6);	 Catch:{ Throwable -> 0x002d }
        r6 = (com.xiaomi.xmpush.thrift.u) r6;	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r7 = "isCache";
        r1 = r1.get(r7);	 Catch:{ Throwable -> 0x002d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002d }
        r7 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r8 = "packageName";
        r1 = r1.get(r8);	 Catch:{ Throwable -> 0x002d }
        r8 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r9 = "appId";
        r1 = r1.get(r9);	 Catch:{ Throwable -> 0x002d }
        r9 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x002d }
        r1 = r10;
        r1.b(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x0137:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "ClientUploadDataItem";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = (com.xiaomi.xmpush.thrift.f) r1;	 Catch:{ Throwable -> 0x002d }
        r10.b(r1);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x0147:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "notifyType";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x002d }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x002d }
        r10.f(r1);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
    L_0x015d:
        r1 = r9.b;	 Catch:{ Throwable -> 0x002d }
        r2 = "XmPushActionUnRegistration";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x002d }
        r1 = (com.xiaomi.xmpush.thrift.aq) r1;	 Catch:{ Throwable -> 0x002d }
        r10.b(r1);	 Catch:{ Throwable -> 0x002d }
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.ac.C():void");
    }

    public static synchronized ac a(Context context) {
        ac acVar;
        synchronized (ac.class) {
            if (c == null) {
                c = new ac(context);
            }
            acVar = c;
        }
        return acVar;
    }

    private void a(Intent intent) {
        try {
            this.j.startService(intent);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    private final void a(String str, aj ajVar, boolean z, HashMap<String, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        hashMap2.put("messageId", str);
        hashMap2.put("type", ajVar);
        hashMap2.put("expand", Boolean.valueOf(z));
        hashMap2.put(PushConstants.EXTRA, hashMap);
        if (!a(7, hashMap2)) {
            b(str, ajVar, z, hashMap);
        }
    }

    private boolean a(int i, HashMap<String, Object> hashMap) {
        if (!this.i || a || "com.xiaomi.xmsf".equals(this.j.getPackageName())) {
            return false;
        }
        synchronized (this.p) {
            b bVar = new b();
            bVar.a = i;
            bVar.b = hashMap;
            this.p.offer(bVar);
        }
        return true;
    }

    private void b(Intent intent) {
        int a = am.a(this.j).a(g.ServiceBootMode.a(), com.xiaomi.xmpush.thrift.b.START.a());
        int k = k();
        Object obj = (a == com.xiaomi.xmpush.thrift.b.BIND.a() && n) ? 1 : null;
        a = obj != null ? com.xiaomi.xmpush.thrift.b.BIND.a() : com.xiaomi.xmpush.thrift.b.START.a();
        if (a != k) {
            c(a);
        }
        if (obj != null) {
            c(intent);
        } else {
            a(intent);
        }
    }

    private final void b(aj ajVar, boolean z) {
        this.u = null;
        Intent o = o();
        byte[] a = au.a(aq.a(this.j, ajVar, com.xiaomi.xmpush.thrift.a.Registration));
        if (a == null) {
            com.xiaomi.channel.commonutils.logger.b.a("register fail, because msgBytes is null.");
            return;
        }
        o.setAction("com.xiaomi.mipush.REGISTER_APP");
        o.putExtra("mipush_app_id", c.a(this.j).c());
        o.putExtra("mipush_payload", a);
        o.putExtra("mipush_session", this.k);
        o.putExtra("mipush_env_chanage", z);
        o.putExtra("mipush_env_type", c.a(this.j).l());
        if (d.c(this.j) && h()) {
            b(o);
        } else {
            this.u = o;
        }
    }

    private final void b(aq aqVar) {
        byte[] a = au.a(aq.a(this.j, aqVar, com.xiaomi.xmpush.thrift.a.UnRegistration));
        if (a == null) {
            com.xiaomi.channel.commonutils.logger.b.a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent o = o();
        o.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        o.putExtra("mipush_app_id", c.a(this.j).c());
        o.putExtra("mipush_payload", a);
        b(o);
    }

    private final void b(com.xiaomi.xmpush.thrift.f fVar) {
        Intent o = o();
        byte[] a = au.a(fVar);
        if (a == null) {
            com.xiaomi.channel.commonutils.logger.b.a("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        o.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        o.putExtra("mipush_payload", a);
        a(o);
    }

    private void b(String str, aj ajVar, boolean z, HashMap<String, String> hashMap) {
        if (c.a(this.j).b() && d.c(this.j)) {
            Object generatePacketID;
            ai aiVar;
            org.apache.thrift.a aiVar2 = new ai();
            aiVar2.a(true);
            Intent o = o();
            if (TextUtils.isEmpty(str)) {
                generatePacketID = MiPushClient.generatePacketID();
                aiVar2.a((String) generatePacketID);
                ai aiVar3 = z ? new ai(generatePacketID, true) : null;
                synchronized (u.class) {
                    u.a(this.j).a((String) generatePacketID);
                }
                aiVar = aiVar3;
            } else {
                aiVar2.a(str);
                aiVar = z ? new ai(str, true) : null;
            }
            switch (ai.a[ajVar.ordinal()]) {
                case 1:
                    aiVar2.c(r.DisablePushMessage.W);
                    aiVar.c(r.DisablePushMessage.W);
                    if (hashMap != null) {
                        aiVar2.a((Map) hashMap);
                        aiVar.a((Map) hashMap);
                    }
                    o.setAction("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE");
                    break;
                case 2:
                    aiVar2.c(r.EnablePushMessage.W);
                    aiVar.c(r.EnablePushMessage.W);
                    if (hashMap != null) {
                        aiVar2.a((Map) hashMap);
                        aiVar.a((Map) hashMap);
                    }
                    o.setAction("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE");
                    break;
                case 3:
                    aiVar2.c(r.ThirdPartyRegUpdate.W);
                    if (hashMap != null) {
                        aiVar2.a((Map) hashMap);
                        break;
                    }
                    break;
            }
            aiVar2.b(c.a(this.j).c());
            aiVar2.d(this.j.getPackageName());
            a(aiVar2, com.xiaomi.xmpush.thrift.a.Notification, false, null);
            if (z) {
                aiVar.b(c.a(this.j).c());
                aiVar.d(this.j.getPackageName());
                byte[] a = au.a(aq.a(this.j, aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, this.j.getPackageName(), c.a(this.j).c()));
                if (a != null) {
                    o.putExtra("mipush_payload", a);
                    o.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    o.putExtra("mipush_app_id", c.a(this.j).c());
                    o.putExtra("mipush_app_token", c.a(this.j).d());
                    b(o);
                }
            }
            Message obtain = Message.obtain();
            obtain.what = 19;
            int ordinal = ajVar.ordinal();
            obtain.obj = generatePacketID;
            obtain.arg1 = ordinal;
            this.q.sendMessageDelayed(obtain, 5000);
        }
    }

    private void b(String str, String str2) {
        Intent o = o();
        o.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        o.putExtra(as.y, this.j.getPackageName());
        o.putExtra(as.D, str);
        o.putExtra(as.E, str2);
        b(o);
    }

    private final <T extends org.apache.thrift.a<T, ?>> void b(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, u uVar, boolean z3, String str, String str2) {
        if (c.a(this.j).i()) {
            org.apache.thrift.a a = aq.a(this.j, t, aVar, z, str, str2);
            if (uVar != null) {
                a.a(uVar);
            }
            byte[] a2 = au.a(a);
            if (a2 == null) {
                com.xiaomi.channel.commonutils.logger.b.a("send message fail, because msgBytes is null.");
                return;
            }
            Intent o = o();
            o.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            o.putExtra("mipush_payload", a2);
            o.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
            b(o);
        } else if (z2) {
            a((org.apache.thrift.a) t, aVar, z);
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("drop the message before initialization.");
        }
    }

    private synchronized void c(Intent intent) {
        if (this.s) {
            Message d = d(intent);
            if (this.r.size() >= 50) {
                this.r.remove(0);
            }
            this.r.add(d);
        } else if (this.l == null) {
            Context context = this.j;
            ServiceConnection agVar = new ag(this);
            Context context2 = this.j;
            context.bindService(intent, agVar, 1);
            this.s = true;
            this.r.clear();
            this.r.add(d(intent));
        } else {
            try {
                this.l.send(d(intent));
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
    }

    private Message d(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    private synchronized void d(int i) {
        this.j.getSharedPreferences("mipush_extra", 0).edit().putInt("service_boot_mode", i).commit();
    }

    private void e(int i) {
        Intent o = o();
        o.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        o.putExtra(as.y, this.j.getPackageName());
        o.putExtra(as.z, i);
        b(o);
    }

    private void f(int i) {
        Intent o = o();
        o.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        o.putExtra(as.y, this.j.getPackageName());
        o.putExtra(as.A, i);
        o.putExtra(as.C, c.b(this.j.getPackageName() + i));
        b(o);
    }

    private synchronized int k() {
        return this.j.getSharedPreferences("mipush_extra", 0).getInt("service_boot_mode", -1);
    }

    private void l() {
        a(o());
    }

    private final void m() {
        Intent o = o();
        o.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        b(o);
    }

    private boolean n() {
        try {
            PackageInfo packageInfo = this.j.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Throwable th) {
            return false;
        }
    }

    private Intent o() {
        if (!com.xiaomi.channel.commonutils.misc.d.a || a) {
            return (d() && w() && !"com.xiaomi.xmsf".equals(this.j.getPackageName())) ? r() : s();
        } else {
            throw new RuntimeException("can't do this on ui thread when debug_switch:" + com.xiaomi.channel.commonutils.misc.d.a);
        }
    }

    private Intent p() {
        if (!"com.xiaomi.xmsf".equals(this.j.getPackageName())) {
            return q();
        }
        com.xiaomi.channel.commonutils.logger.b.c("pushChannel xmsf create own channel");
        return s();
    }

    private Intent q() {
        if (this.i) {
            com.xiaomi.channel.commonutils.logger.b.c("pushChannel app start request xmsf region");
            this.m = new Messenger(this.q);
            this.t.start();
            return null;
        } else if (PushChannelRegion.China.name().equals(e)) {
            com.xiaomi.channel.commonutils.logger.b.c("pushChannel app start miui china channel");
            return r();
        } else {
            com.xiaomi.channel.commonutils.logger.b.c("pushChannel app start  own channel");
            return s();
        }
    }

    private Intent r() {
        Intent intent = new Intent();
        String packageName = this.j.getPackageName();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", t());
        intent.putExtra("mipush_app_package", packageName);
        u();
        return intent;
    }

    private Intent s() {
        Intent intent = new Intent();
        String packageName = this.j.getPackageName();
        v();
        intent.setComponent(new ComponentName(this.j, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    private String t() {
        try {
            if (this.j.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception e) {
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    private void u() {
        try {
            this.j.getPackageManager().setComponentEnabledSetting(new ComponentName(this.j, "com.xiaomi.push.service.XMPushService"), 2, 1);
        } catch (Throwable th) {
        }
    }

    private void v() {
        try {
            this.j.getPackageManager().setComponentEnabledSetting(new ComponentName(this.j, "com.xiaomi.push.service.XMPushService"), 1, 1);
        } catch (Throwable th) {
        }
    }

    private boolean w() {
        return ((TextUtils.isEmpty(e) || TextUtils.isEmpty(d) || TextUtils.equals(d, e)) && !this.h) ? this.i || TextUtils.equals(e, PushChannelRegion.China.name()) : false;
    }

    private void x() {
        if (am.a(this.j).a(g.GlobalPushChannelException.a(), false)) {
            MiTinyDataClient.a(this.j, "push_exception", "xmsf_region_timeout", 1, "app request xmsf_request timeout");
        }
    }

    private boolean y() {
        if (!d()) {
            return true;
        }
        try {
            return this.j.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
        } catch (Exception e) {
            return true;
        }
    }

    private void z() {
        Intent o = o();
        o.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        o.putExtra(as.y, this.j.getPackageName());
        o.putExtra(as.C, c.b(this.j.getPackageName()));
        b(o);
    }

    public void a() {
        if (!a(1, null)) {
            l();
        }
    }

    public void a(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("notifyId", Integer.valueOf(i));
        if (!a(3, hashMap)) {
            e(i);
        }
    }

    public final void a(aj ajVar, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("XmPushActionRegistration", ajVar);
        hashMap.put("isEnvChanage", Boolean.valueOf(z));
        if (!a(6, hashMap)) {
            b(ajVar, z);
        }
    }

    public final void a(aq aqVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("XmPushActionUnRegistration", aqVar);
        if (!a(11, hashMap)) {
            b(aqVar);
        }
    }

    public final void a(com.xiaomi.xmpush.thrift.f fVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("ClientUploadDataItem", fVar);
        if (!a(9, hashMap)) {
            b(fVar);
        }
    }

    public final void a(String str) {
        u.a(this.j).a(aj.UPLOAD_TOKEN, "syncing");
        a(str, aj.UPLOAD_TOKEN, false, ak.e(this.j));
    }

    public void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("title", str);
        hashMap.put("description", str2);
        if (!a(4, hashMap)) {
            b(str, str2);
        }
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, u uVar) {
        a((org.apache.thrift.a) t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), uVar);
    }

    public <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        a aVar2 = new a();
        aVar2.a = t;
        aVar2.b = aVar;
        aVar2.c = z;
        synchronized (o) {
            o.add(aVar2);
            if (o.size() > 10) {
                o.remove(0);
            }
        }
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, u uVar) {
        a(t, aVar, z, true, uVar, true);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, u uVar, boolean z2) {
        a(t, aVar, z, true, uVar, z2);
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, u uVar, boolean z3) {
        a(t, aVar, z, z2, uVar, z3, this.j.getPackageName(), c.a(this.j).c());
    }

    public final <T extends org.apache.thrift.a<T, ?>> void a(T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, boolean z2, u uVar, boolean z3, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("packet", t);
        hashMap.put("actionType", aVar);
        hashMap.put("encrypt", Boolean.valueOf(z));
        hashMap.put("pendingIfNecessary", Boolean.valueOf(z2));
        hashMap.put("metaInfo", uVar);
        hashMap.put("isCache", Boolean.valueOf(z3));
        hashMap.put("packageName", str);
        hashMap.put("appId", str2);
        if (!a(8, hashMap)) {
            b(t, aVar, z, z2, uVar, z3, str, str2);
        }
    }

    public final void a(boolean z) {
        a(z, null);
    }

    public final void a(boolean z, String str) {
        if (z) {
            u.a(this.j).a(aj.DISABLE_PUSH, "syncing");
            u.a(this.j).a(aj.ENABLE_PUSH, "");
            a(str, aj.DISABLE_PUSH, true, null);
            return;
        }
        u.a(this.j).a(aj.ENABLE_PUSH, "syncing");
        u.a(this.j).a(aj.DISABLE_PUSH, "");
        a(str, aj.ENABLE_PUSH, true, null);
    }

    public final void b() {
        if (!a(5, null)) {
            m();
        }
    }

    public final void c() {
        a(null);
    }

    public boolean c(int i) {
        if (!c.a(this.j).b()) {
            return false;
        }
        d(i);
        org.apache.thrift.a aiVar = new ai();
        aiVar.a(MiPushClient.generatePacketID());
        aiVar.b(c.a(this.j).c());
        aiVar.d(this.j.getPackageName());
        aiVar.c(r.ClientABTest.W);
        aiVar.h = new HashMap();
        aiVar.h.put("boot_mode", i + "");
        a(this.j).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
        return true;
    }

    public boolean d() {
        return this.b && 1 == c.a(this.j).l();
    }

    public void e() {
        if (this.u != null) {
            b(this.u);
            this.u = null;
        }
    }

    public void f() {
        synchronized (o) {
            Iterator it = o.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                a(aVar.a, aVar.b, aVar.c, false, null, true);
            }
            o.clear();
        }
    }

    public void g() {
        if (!a(2, null)) {
            z();
        }
    }

    public boolean h() {
        if (!d() || !A()) {
            return true;
        }
        if (this.v == null) {
            this.v = Integer.valueOf(com.xiaomi.push.service.au.a(this.j).b());
            if (this.v.intValue() == 0) {
                this.j.getContentResolver().registerContentObserver(com.xiaomi.push.service.au.a(this.j).c(), false, new af(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.v.intValue() != 0;
    }
}
