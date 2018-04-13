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
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.au;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class XMPushService extends Service implements com.xiaomi.smack.d {
    public static int c = 1;
    private static final int i = Process.myPid();
    Messenger a = null;
    final BroadcastReceiver b = new bp(this);
    private com.xiaomi.smack.b d;
    private ax e;
    private String f;
    private e g;
    private long h = 0;
    private com.xiaomi.slim.f j;
    private com.xiaomi.smack.a k;
    private e l;
    private PacketSync m = null;
    private l n = null;
    private ArrayList<k> o = new ArrayList();
    private com.xiaomi.smack.f p = new bh(this);

    public static abstract class h extends com.xiaomi.push.service.l.b {
        public h(int i) {
            super(i);
        }

        public abstract void a();

        public abstract String b();

        public void run() {
            if (!(this.a == 4 || this.a == 8)) {
                com.xiaomi.channel.commonutils.logger.b.a("JOB: " + b());
            }
            a();
        }
    }

    class a extends h {
        com.xiaomi.push.service.ap.b b = null;
        final /* synthetic */ XMPushService c;

        public a(XMPushService xMPushService, com.xiaomi.push.service.ap.b bVar) {
            this.c = xMPushService;
            super(9);
            this.b = bVar;
        }

        public void a() {
            try {
                if (this.c.f()) {
                    com.xiaomi.push.service.ap.b b = ap.a().b(this.b.h, this.b.b);
                    if (b == null) {
                        com.xiaomi.channel.commonutils.logger.b.a("ignore bind because the channel " + this.b.h + " is removed ");
                        return;
                    } else if (b.m == com.xiaomi.push.service.ap.c.unbind) {
                        b.a(com.xiaomi.push.service.ap.c.binding, 0, 0, null, null);
                        this.c.k.a(b);
                        com.xiaomi.stats.h.a(this.c, b);
                        return;
                    } else {
                        com.xiaomi.channel.commonutils.logger.b.a("trying duplicate bind, ingore! " + b.m);
                        return;
                    }
                }
                com.xiaomi.channel.commonutils.logger.b.d("trying bind while the connection is not created, quit!");
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                this.c.a(10, e);
            } catch (Throwable th) {
            }
        }

        public String b() {
            return "bind the client. " + this.b.h;
        }
    }

    static class b extends h {
        private final com.xiaomi.push.service.ap.b b;

        public b(com.xiaomi.push.service.ap.b bVar) {
            super(12);
            this.b = bVar;
        }

        public void a() {
            this.b.a(com.xiaomi.push.service.ap.c.unbind, 1, 21, null, null);
        }

        public String b() {
            return "bind time out. chid=" + this.b.h;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof b) ? false : TextUtils.equals(((b) obj).b.h, this.b.h);
        }

        public int hashCode() {
            return this.b.h.hashCode();
        }
    }

    class c extends h {
        final /* synthetic */ XMPushService b;
        private com.xiaomi.slim.b c = null;

        public c(XMPushService xMPushService, com.xiaomi.slim.b bVar) {
            this.b = xMPushService;
            super(8);
            this.c = bVar;
        }

        public void a() {
            this.b.m.a(this.c);
        }

        public String b() {
            return "receive a message.";
        }
    }

    public class d extends h {
        final /* synthetic */ XMPushService b;

        d(XMPushService xMPushService) {
            this.b = xMPushService;
            super(1);
        }

        public void a() {
            if (this.b.b()) {
                this.b.o();
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
            this.a.onStart(intent, XMPushService.c);
        }
    }

    public class f extends h {
        public int b;
        public Exception c;
        final /* synthetic */ XMPushService d;

        f(XMPushService xMPushService, int i, Exception exception) {
            this.d = xMPushService;
            super(2);
            this.b = i;
            this.c = exception;
        }

        public void a() {
            this.d.a(this.b, this.c);
        }

        public String b() {
            return "disconnect the connection.";
        }
    }

    class g extends h {
        final /* synthetic */ XMPushService b;
        private Intent c = null;

        public g(XMPushService xMPushService, Intent intent) {
            this.b = xMPushService;
            super(15);
            this.c = intent;
        }

        public void a() {
            this.b.c(this.c);
        }

        public String b() {
            return "Handle intent action = " + this.c.getAction();
        }
    }

    class i extends h {
        final /* synthetic */ XMPushService b;

        public i(XMPushService xMPushService) {
            this.b = xMPushService;
            super(5);
        }

        public void a() {
            this.b.n.b();
        }

        public String b() {
            return "ask the job queue to quit";
        }
    }

    class j extends h {
        final /* synthetic */ XMPushService b;
        private com.xiaomi.smack.packet.d c = null;

        public j(XMPushService xMPushService, com.xiaomi.smack.packet.d dVar) {
            this.b = xMPushService;
            super(8);
            this.c = dVar;
        }

        public void a() {
            this.b.m.a(this.c);
        }

        public String b() {
            return "receive a message.";
        }
    }

    public interface k {
        void a();
    }

    class l extends h {
        boolean b;
        final /* synthetic */ XMPushService c;

        public l(XMPushService xMPushService, boolean z) {
            this.c = xMPushService;
            super(4);
            this.b = z;
        }

        public void a() {
            if (this.c.f()) {
                try {
                    if (!this.b) {
                        com.xiaomi.stats.h.a();
                    }
                    this.c.k.b(this.b);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    this.c.a(10, e);
                }
            }
        }

        public String b() {
            return "send ping..";
        }
    }

    class m extends h {
        com.xiaomi.push.service.ap.b b = null;
        final /* synthetic */ XMPushService c;

        public m(XMPushService xMPushService, com.xiaomi.push.service.ap.b bVar) {
            this.c = xMPushService;
            super(4);
            this.b = bVar;
        }

        public void a() {
            try {
                this.b.a(com.xiaomi.push.service.ap.c.unbind, 1, 16, null, null);
                this.c.k.a(this.b.h, this.b.b);
                this.b.a(com.xiaomi.push.service.ap.c.binding, 1, 16, null, null);
                this.c.k.a(this.b);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                this.c.a(10, e);
            }
        }

        public String b() {
            return "rebind the client. " + this.b.h;
        }
    }

    class n extends h {
        final /* synthetic */ XMPushService b;

        n(XMPushService xMPushService) {
            this.b = xMPushService;
            super(3);
        }

        public void a() {
            this.b.a(11, null);
            if (this.b.b()) {
                this.b.o();
            }
        }

        public String b() {
            return "reset the connection.";
        }
    }

    class o extends h {
        com.xiaomi.push.service.ap.b b = null;
        int c;
        String d;
        String e;
        final /* synthetic */ XMPushService f;

        public o(XMPushService xMPushService, com.xiaomi.push.service.ap.b bVar, int i, String str, String str2) {
            this.f = xMPushService;
            super(9);
            this.b = bVar;
            this.c = i;
            this.d = str;
            this.e = str2;
        }

        public void a() {
            if (!(this.b.m == com.xiaomi.push.service.ap.c.unbind || this.f.k == null)) {
                try {
                    this.f.k.a(this.b.h, this.b.b);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    this.f.a(10, e);
                }
            }
            this.b.a(com.xiaomi.push.service.ap.c.unbind, this.c, 0, this.e, this.d);
        }

        public String b() {
            return "unbind the channel. " + this.b.h;
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

    private com.xiaomi.smack.packet.d a(com.xiaomi.smack.packet.d dVar, String str, String str2) {
        ap a = ap.a();
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
            com.xiaomi.push.service.ap.b b2 = a.b(l, dVar.n());
            if (!f()) {
                com.xiaomi.channel.commonutils.logger.b.a("drop a packet as the channel is not connected, chid=" + l);
            } else if (b2 == null || b2.m != com.xiaomi.push.service.ap.c.binded) {
                com.xiaomi.channel.commonutils.logger.b.a("drop a packet as the channel is not opened, chid=" + l);
            } else if (TextUtils.equals(str2, b2.j)) {
                return dVar;
            } else {
                com.xiaomi.channel.commonutils.logger.b.a("invalid session. " + str2);
            }
        }
        return null;
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra(as.y);
        String stringExtra2 = intent.getStringExtra(as.B);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        intent.getBooleanExtra("ext_encrypt", true);
        com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) a(new com.xiaomi.smack.packet.c(bundleExtra), stringExtra, stringExtra2);
        if (cVar != null) {
            c(new ay(this, com.xiaomi.slim.b.a(cVar, ap.a().b(cVar.l(), cVar.n()).i)));
        }
    }

    private void a(String str, int i) {
        Collection<com.xiaomi.push.service.ap.b> c = ap.a().c(str);
        if (c != null) {
            for (com.xiaomi.push.service.ap.b bVar : c) {
                if (bVar != null) {
                    a(new o(this, bVar, i, null, null));
                }
            }
        }
        ap.a().a(str);
    }

    public static boolean a(int i, String str) {
        return (TextUtils.equals(str, "Enter") && i == 1) ? true : TextUtils.equals(str, "Leave") && i == 2;
    }

    private boolean a(String str, Intent intent) {
        com.xiaomi.push.service.ap.b b = ap.a().b(str, intent.getStringExtra(as.p));
        boolean z = false;
        if (b == null || str == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra(as.B);
        String stringExtra2 = intent.getStringExtra(as.u);
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
        if (TextUtils.equals("Leave", str) && !TextUtils.equals("Enter", h.a(context).c(str2))) {
            return false;
        }
        if (h.a(context).a(str2, str) != 0) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.a("update geofence statue failed geo_id:" + str2);
        return false;
    }

    private com.xiaomi.push.service.ap.b b(String str, Intent intent) {
        com.xiaomi.push.service.ap.b b = ap.a().b(str, intent.getStringExtra(as.p));
        com.xiaomi.push.service.ap.b bVar = b == null ? new com.xiaomi.push.service.ap.b(this) : b;
        bVar.h = intent.getStringExtra(as.q);
        bVar.b = intent.getStringExtra(as.p);
        bVar.c = intent.getStringExtra(as.s);
        bVar.a = intent.getStringExtra(as.y);
        bVar.f = intent.getStringExtra(as.w);
        bVar.g = intent.getStringExtra(as.x);
        bVar.e = intent.getBooleanExtra(as.v, false);
        bVar.i = intent.getStringExtra(as.u);
        bVar.j = intent.getStringExtra(as.B);
        bVar.d = intent.getStringExtra(as.t);
        bVar.k = this.l;
        bVar.a((Messenger) intent.getParcelableExtra(as.F));
        bVar.l = getApplicationContext();
        ap.a().a(bVar);
        return bVar;
    }

    private void b(Intent intent) {
        int i = 0;
        String stringExtra = intent.getStringExtra(as.y);
        String stringExtra2 = intent.getStringExtra(as.B);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        com.xiaomi.smack.packet.c[] cVarArr = new com.xiaomi.smack.packet.c[parcelableArrayExtra.length];
        intent.getBooleanExtra("ext_encrypt", true);
        int i2 = 0;
        while (i2 < parcelableArrayExtra.length) {
            cVarArr[i2] = new com.xiaomi.smack.packet.c((Bundle) parcelableArrayExtra[i2]);
            cVarArr[i2] = (com.xiaomi.smack.packet.c) a(cVarArr[i2], stringExtra, stringExtra2);
            if (cVarArr[i2] != null) {
                i2++;
            } else {
                return;
            }
        }
        ap a = ap.a();
        com.xiaomi.slim.b[] bVarArr = new com.xiaomi.slim.b[cVarArr.length];
        while (i < cVarArr.length) {
            com.xiaomi.smack.packet.d dVar = cVarArr[i];
            bVarArr[i] = com.xiaomi.slim.b.a(dVar, a.b(dVar.l(), dVar.n()).i);
            i++;
        }
        c(new d(this, bVarArr));
    }

    private void b(boolean z) {
        this.h = System.currentTimeMillis();
        if (!f()) {
            a(true);
        } else if (this.k.p() || this.k.q() || com.xiaomi.channel.commonutils.network.d.e(this)) {
            c(new l(this, z));
        } else {
            c(new f(this, 17, null));
            a(true);
        }
    }

    private void c(Intent intent) {
        com.xiaomi.push.service.ap.b bVar = null;
        boolean z = true;
        boolean z2 = false;
        ap a = ap.a();
        String stringExtra;
        if (as.d.equalsIgnoreCase(intent.getAction()) || as.j.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(as.q);
            if (TextUtils.isEmpty(intent.getStringExtra(as.u))) {
                com.xiaomi.channel.commonutils.logger.b.a("security is empty. ignore.");
            } else if (stringExtra != null) {
                boolean a2 = a(stringExtra, intent);
                com.xiaomi.push.service.ap.b b = b(stringExtra, intent);
                if (!com.xiaomi.channel.commonutils.network.d.c(this)) {
                    this.l.a(this, b, false, 2, null);
                } else if (!f()) {
                    a(true);
                } else if (b.m == com.xiaomi.push.service.ap.c.unbind) {
                    c(new a(this, b));
                } else if (a2) {
                    c(new m(this, b));
                } else if (b.m == com.xiaomi.push.service.ap.c.binding) {
                    com.xiaomi.channel.commonutils.logger.b.a(String.format("the client is binding. %1$s %2$s.", new Object[]{b.h, b.b}));
                } else if (b.m == com.xiaomi.push.service.ap.c.binded) {
                    this.l.a(this, b, true, 0, null);
                }
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("channel id is empty, do nothing!");
            }
        } else if (as.i.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(as.y);
            r2 = intent.getStringExtra(as.q);
            Object stringExtra2 = intent.getStringExtra(as.p);
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
        } else if (as.e.equalsIgnoreCase(intent.getAction())) {
            a(intent);
        } else if (as.g.equalsIgnoreCase(intent.getAction())) {
            b(intent);
        } else if (as.f.equalsIgnoreCase(intent.getAction())) {
            r0 = a(new com.xiaomi.smack.packet.b(intent.getBundleExtra("ext_packet")), intent.getStringExtra(as.y), intent.getStringExtra(as.B));
            if (r0 != null) {
                c(new ay(this, com.xiaomi.slim.b.a(r0, a.b(r0.l(), r0.n()).i)));
            }
        } else if (as.h.equalsIgnoreCase(intent.getAction())) {
            r0 = a(new com.xiaomi.smack.packet.f(intent.getBundleExtra("ext_packet")), intent.getStringExtra(as.y), intent.getStringExtra(as.B));
            if (r0 != null) {
                c(new ay(this, com.xiaomi.slim.b.a(r0, a.b(r0.l(), r0.n()).i)));
            }
        } else if (as.k.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(as.q);
            String stringExtra4 = intent.getStringExtra(as.p);
            if (stringExtra3 != null) {
                com.xiaomi.channel.commonutils.logger.b.a("request reset connection from chid = " + stringExtra3);
                com.xiaomi.push.service.ap.b b2 = ap.a().b(stringExtra3, stringExtra4);
                if (b2 != null && b2.i.equals(intent.getStringExtra(as.u)) && b2.m == com.xiaomi.push.service.ap.c.binded) {
                    com.xiaomi.smack.a h = h();
                    if (h == null || !h.a(System.currentTimeMillis() - 15000)) {
                        c(new n(this));
                    }
                }
            }
        } else if (as.l.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(as.y);
            List b3 = a.b(stringExtra3);
            if (b3.isEmpty()) {
                com.xiaomi.channel.commonutils.logger.b.a("open channel should be called first before update info, pkg=" + stringExtra3);
                return;
            }
            stringExtra3 = intent.getStringExtra(as.q);
            Object stringExtra5 = intent.getStringExtra(as.p);
            if (TextUtils.isEmpty(stringExtra3)) {
                stringExtra3 = (String) b3.get(0);
            }
            if (TextUtils.isEmpty(stringExtra5)) {
                Collection c = a.c(stringExtra3);
                if (!(c == null || c.isEmpty())) {
                    bVar = (com.xiaomi.push.service.ap.b) c.iterator().next();
                }
            } else {
                bVar = a.b(stringExtra3, stringExtra5);
            }
            if (bVar != null) {
                if (intent.hasExtra(as.w)) {
                    bVar.f = intent.getStringExtra(as.w);
                }
                if (intent.hasExtra(as.x)) {
                    bVar.g = intent.getStringExtra(as.x);
                }
            }
        } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
            if (au.a(getApplicationContext()).a() && au.a(getApplicationContext()).b() == 0) {
                com.xiaomi.channel.commonutils.logger.b.a("register without being provisioned. " + intent.getStringExtra("mipush_app_package"));
                return;
            }
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            String stringExtra6 = intent.getStringExtra("mipush_app_package");
            boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
            int intExtra = intent.getIntExtra("mipush_env_type", 1);
            u.a((Context) this).g(stringExtra6);
            if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                a(byteArrayExtra, stringExtra6);
            } else {
                c(new br(this, 14, intExtra, byteArrayExtra, stringExtra6));
            }
        } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("mipush_app_package");
            r1 = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
            if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                u.a((Context) this).d(stringExtra3);
            }
            a(stringExtra3, r1, booleanExtra2);
        } else if (av.a.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("uninstall_pkg_name");
            if (stringExtra3 != null && !TextUtils.isEmpty(stringExtra3.trim())) {
                try {
                    getPackageManager().getPackageInfo(stringExtra3, 0);
                    z = false;
                } catch (NameNotFoundException e) {
                }
                if ("com.xiaomi.channel".equals(stringExtra3) && !ap.a().c("1").isEmpty() && r9) {
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
                    if (ah.e(this, stringExtra3)) {
                        ah.d(this, stringExtra3);
                    }
                    ah.b(this, stringExtra3);
                    if (f() && r2 != null) {
                        try {
                            f.a(this, f.a(stringExtra3, r2));
                            com.xiaomi.channel.commonutils.logger.b.a("uninstall " + stringExtra3 + " msg sent");
                        } catch (Exception e2) {
                            com.xiaomi.channel.commonutils.logger.b.d("Fail to send Message: " + e2.getMessage());
                            a(10, e2);
                        }
                    }
                }
            }
        } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(as.y);
            r1 = intent.getIntExtra(as.z, -2);
            if (!TextUtils.isEmpty(stringExtra3)) {
                if (r1 >= -1) {
                    ah.a((Context) this, stringExtra3, r1);
                } else {
                    ah.a((Context) this, stringExtra3, intent.getStringExtra(as.D), intent.getStringExtra(as.E));
                }
            }
        } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
            r2 = intent.getStringExtra(as.y);
            CharSequence stringExtra7 = intent.getStringExtra(as.C);
            CharSequence b4;
            if (intent.hasExtra(as.A)) {
                r1 = intent.getIntExtra(as.A, 0);
                b4 = com.xiaomi.channel.commonutils.string.c.b(r2 + r1);
            } else {
                b4 = com.xiaomi.channel.commonutils.string.c.b(r2);
                r1 = 0;
                z2 = true;
            }
            if (TextUtils.isEmpty(r2) || !TextUtils.equals(stringExtra7, r0)) {
                com.xiaomi.channel.commonutils.logger.b.d("invalid notification for " + r2);
            } else if (z2) {
                ah.d(this, r2);
            } else {
                ah.b((Context) this, r2, r1);
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
            Object stringExtra8 = intent.getStringExtra("mipush_app_package");
            if (!TextUtils.isEmpty(stringExtra8)) {
                u.a((Context) this).e(stringExtra8);
            }
            if (!"com.xiaomi.xmsf".equals(getPackageName())) {
                if (this.g != null) {
                    unregisterReceiver(this.g);
                    this.g = null;
                }
                this.n.c();
                a(new bs(this, 2));
                ap.a().e();
                ap.a().a((Context) this, 0);
                ap.a().d();
                az.a().b();
                com.xiaomi.push.service.timers.a.a();
            }
        } else if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
            r2 = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            String stringExtra9 = intent.getStringExtra("mipush_app_id");
            String stringExtra10 = intent.getStringExtra("mipush_app_token");
            if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                u.a((Context) this).f(r2);
            }
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                u.a((Context) this).h(r2);
                u.a((Context) this).i(r2);
            }
            if (byteArrayExtra2 == null) {
                w.a(this, r2, byteArrayExtra2, 70000003, "null payload");
                return;
            }
            w.b(r2, byteArrayExtra2);
            a(new v(this, r2, stringExtra9, stringExtra10, byteArrayExtra2));
            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.g == null) {
                this.g = new e(this);
                registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
        } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("mipush_app_package");
            r1 = intent.getByteArrayExtra("mipush_payload");
            com.xiaomi.xmpush.thrift.f fVar = new com.xiaomi.xmpush.thrift.f();
            try {
                au.a((org.apache.thrift.a) fVar, r1);
                com.xiaomi.tinyData.b.a(this).a(fVar, stringExtra3);
            } catch (Throwable e3) {
                com.xiaomi.channel.commonutils.logger.b.a(e3);
            }
        } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
            com.xiaomi.channel.commonutils.logger.b.a("Service called on timer");
            if (l()) {
                b(false);
            }
        } else if ("com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
            com.xiaomi.channel.commonutils.logger.b.a("Service called on check alive.");
            if (l()) {
                b(false);
            }
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            k();
        }
    }

    private void c(h hVar) {
        this.n.a((com.xiaomi.push.service.l.b) hVar);
    }

    private void c(boolean z) {
        try {
            if (!com.xiaomi.channel.commonutils.android.h.d()) {
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
            State state = activeNetworkInfo.getState();
            if (state == State.SUSPENDED || state == State.UNKNOWN) {
                return;
            }
        }
        com.xiaomi.channel.commonutils.logger.b.a("network changed, no active network");
        if (com.xiaomi.stats.f.b() != null) {
            com.xiaomi.stats.f.b().b();
        }
        com.xiaomi.smack.util.g.a((Context) this);
        this.j.r();
        if (com.xiaomi.channel.commonutils.network.d.c(this)) {
            if (f() && l()) {
                b(false);
            }
            if (!(f() || g())) {
                this.n.b(1);
                a(new d(this));
            }
            com.xiaomi.push.log.b.a((Context) this).a();
        } else {
            a(new f(this, 2, null));
        }
        n();
    }

    private boolean l() {
        return System.currentTimeMillis() - this.h < 30000 ? false : com.xiaomi.channel.commonutils.network.d.d(this);
    }

    private boolean m() {
        return "com.xiaomi.xmsf".equals(getPackageName()) || !u.a((Context) this).b(getPackageName());
    }

    private void n() {
        if (!b()) {
            com.xiaomi.push.service.timers.a.a();
        } else if (!com.xiaomi.push.service.timers.a.b()) {
            com.xiaomi.push.service.timers.a.a(true);
        }
    }

    private void o() {
        if (this.k != null && this.k.k()) {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while connecting.");
        } else if (this.k == null || !this.k.l()) {
            this.d.b(com.xiaomi.channel.commonutils.network.d.k(this));
            p();
            if (this.k == null) {
                ap.a().a((Context) this);
                c(false);
            }
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while is connected.");
        }
    }

    private void p() {
        try {
            this.j.a(this.p, new bj(this));
            this.j.t();
            this.k = this.j;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a("fail to create Slim connection", e);
            this.j.b(3, e);
        }
    }

    private boolean q() {
        return TextUtils.equals(getPackageName(), "com.xiaomi.xmsf") ? false : am.a((Context) this).a(com.xiaomi.xmpush.thrift.g.ForegroundServiceSwitch.a(), false);
    }

    private void r() {
        if (VERSION.SDK_INT < 18) {
            startForeground(i, new Notification());
        } else {
            bindService(new Intent(this, XMJobService.class), new bk(this), 1);
        }
    }

    private void s() {
        synchronized (this.o) {
            this.o.clear();
        }
    }

    void a() {
        if (System.currentTimeMillis() - this.h >= ((long) com.xiaomi.smack.g.c()) && com.xiaomi.channel.commonutils.network.d.d(this)) {
            b(true);
        }
    }

    public void a(int i) {
        this.n.b(i);
    }

    public void a(int i, Exception exception) {
        com.xiaomi.channel.commonutils.logger.b.a("disconnect " + hashCode() + ", " + (this.k == null ? null : Integer.valueOf(this.k.hashCode())));
        if (this.k != null) {
            this.k.b(i, exception);
            this.k = null;
        }
        a(7);
        a(4);
        ap.a().a((Context) this, i);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.metoknlp.geofencing.state_change_protected");
        registerReceiver(broadcastReceiver, intentFilter, "com.xiaomi.metoknlp.permission.NOTIFY_FENCE_STATE", null);
    }

    public void a(h hVar) {
        a(hVar, 0);
    }

    public void a(h hVar, long j) {
        try {
            this.n.a((com.xiaomi.push.service.l.b) hVar, j);
        } catch (IllegalStateException e) {
        }
    }

    public void a(k kVar) {
        synchronized (this.o) {
            this.o.add(kVar);
        }
    }

    public void a(com.xiaomi.push.service.ap.b bVar) {
        if (bVar != null) {
            long b = bVar.b();
            com.xiaomi.channel.commonutils.logger.b.a("schedule rebind job in " + (b / 1000));
            a(new a(this, bVar), b);
        }
    }

    public void a(com.xiaomi.slim.b bVar) {
        if (this.k != null) {
            this.k.b(bVar);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void a(com.xiaomi.smack.a aVar) {
        com.xiaomi.stats.f.b().a(aVar);
        c(true);
        this.e.a();
        Iterator it = ap.a().b().iterator();
        while (it.hasNext()) {
            a(new a(this, (com.xiaomi.push.service.ap.b) it.next()));
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

    public void a(String str, String str2, int i, String str3, String str4) {
        com.xiaomi.push.service.ap.b b = ap.a().b(str, str2);
        if (b != null) {
            a(new o(this, b, i, str4, str3));
        }
        ap.a().a(str, str2);
    }

    void a(String str, byte[] bArr, boolean z) {
        Collection c = ap.a().c("5");
        if (c.isEmpty()) {
            if (z) {
                w.b(str, bArr);
            }
        } else if (((com.xiaomi.push.service.ap.b) c.iterator().next()).m == com.xiaomi.push.service.ap.c.binded) {
            c(new bt(this, 4, str, bArr));
        } else if (z) {
            w.b(str, bArr);
        }
    }

    public void a(boolean z) {
        this.e.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            w.a(this, str, bArr, 70000003, "null payload");
            com.xiaomi.channel.commonutils.logger.b.a("register request without payload");
            return;
        }
        org.apache.thrift.a afVar = new af();
        try {
            au.a(afVar, bArr);
            if (afVar.a == com.xiaomi.xmpush.thrift.a.Registration) {
                org.apache.thrift.a ajVar = new aj();
                try {
                    au.a(ajVar, afVar.f());
                    w.a(afVar.j(), bArr);
                    a(new v(this, afVar.j(), ajVar.d(), ajVar.h(), bArr));
                    return;
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    w.a(this, str, bArr, 70000003, " data action error.");
                    return;
                }
            }
            w.a(this, str, bArr, 70000003, " registration action required.");
            com.xiaomi.channel.commonutils.logger.b.a("register request with invalid payload");
        } catch (Throwable e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            w.a(this, str, bArr, 70000003, " data container error.");
        }
    }

    public void a(com.xiaomi.slim.b[] bVarArr) {
        if (this.k != null) {
            this.k.a(bVarArr);
            return;
        }
        throw new com.xiaomi.smack.l("try send msg while connection is null.");
    }

    public void b(h hVar) {
        this.n.a(hVar.a, (com.xiaomi.push.service.l.b) hVar);
    }

    public void b(com.xiaomi.smack.a aVar) {
        com.xiaomi.channel.commonutils.logger.b.c("begin to connect...");
        com.xiaomi.stats.f.b().b(aVar);
    }

    public boolean b() {
        return com.xiaomi.channel.commonutils.network.d.c(this) && ap.a().c() > 0 && !c() && m();
    }

    public boolean b(int i) {
        return this.n.a(i);
    }

    public boolean c() {
        try {
            Class cls = Class.forName("miui.os.Build");
            return cls.getField("IS_CM_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CU_CUSTOMIZATION_TEST").getBoolean(null) || cls.getField("IS_CT_CUSTOMIZATION_TEST").getBoolean(null);
        } catch (Throwable th) {
            return false;
        }
    }

    public e d() {
        return new e();
    }

    public e e() {
        return this.l;
    }

    public boolean f() {
        return this.k != null && this.k.l();
    }

    public boolean g() {
        return this.k != null && this.k.k();
    }

    public com.xiaomi.smack.a h() {
        return this.k;
    }

    void i() {
        Iterator it = new ArrayList(this.o).iterator();
        while (it.hasNext()) {
            ((k) it.next()).a();
        }
    }

    public IBinder onBind(Intent intent) {
        return this.a.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        com.xiaomi.channel.commonutils.android.h.a(this);
        s a = t.a(this);
        if (a != null) {
            com.xiaomi.channel.commonutils.misc.a.a(a.g);
        }
        Object a2 = a.a(getApplicationContext()).a();
        if (TextUtils.isEmpty(a2)) {
            this.f = PushChannelRegion.China.name();
        } else {
            this.f = a2;
            if (PushChannelRegion.Global.name().equals(this.f)) {
                com.xiaomi.smack.b.a("app.chat.global.xiaomi.net");
            }
        }
        this.a = new Messenger(new bl(this));
        at.a(this);
        this.d = new bm(this, null, 5222, "xiaomi.com", null);
        this.d.a(true);
        this.j = new com.xiaomi.slim.f(this, this.d);
        this.l = d();
        try {
            if (com.xiaomi.channel.commonutils.android.h.d()) {
                this.l.a((Context) this);
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        com.xiaomi.push.service.timers.a.a((Context) this);
        this.j.a(this);
        this.m = new PacketSync(this);
        this.e = new ax(this);
        new m().a();
        com.xiaomi.stats.f.a().a(this);
        this.n = new l("Connection Controller Thread");
        if (m()) {
            a(new bn(this, 11));
        }
        ap a3 = ap.a();
        a3.e();
        a3.a(new bo(this));
        if (m()) {
            this.g = new e(this);
            registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (q()) {
            r();
        }
        com.xiaomi.tinyData.b.a(this).a(new n(this), "UPLOADER_PUSH_CHANNEL");
        a(this.b);
        com.xiaomi.channel.commonutils.misc.h.a((Context) this).a(new g(this), 86400);
        a(new com.xiaomi.tinyData.a(this));
        com.xiaomi.channel.commonutils.logger.b.a("XMPushService created pid = " + i);
    }

    public void onDestroy() {
        if (this.g != null) {
            unregisterReceiver(this.g);
        }
        unregisterReceiver(this.b);
        this.n.c();
        a(new bi(this, 2));
        a(new i(this));
        ap.a().e();
        ap.a().a((Context) this, 15);
        ap.a().d();
        this.j.b(this);
        az.a().b();
        com.xiaomi.push.service.timers.a.a();
        s();
        super.onDestroy();
        com.xiaomi.channel.commonutils.logger.b.a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        if (intent == null) {
            com.xiaomi.channel.commonutils.logger.b.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.logger.b.c(String.format("onStart() with intent.Action = %s, chid = %s", new Object[]{intent.getAction(), intent.getStringExtra(as.q)}));
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.n.d()) {
                    com.xiaomi.channel.commonutils.logger.b.d("ERROR, the job controller is blocked.");
                    ap.a().a((Context) this, 14);
                    stopSelf();
                    return;
                }
                a(new g(this, intent));
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                a(new g(this, intent));
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return c;
    }
}
