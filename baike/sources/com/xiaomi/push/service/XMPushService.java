package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.network.HostManager;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.aq;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import qsbk.app.core.utils.ACache;

public class XMPushService extends Service implements com.xiaomi.smack.d {
    public static int b = 1;
    private static final int g = Process.myPid();
    final BroadcastReceiver a = new au(this);
    private com.xiaomi.smack.b c;
    private w d;
    private e e;
    private long f = 0;
    private com.xiaomi.slim.f h;
    private com.xiaomi.smack.a i;
    private b j;
    private PacketSync k = null;
    private i l = null;
    private com.xiaomi.smack.f m = new aa(this);

    public static abstract class h extends com.xiaomi.push.service.i.b {
        public h(int i) {
            super(i);
        }

        public abstract void a();

        public abstract String b();

        public void run() {
            if (!(this.f == 4 || this.f == 8)) {
                com.xiaomi.channel.commonutils.logger.b.a("JOB: " + b());
            }
            a();
        }
    }

    class a extends h {
        com.xiaomi.push.service.ak.b a = null;
        final /* synthetic */ XMPushService b;

        public a(XMPushService xMPushService, com.xiaomi.push.service.ak.b bVar) {
            this.b = xMPushService;
            super(9);
            this.a = bVar;
        }

        public void a() {
            try {
                if (this.b.f()) {
                    com.xiaomi.push.service.ak.b b = ak.a().b(this.a.h, this.a.b);
                    if (b == null) {
                        com.xiaomi.channel.commonutils.logger.b.a("ignore bind because the channel " + this.a.h + " is removed ");
                        return;
                    } else if (b.m == com.xiaomi.push.service.ak.c.unbind) {
                        b.a(com.xiaomi.push.service.ak.c.b, 0, 0, null, null);
                        this.b.i.a(b);
                        com.xiaomi.stats.h.a(this.b, b);
                        return;
                    } else {
                        com.xiaomi.channel.commonutils.logger.b.a("trying duplicate bind, ingore! " + b.m);
                        return;
                    }
                }
                com.xiaomi.channel.commonutils.logger.b.d("trying bind while the connection is not created, quit!");
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                this.b.a(10, e);
            }
        }

        public String b() {
            return "bind the client. " + this.a.h;
        }
    }

    static class b extends h {
        private final com.xiaomi.push.service.ak.b a;

        public b(com.xiaomi.push.service.ak.b bVar) {
            super(12);
            this.a = bVar;
        }

        public void a() {
            this.a.a(com.xiaomi.push.service.ak.c.unbind, 1, 21, null, null);
        }

        public String b() {
            return "bind time out. chid=" + this.a.h;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof b) ? false : TextUtils.equals(((b) obj).a.h, this.a.h);
        }

        public int hashCode() {
            return this.a.h.hashCode();
        }
    }

    class c extends h {
        final /* synthetic */ XMPushService a;
        private com.xiaomi.slim.b b = null;

        public c(XMPushService xMPushService, com.xiaomi.slim.b bVar) {
            this.a = xMPushService;
            super(8);
            this.b = bVar;
        }

        public void a() {
            this.a.k.a(this.b);
        }

        public String b() {
            return "receive a message.";
        }
    }

    public class d extends h {
        final /* synthetic */ XMPushService a;

        d(XMPushService xMPushService) {
            this.a = xMPushService;
            super(1);
        }

        public void a() {
            if (this.a.b()) {
                this.a.o();
            } else {
                com.xiaomi.channel.commonutils.logger.b.a("should not connect. quit the job.");
            }
        }

        public String b() {
            return "do reconnect..";
        }
    }

    class e extends BroadcastReceiver {
        final /* synthetic */ XMPushService a;

        e(XMPushService xMPushService) {
            this.a = xMPushService;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.k();
        }
    }

    public class f extends h {
        final /* synthetic */ XMPushService a;
        public int b;
        public Exception c;

        f(XMPushService xMPushService, int i, Exception exception) {
            this.a = xMPushService;
            super(2);
            this.b = i;
            this.c = exception;
        }

        public void a() {
            this.a.a(this.b, this.c);
        }

        public String b() {
            return "disconnect the connection.";
        }
    }

    class g extends h {
        final /* synthetic */ XMPushService a;
        private Intent b = null;

        public g(XMPushService xMPushService, Intent intent) {
            this.a = xMPushService;
            super(15);
            this.b = intent;
        }

        public void a() {
            this.a.c(this.b);
        }

        public String b() {
            return "Handle intent action = " + this.b.getAction();
        }
    }

    class i extends h {
        final /* synthetic */ XMPushService a;

        public i(XMPushService xMPushService) {
            this.a = xMPushService;
            super(5);
        }

        public void a() {
            this.a.l.b();
        }

        public String b() {
            return "ask the job queue to quit";
        }
    }

    public class j extends Binder {
        final /* synthetic */ XMPushService a;

        public j(XMPushService xMPushService) {
            this.a = xMPushService;
        }
    }

    class k extends h {
        final /* synthetic */ XMPushService a;
        private com.xiaomi.smack.packet.d b = null;

        public k(XMPushService xMPushService, com.xiaomi.smack.packet.d dVar) {
            this.a = xMPushService;
            super(8);
            this.b = dVar;
        }

        public void a() {
            this.a.k.a(this.b);
        }

        public String b() {
            return "receive a message.";
        }
    }

    class l extends h {
        boolean a;
        final /* synthetic */ XMPushService b;

        public l(XMPushService xMPushService, boolean z) {
            this.b = xMPushService;
            super(4);
            this.a = z;
        }

        public void a() {
            if (this.b.f()) {
                try {
                    if (!this.a) {
                        com.xiaomi.stats.h.a();
                    }
                    this.b.i.b(this.a);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    this.b.a(10, e);
                }
            }
        }

        public String b() {
            return "send ping..";
        }
    }

    class m extends h {
        com.xiaomi.push.service.ak.b a = null;
        final /* synthetic */ XMPushService b;

        public m(XMPushService xMPushService, com.xiaomi.push.service.ak.b bVar) {
            this.b = xMPushService;
            super(4);
            this.a = bVar;
        }

        public void a() {
            try {
                this.a.a(com.xiaomi.push.service.ak.c.unbind, 1, 16, null, null);
                this.b.i.a(this.a.h, this.a.b);
                this.a.a(com.xiaomi.push.service.ak.c.b, 1, 16, null, null);
                this.b.i.a(this.a);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                this.b.a(10, e);
            }
        }

        public String b() {
            return "rebind the client. " + this.a.h;
        }
    }

    class n extends h {
        final /* synthetic */ XMPushService a;

        n(XMPushService xMPushService) {
            this.a = xMPushService;
            super(3);
        }

        public void a() {
            this.a.a(11, null);
            if (this.a.b()) {
                this.a.o();
            }
        }

        public String b() {
            return "reset the connection.";
        }
    }

    class o extends h {
        com.xiaomi.push.service.ak.b a = null;
        int b;
        String c;
        String d;
        final /* synthetic */ XMPushService e;

        public o(XMPushService xMPushService, com.xiaomi.push.service.ak.b bVar, int i, String str, String str2) {
            this.e = xMPushService;
            super(9);
            this.a = bVar;
            this.b = i;
            this.c = str;
            this.d = str2;
        }

        public void a() {
            if (!(this.a.m == com.xiaomi.push.service.ak.c.unbind || this.e.i == null)) {
                try {
                    this.e.i.a(this.a.h, this.a.b);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    this.e.a(10, e);
                }
            }
            this.a.a(com.xiaomi.push.service.ak.c.unbind, this.b, 0, this.d, this.c);
        }

        public String b() {
            return "unbind the channel. " + this.a.h;
        }
    }

    static {
        HostManager.addReservedHost("app.chat.xiaomi.net", "app.chat.xiaomi.net");
        HostManager.addReservedHost("app.chat.xiaomi.net", "42.62.94.2:443");
        HostManager.addReservedHost("app.chat.xiaomi.net", "114.54.23.2");
        HostManager.addReservedHost("app.chat.xiaomi.net", "111.13.142.2");
        HostManager.addReservedHost("app.chat.xiaomi.net", "111.206.200.2");
    }

    @TargetApi(11)
    public static Notification a(Context context) {
        Intent intent = new Intent(context, XMPushService.class);
        if (VERSION.SDK_INT >= 11) {
            Builder builder = new Builder(context);
            builder.setSmallIcon(context.getApplicationInfo().icon);
            builder.setContentTitle("Push Service");
            builder.setContentText("Push Service");
            builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
            return builder.getNotification();
        }
        Notification notification = new Notification();
        notification.setLatestEventInfo(context, "Push Service", "Push Service", PendingIntent.getService(context, 0, intent, 0));
        return notification;
    }

    private com.xiaomi.smack.packet.c a(com.xiaomi.smack.packet.c cVar, String str) {
        byte[] a = aq.a(str, cVar.k());
        com.xiaomi.smack.packet.c cVar2 = new com.xiaomi.smack.packet.c();
        cVar2.n(cVar.n());
        cVar2.m(cVar.m());
        cVar2.k(cVar.k());
        cVar2.l(cVar.l());
        cVar2.b(true);
        String a2 = aq.a(a, com.xiaomi.smack.util.d.c(cVar.c()));
        com.xiaomi.smack.packet.a aVar = new com.xiaomi.smack.packet.a("s", null, (String[]) null, (String[]) null);
        aVar.b(a2);
        cVar2.a(aVar);
        return cVar2;
    }

    private com.xiaomi.smack.packet.d a(com.xiaomi.smack.packet.d dVar, String str, String str2, boolean z) {
        ak a = ak.a();
        List b = a.b(str);
        if (b.isEmpty()) {
            com.xiaomi.channel.commonutils.logger.b.a("open channel should be called first before sending a packet, pkg=" + str);
        } else {
            dVar.o(str);
            String l = dVar.l();
            if (TextUtils.isEmpty(l)) {
                l = (String) b.get(0);
                dVar.l(l);
            }
            com.xiaomi.push.service.ak.b b2 = a.b(l, dVar.n());
            if (!f()) {
                com.xiaomi.channel.commonutils.logger.b.a("drop a packet as the channel is not connected, chid=" + l);
            } else if (b2 == null || b2.m != com.xiaomi.push.service.ak.c.binded) {
                com.xiaomi.channel.commonutils.logger.b.a("drop a packet as the channel is not opened, chid=" + l);
            } else if (TextUtils.equals(str2, b2.j)) {
                return ((dVar instanceof com.xiaomi.smack.packet.c) && z) ? a((com.xiaomi.smack.packet.c) dVar, b2.i) : dVar;
            } else {
                com.xiaomi.channel.commonutils.logger.b.a("invalid session. " + str2);
            }
        }
        return null;
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra(am.y);
        String stringExtra2 = intent.getStringExtra(am.B);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
        com.xiaomi.smack.packet.d dVar = (com.xiaomi.smack.packet.c) a(new com.xiaomi.smack.packet.c(bundleExtra), stringExtra, stringExtra2, false);
        if (dVar != null) {
            com.xiaomi.push.service.ak.b b = ak.a().b(dVar.l(), dVar.n());
            if (booleanExtra && "3".equals(dVar.l())) {
                com.xiaomi.smack.a h = h();
                if (h != null && h.a()) {
                    c(new x(this, com.xiaomi.slim.b.a(dVar, b.i)));
                    return;
                }
            }
            if (booleanExtra) {
                dVar = a((com.xiaomi.smack.packet.c) dVar, b.i);
            }
            if (dVar != null) {
                c(new x(this, dVar));
            }
        }
    }

    private void a(String str, int i) {
        Collection<com.xiaomi.push.service.ak.b> c = ak.a().c(str);
        if (c != null) {
            for (com.xiaomi.push.service.ak.b bVar : c) {
                if (bVar != null) {
                    a(new o(this, bVar, i, null, null));
                }
            }
        }
        ak.a().a(str);
    }

    private void a(String str, byte[] bArr, boolean z) {
        Collection c = ak.a().c("5");
        if (c.isEmpty()) {
            if (z) {
                r.b(str, bArr);
            }
        } else if (((com.xiaomi.push.service.ak.b) c.iterator().next()).m == com.xiaomi.push.service.ak.c.binded) {
            c(new bd(this, 4, str, bArr));
        } else if (z) {
            r.b(str, bArr);
        }
    }

    public static boolean a(int i, String str) {
        return (TextUtils.equals(str, "Enter") && i == 1) ? true : TextUtils.equals(str, "Leave") && i == 2;
    }

    private boolean a(String str, Intent intent) {
        com.xiaomi.push.service.ak.b b = ak.a().b(str, intent.getStringExtra(am.p));
        boolean z = false;
        if (b == null || str == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra(am.B);
        String stringExtra2 = intent.getStringExtra(am.u);
        if (!(TextUtils.isEmpty(b.j) || TextUtils.equals(stringExtra, b.j))) {
            com.xiaomi.channel.commonutils.logger.b.a("session changed. old session=" + b.j + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(b.i)) {
            return z;
        }
        com.xiaomi.channel.commonutils.logger.b.a("security changed. chid = " + str + " sechash = " + com.xiaomi.channel.commonutils.string.c.a(stringExtra2));
        return true;
    }

    private boolean a(String str, String str2, Context context) {
        if (TextUtils.equals("Leave", str) && !TextUtils.equals("Enter", e.a(context).c(str2))) {
            return false;
        }
        if (e.a(context).a(str2, str) != 0) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.a("update geofence statue failed geo_id:" + str2);
        return false;
    }

    private com.xiaomi.push.service.ak.b b(String str, Intent intent) {
        com.xiaomi.push.service.ak.b b = ak.a().b(str, intent.getStringExtra(am.p));
        if (b == null) {
            b = new com.xiaomi.push.service.ak.b(this);
        }
        b.h = intent.getStringExtra(am.q);
        b.b = intent.getStringExtra(am.p);
        b.c = intent.getStringExtra(am.s);
        b.a = intent.getStringExtra(am.y);
        b.f = intent.getStringExtra(am.w);
        b.g = intent.getStringExtra(am.x);
        b.e = intent.getBooleanExtra(am.v, false);
        b.i = intent.getStringExtra(am.u);
        b.j = intent.getStringExtra(am.B);
        b.d = intent.getStringExtra(am.t);
        b.k = this.j;
        b.l = getApplicationContext();
        ak.a().a(b);
        return b;
    }

    private void b(Intent intent) {
        int i = 0;
        String stringExtra = intent.getStringExtra(am.y);
        String stringExtra2 = intent.getStringExtra(am.B);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        com.xiaomi.smack.packet.c[] cVarArr = new com.xiaomi.smack.packet.c[parcelableArrayExtra.length];
        boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
        int i2 = 0;
        while (i2 < parcelableArrayExtra.length) {
            cVarArr[i2] = new com.xiaomi.smack.packet.c((Bundle) parcelableArrayExtra[i2]);
            cVarArr[i2] = (com.xiaomi.smack.packet.c) a(cVarArr[i2], stringExtra, stringExtra2, false);
            if (cVarArr[i2] != null) {
                i2++;
            } else {
                return;
            }
        }
        ak a = ak.a();
        if (booleanExtra && "3".equals(cVarArr[0].l())) {
            com.xiaomi.smack.a h = h();
            if (h != null && h.a()) {
                com.xiaomi.slim.b[] bVarArr = new com.xiaomi.slim.b[cVarArr.length];
                while (i < cVarArr.length) {
                    com.xiaomi.smack.packet.d dVar = cVarArr[i];
                    bVarArr[i] = com.xiaomi.slim.b.a(dVar, a.b(dVar.l(), dVar.n()).i);
                    i++;
                }
                c(new a(this, bVarArr));
                return;
            }
        }
        while (i < cVarArr.length) {
            cVarArr[i] = booleanExtra ? a(cVarArr[i], a.b(cVarArr[i].l(), cVarArr[i].n()).i) : cVarArr[i];
            i++;
        }
        c(new a(this, cVarArr));
    }

    private void b(boolean z) {
        this.f = System.currentTimeMillis();
        if (this.l.d()) {
            com.xiaomi.channel.commonutils.logger.b.d("ERROR, the job controller is blocked.");
            ak.a().a((Context) this, 14);
            stopSelf();
        } else if (!f()) {
            a(true);
        } else if (this.i.o() || com.xiaomi.channel.commonutils.network.d.f(this)) {
            a(new l(this, z));
        } else {
            a(new f(this, 17, null));
            a(true);
        }
    }

    private void c(Intent intent) {
        com.xiaomi.push.service.ak.b bVar = null;
        boolean z = true;
        boolean z2 = false;
        ak a = ak.a();
        String stringExtra;
        if (am.d.equalsIgnoreCase(intent.getAction()) || am.j.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(am.q);
            if (TextUtils.isEmpty(intent.getStringExtra(am.u))) {
                com.xiaomi.channel.commonutils.logger.b.a("security is empty. ignore.");
            } else if (stringExtra != null) {
                boolean a2 = a(stringExtra, intent);
                com.xiaomi.push.service.ak.b b = b(stringExtra, intent);
                if (!com.xiaomi.channel.commonutils.network.d.d(this)) {
                    this.j.a(this, b, false, 2, null);
                } else if (!f()) {
                    a(true);
                } else if (b.m == com.xiaomi.push.service.ak.c.unbind) {
                    c(new a(this, b));
                } else if (a2) {
                    c(new m(this, b));
                } else if (b.m == com.xiaomi.push.service.ak.c.b) {
                    com.xiaomi.channel.commonutils.logger.b.a(String.format("the client is binding. %1$s %2$s.", new Object[]{b.h, b.b}));
                } else if (b.m == com.xiaomi.push.service.ak.c.binded) {
                    this.j.a(this, b, true, 0, null);
                }
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("channel id is empty, do nothing!");
            }
        } else if (am.i.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(am.y);
            r2 = intent.getStringExtra(am.q);
            Object stringExtra2 = intent.getStringExtra(am.p);
            com.xiaomi.channel.commonutils.logger.b.a("Service called closechannel chid = " + r2 + " userId = " + stringExtra2);
            if (TextUtils.isEmpty(r2)) {
                for (String stringExtra3 : a.b(stringExtra3)) {
                    a(stringExtra3, 2);
                }
            } else if (TextUtils.isEmpty(stringExtra2)) {
                a(r2, 2);
            } else {
                a(r2, stringExtra2, 2, null, null);
            }
        } else if (am.e.equalsIgnoreCase(intent.getAction())) {
            a(intent);
        } else if (am.g.equalsIgnoreCase(intent.getAction())) {
            b(intent);
        } else if (am.f.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(am.y);
            r1 = intent.getStringExtra(am.B);
            r4 = new com.xiaomi.smack.packet.b(intent.getBundleExtra("ext_packet"));
            if (a(r4, stringExtra3, r1, false) != null) {
                c(new x(this, r4));
            }
        } else if (am.h.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(am.y);
            r1 = intent.getStringExtra(am.B);
            r4 = new com.xiaomi.smack.packet.f(intent.getBundleExtra("ext_packet"));
            if (a(r4, stringExtra3, r1, false) != null) {
                c(new x(this, r4));
            }
        } else if (am.k.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(am.q);
            r1 = intent.getStringExtra(am.p);
            if (stringExtra3 != null) {
                com.xiaomi.channel.commonutils.logger.b.a("request reset connection from chid = " + stringExtra3);
                com.xiaomi.push.service.ak.b b2 = ak.a().b(stringExtra3, r1);
                if (b2 != null && b2.i.equals(intent.getStringExtra(am.u)) && b2.m == com.xiaomi.push.service.ak.c.binded) {
                    com.xiaomi.smack.a h = h();
                    if (h == null || !h.a(System.currentTimeMillis() - 15000)) {
                        c(new n(this));
                    }
                }
            }
        } else if (am.l.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(am.y);
            List b3 = a.b(stringExtra3);
            if (b3.isEmpty()) {
                com.xiaomi.channel.commonutils.logger.b.a("open channel should be called first before update info, pkg=" + stringExtra3);
                return;
            }
            stringExtra3 = intent.getStringExtra(am.q);
            Object stringExtra4 = intent.getStringExtra(am.p);
            if (TextUtils.isEmpty(stringExtra3)) {
                stringExtra3 = (String) b3.get(0);
            }
            if (TextUtils.isEmpty(stringExtra4)) {
                Collection c = a.c(stringExtra3);
                if (!(c == null || c.isEmpty())) {
                    bVar = (com.xiaomi.push.service.ak.b) c.iterator().next();
                }
            } else {
                bVar = a.b(stringExtra3, stringExtra4);
            }
            if (bVar != null) {
                if (intent.hasExtra(am.w)) {
                    bVar.f = intent.getStringExtra(am.w);
                }
                if (intent.hasExtra(am.x)) {
                    bVar.g = intent.getStringExtra(am.x);
                }
            }
        } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
            if (ao.a(getApplicationContext()).a() && ao.a(getApplicationContext()).b() == 0) {
                com.xiaomi.channel.commonutils.logger.b.a("register without being provisioned. " + intent.getStringExtra("mipush_app_package"));
                return;
            }
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            String stringExtra5 = intent.getStringExtra("mipush_app_package");
            boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
            int intExtra = intent.getIntExtra("mipush_env_type", 1);
            p.a((Context) this).g(stringExtra5);
            if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                a(byteArrayExtra, stringExtra5);
            } else {
                c(new bb(this, 14, intExtra, byteArrayExtra, stringExtra5));
            }
        } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
            if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                p.a((Context) this).d(stringExtra3);
            }
            a(stringExtra3, byteArrayExtra2, booleanExtra2);
        } else if (ap.a.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("uninstall_pkg_name");
            if (stringExtra3 != null && !TextUtils.isEmpty(stringExtra3.trim())) {
                try {
                    getPackageManager().getPackageInfo(stringExtra3, 8192);
                    z = false;
                } catch (NameNotFoundException e) {
                }
                if ("com.xiaomi.channel".equals(stringExtra3) && !ak.a().c("1").isEmpty() && r9) {
                    a("1", 0);
                    com.xiaomi.channel.commonutils.logger.b.a("close the miliao channel as the app is uninstalled.");
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                r2 = sharedPreferences.getString(stringExtra3, null);
                if (!TextUtils.isEmpty(r2) && r9) {
                    Editor edit = sharedPreferences.edit();
                    edit.remove(stringExtra3);
                    edit.commit();
                    if (ac.e(this, stringExtra3)) {
                        ac.d(this, stringExtra3);
                    }
                    ac.b(this, stringExtra3);
                    if (f() && r2 != null) {
                        try {
                            j.a(this, j.a(stringExtra3, r2));
                            com.xiaomi.channel.commonutils.logger.b.a("uninstall " + stringExtra3 + " msg sent");
                        } catch (Exception e2) {
                            com.xiaomi.channel.commonutils.logger.b.d("Fail to send Message: " + e2.getMessage());
                            a(10, e2);
                        }
                    }
                }
            }
        } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(am.y);
            r1 = intent.getIntExtra(am.z, -2);
            if (!TextUtils.isEmpty(stringExtra3)) {
                if (r1 >= -1) {
                    ac.a((Context) this, stringExtra3, r1);
                } else {
                    ac.a((Context) this, stringExtra3, intent.getStringExtra(am.D), intent.getStringExtra(am.E));
                }
            }
        } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
            r2 = intent.getStringExtra(am.y);
            CharSequence stringExtra6 = intent.getStringExtra(am.C);
            CharSequence b4;
            if (intent.hasExtra(am.A)) {
                r1 = intent.getIntExtra(am.A, 0);
                b4 = com.xiaomi.channel.commonutils.string.c.b(r2 + r1);
            } else {
                b4 = com.xiaomi.channel.commonutils.string.c.b(r2);
                r1 = 0;
                z2 = true;
            }
            if (TextUtils.isEmpty(r2) || !TextUtils.equals(stringExtra6, r0)) {
                com.xiaomi.channel.commonutils.logger.b.d("invalid notification for " + r2);
            } else if (z2) {
                ac.d(this, r2);
            } else {
                ac.b((Context) this, r2, r1);
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
            Object stringExtra7 = intent.getStringExtra("mipush_app_package");
            if (!TextUtils.isEmpty(stringExtra7)) {
                p.a((Context) this).e(stringExtra7);
            }
            if (!"com.xiaomi.xmsf".equals(getPackageName())) {
                if (this.e != null) {
                    unregisterReceiver(this.e);
                    this.e = null;
                }
                this.l.c();
                a(new bc(this, 2));
                ak.a().e();
                ak.a().a((Context) this, 0);
                ak.a().d();
                at.a().b();
                com.xiaomi.push.service.timers.a.a();
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
            r2 = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
            String stringExtra8 = intent.getStringExtra("mipush_app_id");
            String stringExtra9 = intent.getStringExtra("mipush_app_token");
            if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                p.a((Context) this).f(r2);
            }
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                p.a((Context) this).h(r2);
                p.a((Context) this).i(r2);
            }
            if (byteArrayExtra3 == null) {
                r.a(this, r2, byteArrayExtra3, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
                return;
            }
            r.b(r2, byteArrayExtra3);
            a(new q(this, r2, stringExtra8, stringExtra9, byteArrayExtra3));
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.e == null) {
                this.e = new e(this);
                registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
        }
    }

    private void c(h hVar) {
        this.l.a((com.xiaomi.push.service.i.b) hVar);
    }

    private void c(boolean z) {
        try {
            if (!com.xiaomi.channel.commonutils.android.j.d()) {
                return;
            }
            if (z) {
                sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
            } else {
                sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    private com.xiaomi.push.service.aw.b j() {
        return new az(this);
    }

    private void k() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null) {
            com.xiaomi.channel.commonutils.logger.b.a("network changed, " + activeNetworkInfo.toString());
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("network changed, no active network");
        }
        if (com.xiaomi.stats.f.b() != null) {
            com.xiaomi.stats.f.b().b();
        }
        this.h.p();
        if (com.xiaomi.channel.commonutils.network.d.d(this)) {
            if (f() && l()) {
                b(false);
            }
            if (!(f() || g())) {
                this.l.b(1);
                a(new d(this));
            }
            com.xiaomi.push.log.b.a((Context) this).a();
        } else {
            a(new f(this, 2, null));
        }
        n();
    }

    private boolean l() {
        return System.currentTimeMillis() - this.f < i.MIN_UPLOAD_INTERVAL ? false : com.xiaomi.channel.commonutils.network.d.e(this);
    }

    private boolean m() {
        return "com.xiaomi.xmsf".equals(getPackageName()) || !p.a((Context) this).b(getPackageName());
    }

    private void n() {
        if (!b()) {
            com.xiaomi.push.service.timers.a.a();
        } else if (!com.xiaomi.push.service.timers.a.b()) {
            com.xiaomi.push.service.timers.a.a(true);
        }
    }

    private void o() {
        if (this.i != null && this.i.j()) {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while connecting.");
        } else if (this.i == null || !this.i.k()) {
            this.c.a(com.xiaomi.channel.commonutils.network.d.k(this));
            p();
            if (this.i == null) {
                ak.a().a((Context) this);
                c(false);
            }
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while is connected.");
        }
    }

    private void p() {
        try {
            this.h.a(this.m, new af(this));
            this.h.r();
            this.i = this.h;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a("fail to create Slim connection", e);
            this.h.b(3, e);
        }
    }

    private boolean q() {
        return TextUtils.equals(getPackageName(), "com.xiaomi.xmsf") ? false : ah.a((Context) this).a(com.xiaomi.xmpush.thrift.e.ForegroundServiceSwitch.a(), false);
    }

    private void r() {
        if (VERSION.SDK_INT < 18) {
            startForeground(g, new Notification());
        } else {
            bindService(new Intent(this, XMJobService.class), new aj(this), 1);
        }
    }

    void a() {
        if (System.currentTimeMillis() - this.f >= ((long) com.xiaomi.smack.g.c()) && com.xiaomi.channel.commonutils.network.d.e(this)) {
            b(true);
        }
    }

    public void a(int i) {
        this.l.b(i);
    }

    public void a(int i, Exception exception) {
        com.xiaomi.channel.commonutils.logger.b.a("disconnect " + hashCode() + ", " + (this.i == null ? null : Integer.valueOf(this.i.hashCode())));
        if (this.i != null) {
            this.i.b(i, exception);
            this.i = null;
        }
        a(7);
        a(4);
        ak.a().a((Context) this, i);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.metok.geofencing.state_change");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void a(h hVar) {
        a(hVar, 0);
    }

    public void a(h hVar, long j) {
        try {
            this.l.a((com.xiaomi.push.service.i.b) hVar, j);
        } catch (IllegalStateException e) {
        }
    }

    public void a(com.xiaomi.push.service.ak.b bVar) {
        if (bVar != null) {
            long a = bVar.a();
            com.xiaomi.channel.commonutils.logger.b.a("schedule rebind job in " + (a / 1000));
            a(new a(this, bVar), a);
        }
    }

    public void a(com.xiaomi.slim.b bVar) {
        if (this.i != null) {
            this.i.b(bVar);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(com.xiaomi.smack.a aVar) {
        com.xiaomi.stats.f.b().a(aVar);
        c(true);
        this.d.a();
        Iterator it = ak.a().b().iterator();
        while (it.hasNext()) {
            a(new a(this, (com.xiaomi.push.service.ak.b) it.next()));
        }
    }

    public void a(com.xiaomi.smack.a aVar, int i, Exception exception) {
        com.xiaomi.stats.f.b().a(aVar, i, exception);
        a(false);
    }

    public void a(com.xiaomi.smack.a aVar, Exception exception) {
        com.xiaomi.stats.f.b().a(aVar, exception);
        c(false);
        a(false);
    }

    public void a(com.xiaomi.smack.packet.d dVar) {
        if (this.i != null) {
            this.i.a(dVar);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        com.xiaomi.push.service.ak.b b = ak.a().b(str, str2);
        if (b != null) {
            a(new o(this, b, i, str4, str3));
        }
        ak.a().a(str, str2);
    }

    public void a(boolean z) {
        this.d.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            r.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
            com.xiaomi.channel.commonutils.logger.b.a("register request without payload");
            return;
        }
        org.apache.thrift.a abVar = new ab();
        try {
            aq.a(abVar, bArr);
            if (abVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                org.apache.thrift.a afVar = new af();
                try {
                    aq.a(afVar, abVar.f());
                    r.a(abVar.j(), bArr);
                    a(new q(this, abVar.j(), afVar.d(), afVar.h(), bArr));
                    return;
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    r.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data action error.");
                    return;
                }
            }
            r.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " registration action required.");
            com.xiaomi.channel.commonutils.logger.b.a("register request with invalid payload");
        } catch (Throwable e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            r.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data container error.");
        }
    }

    public void a(com.xiaomi.slim.b[] bVarArr) {
        if (this.i != null) {
            this.i.a(bVarArr);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        if (this.i != null) {
            this.i.a(dVarArr);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void b(h hVar) {
        this.l.a(hVar.f, (com.xiaomi.push.service.i.b) hVar);
    }

    public void b(com.xiaomi.smack.a aVar) {
        com.xiaomi.channel.commonutils.logger.b.c("begin to connect...");
        com.xiaomi.stats.f.b().b(aVar);
    }

    public boolean b() {
        return com.xiaomi.channel.commonutils.network.d.d(this) && ak.a().c() > 0 && !c() && m();
    }

    public boolean b(int i) {
        return this.l.a(i);
    }

    public boolean c() {
        try {
            Class cls = Class.forName("miui.os.Build");
            return cls.getField("IS_CM_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CU_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CT_CUSTOMIZATION_TEST").getBoolean(null);
        } catch (Throwable th) {
            return false;
        }
    }

    public b d() {
        return new b();
    }

    public b e() {
        return this.j;
    }

    public boolean f() {
        return this.i != null && this.i.k();
    }

    public boolean g() {
        return this.i != null && this.i.j();
    }

    public com.xiaomi.smack.a h() {
        return this.i;
    }

    public IBinder onBind(Intent intent) {
        return new j(this);
    }

    public void onCreate() {
        super.onCreate();
        com.xiaomi.channel.commonutils.android.j.a(this);
        n a = o.a(this);
        if (a != null) {
            com.xiaomi.channel.commonutils.misc.a.a(a.g);
        }
        an.a(this);
        this.c = new al(this, null, 5222, "xiaomi.com", null);
        this.c.a(true);
        this.h = new com.xiaomi.slim.f(this, this.c);
        this.j = d();
        try {
            if (com.xiaomi.channel.commonutils.android.j.d()) {
                this.j.a((Context) this);
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        com.xiaomi.push.service.timers.a.a((Context) this);
        this.h.a(this);
        this.k = new PacketSync(this);
        this.d = new w(this);
        new c().a();
        com.xiaomi.stats.f.a().a(this);
        this.l = new i("Connection Controller Thread");
        if (m()) {
            a(new ar(this, 11));
        }
        ak a2 = ak.a();
        a2.e();
        a2.a(new as(this));
        if (m()) {
            this.e = new e(this);
            registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (q()) {
            r();
        }
        if (!getPackageName().equals(com.xiaomi.channel.commonutils.android.b.c(this))) {
            aw a3 = aw.a();
            if (a3.b()) {
                a3.a((Context) this);
            }
            a3.a(j(), "UPLOADER_FROM_XMPUSHSERVICE");
        }
        a(this.a);
        com.xiaomi.channel.commonutils.misc.f.a((Context) this).a(new d(this), (int) ACache.TIME_DAY);
        com.xiaomi.channel.commonutils.logger.b.a("XMPushService created pid = " + g);
    }

    public void onDestroy() {
        if (this.e != null) {
            unregisterReceiver(this.e);
        }
        unregisterReceiver(this.a);
        this.l.c();
        a(new ab(this, 2));
        a(new i(this));
        ak.a().e();
        ak.a().a((Context) this, 15);
        ak.a().d();
        this.h.b(this);
        at.a().b();
        com.xiaomi.push.service.timers.a.a();
        super.onDestroy();
        com.xiaomi.channel.commonutils.logger.b.a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        if (intent == null) {
            com.xiaomi.channel.commonutils.logger.b.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.logger.b.c(String.format("onStart() with intent.Action = %s, chid = %s", new Object[]{intent.getAction(), intent.getStringExtra(am.q)}));
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                com.xiaomi.channel.commonutils.logger.b.a("Service called on timer");
                if (l()) {
                    b(false);
                }
            } else if ("com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                com.xiaomi.channel.commonutils.logger.b.a("Service called on check alive.");
                if (l()) {
                    b(false);
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                a(new g(this, intent));
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return b;
    }
}
