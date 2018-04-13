package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.be;
import com.xiaomi.xmpush.thrift.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MiTinyDataClient {

    public static class a {
        private static a a;
        private Context b;
        private String c;
        private Boolean d;
        private a e = new a(this);
        private final ArrayList<f> f = new ArrayList();

        public class a {
            public final ArrayList<f> a = new ArrayList();
            final /* synthetic */ a b;
            private ScheduledThreadPoolExecutor c = new ScheduledThreadPoolExecutor(1);
            private ScheduledFuture<?> d;
            private final Runnable e = new s(this);

            public a(a aVar) {
                this.b = aVar;
            }

            private void a() {
                if (this.d == null) {
                    this.d = this.c.scheduleAtFixedRate(this.e, 1000, 1000, TimeUnit.MILLISECONDS);
                }
            }

            private void b() {
                f fVar = (f) this.a.remove(0);
                for (org.apache.thrift.a aVar : be.a(Arrays.asList(new f[]{fVar}), this.b.b.getPackageName(), c.a(this.b.b).c(), 30720)) {
                    b.c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + fVar.d());
                    ac.a(this.b.b).a(aVar, com.xiaomi.xmpush.thrift.a.i, true, null);
                }
            }

            public void a(f fVar) {
                this.c.execute(new r(this, fVar));
            }
        }

        public static a a() {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new a();
                    }
                }
            }
            return a;
        }

        private void b(f fVar) {
            synchronized (this.f) {
                if (!this.f.contains(fVar)) {
                    this.f.add(fVar);
                    if (this.f.size() > 100) {
                        this.f.remove(0);
                    }
                }
            }
        }

        private boolean b(Context context) {
            if (!ac.a(context).d()) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                return packageInfo == null ? false : packageInfo.versionCode >= 108;
            } catch (Exception e) {
                return false;
            }
        }

        private boolean c(Context context) {
            return c.a(context).c() == null && !b(this.b);
        }

        private boolean c(f fVar) {
            if (be.a(fVar, false)) {
                return false;
            }
            if (this.d.booleanValue()) {
                b.c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + fVar.d());
                ac.a(this.b).a(fVar);
            } else {
                this.e.a(fVar);
            }
            return true;
        }

        public void a(Context context) {
            if (context == null) {
                b.a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.b = context;
            this.d = Boolean.valueOf(b(context));
            b("com.xiaomi.xmpushsdk.tinydataPending.init");
        }

        public synchronized boolean a(f fVar) {
            boolean z = false;
            synchronized (this) {
                if (fVar != null) {
                    if (!be.a(fVar, true)) {
                        boolean z2 = TextUtils.isEmpty(fVar.a()) && TextUtils.isEmpty(this.c);
                        boolean z3 = !b();
                        if (this.b == null || c(this.b)) {
                            z = true;
                        }
                        if (z3 || z2 || z) {
                            if (z2) {
                                b.c("MiTinyDataClient Pending " + fVar.d() + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.channel");
                            } else if (z3) {
                                b.c("MiTinyDataClient Pending " + fVar.d() + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.init");
                            } else if (z) {
                                b.c("MiTinyDataClient Pending " + fVar.d() + " reason is " + "com.xiaomi.xmpushsdk.tinydataPending.appId");
                            }
                            b(fVar);
                            z = true;
                        } else {
                            b.c("MiTinyDataClient Send item immediately." + fVar.d());
                            if (TextUtils.isEmpty(fVar.m())) {
                                fVar.f(MiPushClient.generatePacketID());
                            }
                            if (TextUtils.isEmpty(fVar.a())) {
                                fVar.a(this.c);
                            }
                            if (TextUtils.isEmpty(fVar.k())) {
                                fVar.e(this.b.getPackageName());
                            }
                            if (fVar.g() <= 0) {
                                fVar.b(System.currentTimeMillis());
                            }
                            z = c(fVar);
                        }
                    }
                }
            }
            return z;
        }

        public void b(String str) {
            b.c("MiTinyDataClient.processPendingList(" + str + ")");
            ArrayList arrayList = new ArrayList();
            synchronized (this.f) {
                arrayList.addAll(this.f);
                this.f.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a((f) it.next());
            }
        }

        public boolean b() {
            return this.b != null;
        }
    }

    public static boolean a(Context context, f fVar) {
        b.c("MiTinyDataClient.upload " + fVar.d());
        if (!a.a().b()) {
            a.a().a(context);
        }
        return a.a().a(fVar);
    }

    public static boolean a(Context context, String str, String str2, long j, String str3) {
        f fVar = new f();
        fVar.d(str);
        fVar.c(str2);
        fVar.a(j);
        fVar.b(str3);
        fVar.c(true);
        fVar.a("push_sdk_channel");
        return a(context, fVar);
    }
}
