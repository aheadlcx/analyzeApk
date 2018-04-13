package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.smack.util.d;
import com.xiaomi.stats.f;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class at extends com.xiaomi.push.service.az.a implements com.xiaomi.network.HostManager.a {
    private XMPushService a;
    private long b;

    static class a implements com.xiaomi.network.HostManager.b {
        a() {
        }

        public String a(String str) {
            Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(31));
            buildUpon.appendQueryParameter("osver", String.valueOf(VERSION.SDK_INT));
            buildUpon.appendQueryParameter(g.p, d.a(Build.MODEL + ":" + VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(h.c()));
            String builder = buildUpon.toString();
            com.xiaomi.channel.commonutils.logger.b.c("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String a = com.xiaomi.channel.commonutils.network.d.a(h.a(), url);
                com.xiaomi.stats.h.a(url.getHost() + ":" + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
                return a;
            } catch (Exception e) {
                com.xiaomi.stats.h.a(url.getHost() + ":" + port, -1, e);
                throw e;
            }
        }
    }

    static class b extends HostManager {
        protected b(Context context, com.xiaomi.network.d dVar, com.xiaomi.network.HostManager.b bVar, String str) {
            super(context, dVar, bVar, str);
        }

        protected String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2, boolean z) {
            try {
                if (f.a().c()) {
                    str2 = az.e();
                }
                return super.getRemoteFallbackJSON(arrayList, str, str2, z);
            } catch (IOException e) {
                IOException iOException = e;
                com.xiaomi.stats.h.a(0, com.xiaomi.push.thrift.a.GSLB_ERR.a(), 1, null, com.xiaomi.channel.commonutils.network.d.c(sAppContext) ? 1 : 0);
                throw iOException;
            }
        }
    }

    at(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        com.xiaomi.push.service.az.a atVar = new at(xMPushService);
        az.a().a(atVar);
        synchronized (HostManager.class) {
            HostManager.setHostManagerFactory(atVar);
            HostManager.init(xMPushService, null, new a(), "0", "push", "2.2");
        }
    }

    public HostManager a(Context context, com.xiaomi.network.d dVar, com.xiaomi.network.HostManager.b bVar, String str) {
        return new b(context, dVar, bVar, str);
    }

    public void a(com.xiaomi.push.protobuf.a.a aVar) {
    }

    public void a(com.xiaomi.push.protobuf.b.b bVar) {
        if (bVar.e() && bVar.d() && System.currentTimeMillis() - this.b > com.umeng.analytics.a.j) {
            com.xiaomi.channel.commonutils.logger.b.a("fetch bucket :" + bVar.d());
            this.b = System.currentTimeMillis();
            HostManager instance = HostManager.getInstance();
            instance.clear();
            instance.refreshFallbacks();
            com.xiaomi.smack.a h = this.a.h();
            if (h != null) {
                Fallback fallbacksByHost = instance.getFallbacksByHost(h.d().e());
                if (fallbacksByHost != null) {
                    boolean z;
                    ArrayList d = fallbacksByHost.d();
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).equals(h.e())) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                    if (z && !d.isEmpty()) {
                        com.xiaomi.channel.commonutils.logger.b.a("bucket changed, force reconnect");
                        this.a.a(0, null);
                        this.a.a(false);
                    }
                }
            }
        }
    }
}
