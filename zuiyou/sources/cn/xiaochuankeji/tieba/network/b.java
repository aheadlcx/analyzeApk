package cn.xiaochuankeji.tieba.network;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alibaba.sdk.android.httpdns.DegradationFilter;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.ArrayList;
import java.util.HashMap;

public class b {
    private static b a;
    private static String b = "184840";
    private final ArrayList<String> c = new ArrayList();
    private final HashMap<String, String> d = new HashMap();
    private HttpDnsService e;
    private boolean f = false;
    private String g;
    private long h;

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    private b() {
        this.c.add("api.izuiyou.com");
        this.c.add("file.izuiyou.com");
        this.c.add("tbvideo.ixiaochuan.cn");
    }

    public synchronized void a(final Context context, boolean z) {
        if (z) {
            this.f = true;
            this.e = HttpDns.getService(context, b);
            this.e.setPreResolveHosts(this.c);
            this.e.setExpiredIPEnabled(true);
            this.e.setDegradationFilter(new DegradationFilter(this) {
                final /* synthetic */ b b;

                public boolean shouldDegradeHttpDNS(String str) {
                    return this.b.a(context);
                }
            });
        }
    }

    public synchronized String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || !this.f || !this.c.contains(str)) {
            str2 = null;
        } else if ("api.izuiyou.com".equals(str)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (TextUtils.isEmpty(this.g) || currentTimeMillis - this.h > 36000000) {
                this.g = this.e.getIpByHostAsync(str);
                this.h = currentTimeMillis;
            }
            if (!TextUtils.isEmpty(this.g)) {
                this.d.put(str, this.g);
            }
            str2 = this.g;
        } else {
            str2 = this.e.getIpByHostAsync(str);
            if (!TextUtils.isEmpty(str2)) {
                this.d.put(str, str2);
            }
        }
        return str2;
    }

    public HashMap<String, String> b() {
        return this.d;
    }

    private boolean a(Context context) {
        String property;
        int parseInt;
        if ((VERSION.SDK_INT >= 14 ? 1 : null) != null) {
            property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            }
            parseInt = Integer.parseInt(property2);
        } else {
            property = Proxy.getHost(context);
            parseInt = Proxy.getPort(context);
        }
        if (property == null || r0 == -1) {
            return false;
        }
        return true;
    }
}
