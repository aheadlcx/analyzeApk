package net.tsz.afinal;

import android.content.Context;
import android.os.Build;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.packet.d;
import com.facebook.common.util.UriUtil;
import com.umeng.analytics.pro.x;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.tsz.afinal.a.a;
import net.tsz.afinal.a.e;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class b {
    private static int a = 10;
    private static int b = 10000;
    private static int c = 5;
    private static int d = 3;
    private static Context i;
    private static final ThreadFactory k = new b$1();
    private static final Executor l = Executors.newFixedThreadPool(d, k);
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;
    private static String v;
    private static String w;
    private static String x;
    private static String y;
    private final DefaultHttpClient e;
    private final HttpContext f;
    private String g = "utf-8";
    private final Map<String, String> h;
    private d j;

    public b(Context context, d dVar) {
        i = context;
        this.j = dVar;
        a();
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) b);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(a));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, b);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, b);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.f = new SyncBasicHttpContext(new BasicHttpContext());
        this.e = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.e.addRequestInterceptor(new b$2(this));
        this.e.addResponseInterceptor(new b$3(this));
        this.e.setHttpRequestRetryHandler(new e(c));
        this.h = new HashMap();
        c.a(dVar, new b$a(this), this);
    }

    public void a(String str) {
        if (str != null && str.trim().length() != 0) {
            this.g = str;
        }
    }

    public void a(CookieStore cookieStore) {
        this.f.setAttribute("http.cookie-store", cookieStore);
    }

    public void a(int i) {
        HttpParams params = this.e.getParams();
        ConnManagerParams.setTimeout(params, (long) i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpConnectionParams.setConnectionTimeout(params, i);
    }

    public void a(SSLSocketFactory sSLSocketFactory) {
        this.e.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
    }

    public void b(int i) {
        this.e.setHttpRequestRetryHandler(new e(i));
    }

    public void a(String str, String str2) {
        this.h.put(str, str2);
    }

    public void a(String str, a<? extends Object> aVar) {
        a(str, null, aVar);
    }

    public void a(String str, net.tsz.afinal.a.b bVar, a<? extends Object> aVar) {
        d();
        a(this.e, this.f, new HttpGet(a(str, bVar)), null, (a) aVar);
    }

    public void a() {
        m = a.c(i);
        n = a.d(i);
        o = a.e(i);
        p = a.a();
        q = a.a(i);
        r = a.b(i);
        s = a.f(i);
        t = a.b();
        u = a.g(i);
        v = Build.MODEL;
        w = "";
        x = "";
        y = a.h(i);
    }

    public static void b() {
        if (i != null) {
            o = a.e(i);
            s = a.e(i);
        }
    }

    public static void c() {
        if (i != null) {
            u = a.g(i);
        }
    }

    private void d() {
        String encode;
        UnsupportedEncodingException e;
        check();
        a("udid", m);
        a("mac", n);
        a("lang", o);
        a("devicever", p);
        a("sdkver", this.j.f());
        a("screenwidth", q);
        a("screenheight", r);
        a("corpid", this.j.c());
        a("appkey", this.j.a());
        a("appver", this.j.b());
        a("sex", this.j.j());
        a("age", this.j.j());
        a("module", this.j.e());
        a(d.n, "Android");
        a("model", v);
        a("openudid", "");
        a("dadid", "");
        a("dasid", "");
        a(x.G, s);
        a("crack", t);
        a("screenrotate", this.j.g());
        a(c.a, u);
        a(HistoryOpenHelper.COLUMN_UID, this.j.i());
        a(INoCaptchaComponent.token, this.j.h());
        a("dkey", w);
        String d = this.j.d();
        String str = "";
        try {
            encode = URLEncoder.encode(d, "utf-8");
            try {
                x = URLEncoder.encode(x, "utf-8");
                str = URLEncoder.encode(y, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                e.printStackTrace();
                a("market", encode);
                a("source", x);
                a("telcom", str);
            }
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException unsupportedEncodingException = e3;
            encode = d;
            e = unsupportedEncodingException;
            e.printStackTrace();
            a("market", encode);
            a("source", x);
            a("telcom", str);
        }
        a("market", encode);
        a("source", x);
        a("telcom", str);
    }

    private void check() {
        if (d(this.j.a())) {
            throw new IllegalArgumentException(this.j.a());
        } else if (d(this.j.b())) {
            throw new IllegalArgumentException(this.j.b());
        } else if (d(this.j.d())) {
            throw new IllegalArgumentException(this.j.d());
        }
    }

    private boolean d(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        return false;
    }

    public void b(String str, net.tsz.afinal.a.b bVar, a<? extends Object> aVar) {
        a(str, a(bVar), null, (a) aVar);
    }

    public void a(String str, HttpEntity httpEntity, String str2, a<? extends Object> aVar) {
        d();
        a(this.e, this.f, a(new HttpPost(str), httpEntity), str2, (a) aVar);
    }

    public net.tsz.afinal.a.c<File> a(String str, String str2, boolean z, a<File> aVar) {
        return a(str, null, str2, z, (a) aVar);
    }

    public net.tsz.afinal.a.c<File> a(String str, net.tsz.afinal.a.b bVar, String str2, boolean z, a<File> aVar) {
        HttpGet httpGet = new HttpGet(a(str, bVar));
        net.tsz.afinal.a.c<File> cVar = new net.tsz.afinal.a.c(this.e, this.f, aVar, this.g);
        cVar.a(l, new Object[]{httpGet, str2, Boolean.valueOf(z)});
        return cVar;
    }

    protected <T> void a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, a<T> aVar) {
        if (str != null) {
            httpUriRequest.addHeader(MIME.CONTENT_TYPE, str);
        }
        new net.tsz.afinal.a.c(defaultHttpClient, httpContext, aVar, this.g).a(l, new Object[]{httpUriRequest});
    }

    public static String a(String str, net.tsz.afinal.a.b bVar) {
        if (bVar == null) {
            return str;
        }
        return new StringBuilder(String.valueOf(str)).append("?").append(bVar.v()).toString();
    }

    private HttpEntity a(net.tsz.afinal.a.b bVar) {
        if (bVar != null) {
            return bVar.t();
        }
        return null;
    }

    private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }
}
