package com.danikula.videocache.c;

import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.internal.b.f;
import okhttp3.t;
import okhttp3.y;
import okhttp3.y$a;

public class a implements t {
    private static a b;
    private int a = 20;
    private Map<String, List<String>> c;

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(Map<String, List<String>> map) {
        this.c = map;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        y a = aVar.a();
        if (this.c == null) {
            return aVar.a(a);
        }
        return a(aVar, a);
    }

    private aa a(okhttp3.t.a aVar, y yVar) throws IOException {
        String httpUrl = yVar.a().toString();
        String host = new URL(httpUrl).getHost();
        List list = (List) this.c.get(host);
        if (list == null || list.size() == 0) {
            return aVar.a(yVar);
        }
        host = (String) ((List) this.c.get(host)).get(0);
        aa a = a(aVar, yVar, httpUrl);
        Object f = a.a().a().f();
        String httpUrl2 = a.a().a().toString();
        if (!a.c()) {
            a = aVar.a(yVar.f().a(httpUrl2.replace(f, host)).b("Host", f).d());
            if (!a.c()) {
                list.remove(0);
                list.add(host);
            }
        }
        return a;
    }

    private aa a(okhttp3.t.a aVar, y yVar, String str) throws IOException {
        aa a;
        IOException e;
        y d = yVar.f().a(str).d();
        int i = 0;
        aa aaVar = null;
        aa aaVar2 = null;
        while (true) {
            try {
                aaVar = aVar.a(d);
                if (aaVar2 != null) {
                    a = aaVar.h().c(aaVar2.h().a(null).a()).a();
                } else {
                    a = aaVar;
                }
                try {
                    d = a(a);
                    if (d == null) {
                        break;
                    }
                    i++;
                    if (i > this.a) {
                        throw new ProtocolException("Too many follow-up requests: " + i);
                    }
                    aaVar = a;
                    aaVar2 = a;
                } catch (IOException e2) {
                    e = e2;
                    if (!!(e instanceof SocketTimeoutException)) {
                    }
                    if (a == null) {
                    }
                    aaVar = a;
                }
            } catch (IOException e3) {
                IOException iOException = e3;
                a = aaVar;
                e = iOException;
                if (!(e instanceof SocketTimeoutException) && !(e instanceof SocketException)) {
                    throw e;
                } else if (a == null && a.a() != null) {
                    return a;
                } else {
                    aaVar = a;
                }
            }
        }
        return a;
    }

    private y a(aa aaVar) throws IOException {
        if (aaVar == null) {
            throw new IllegalStateException();
        }
        int b = aaVar.b();
        String b2 = aaVar.a().b();
        switch (b) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
                if (!(b2.equals(Constants.HTTP_GET) || b2.equals("HEAD"))) {
                    return null;
                }
            default:
                return null;
        }
        String a = aaVar.a("Location");
        if (a == null) {
            return null;
        }
        HttpUrl c = aaVar.a().a().c(a);
        if (c == null || !c.b().equals(aaVar.a().a().b())) {
            return null;
        }
        y$a f = aaVar.a().f();
        if (f.c(b2)) {
            if (f.e(b2)) {
                f.a(Constants.HTTP_GET, null);
            } else {
                f.a(b2, null);
            }
            f.b("Transfer-Encoding");
            f.b("Content-Length");
            f.b("Content-Type");
        }
        f.b("Host");
        if (!a(aaVar, c)) {
            f.b("Authorization");
        }
        return f.a(c).d();
    }

    private boolean a(aa aaVar, HttpUrl httpUrl) {
        HttpUrl a = aaVar.a().a();
        return a.f().equals(httpUrl.f()) && a.g() == httpUrl.g() && a.b().equals(httpUrl.b());
    }
}
