package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import com.baidu.mobstat.Config;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HostManager.HostManagerFactory;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.smack.util.d;
import com.xiaomi.stats.f;
import com.xiaomi.stats.h;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.im.TimeUtils;

public class an extends com.xiaomi.push.service.at.a implements HostManagerFactory {
    private XMPushService a;
    private long b;

    static class a implements HttpGet {
        a() {
        }

        public String a(String str) {
            Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(26));
            buildUpon.appendQueryParameter("osver", String.valueOf(VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", d.a(Build.MODEL + Config.TRACE_TODAY_VISIT_SPLIT + VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(j.c()));
            String builder = buildUpon.toString();
            com.xiaomi.channel.commonutils.logger.b.c("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String a = com.xiaomi.channel.commonutils.network.d.a(j.a(), url);
                h.a(url.getHost() + Config.TRACE_TODAY_VISIT_SPLIT + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
                return a;
            } catch (Exception e) {
                h.a(url.getHost() + Config.TRACE_TODAY_VISIT_SPLIT + port, -1, e);
                throw e;
            }
        }
    }

    static class b extends HostManagerV2 {
        protected b(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
            super(context, hostFilter, httpGet, str);
        }

        protected String a(ArrayList<String> arrayList, String str, String str2) {
            try {
                if (f.a().c()) {
                    str2 = at.e();
                }
                return super.a(arrayList, str, str2);
            } catch (IOException e) {
                IOException iOException = e;
                h.a(0, com.xiaomi.push.thrift.a.GSLB_ERR.a(), 1, null, com.xiaomi.channel.commonutils.network.d.d(this.c) ? 1 : 0);
                throw iOException;
            }
        }
    }

    an(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        com.xiaomi.push.service.at.a anVar = new an(xMPushService);
        at.a().a(anVar);
        synchronized (HostManager.class) {
            HostManager.setHostManagerFactory(anVar);
            HostManager.init(xMPushService, null, new a(), "0", "push", "2.2");
        }
    }

    public HostManager a(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
        return new b(context, hostFilter, httpGet, str);
    }

    public void a(com.xiaomi.push.protobuf.a.a aVar) {
    }

    public void a(com.xiaomi.push.protobuf.b.b bVar) {
        if (bVar.e() && bVar.d() && System.currentTimeMillis() - this.b > TimeUtils.HOUR) {
            com.xiaomi.channel.commonutils.logger.b.a("fetch bucket :" + bVar.d());
            this.b = System.currentTimeMillis();
            HostManager instance = HostManager.getInstance();
            instance.clear();
            instance.refreshFallbacks();
            com.xiaomi.smack.a h = this.a.h();
            if (h != null) {
                Fallback fallbacksByHost = instance.getFallbacksByHost(h.c().e());
                if (fallbacksByHost != null) {
                    boolean z;
                    ArrayList d = fallbacksByHost.d();
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).equals(h.d())) {
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
