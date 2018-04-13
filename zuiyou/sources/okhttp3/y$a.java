package okhttp3;

import com.tencent.connect.common.Constants;
import javax.annotation.Nullable;
import okhttp3.internal.b.f;
import okhttp3.internal.c;

public class y$a {
    HttpUrl a;
    String b;
    s$a c;
    z d;
    Object e;

    public y$a() {
        this.b = Constants.HTTP_GET;
        this.c = new s$a();
    }

    y$a(y yVar) {
        this.a = yVar.a;
        this.b = yVar.b;
        this.d = yVar.d;
        this.e = yVar.e;
        this.c = yVar.c.b();
    }

    public y$a a(HttpUrl httpUrl) {
        if (httpUrl == null) {
            throw new NullPointerException("url == null");
        }
        this.a = httpUrl;
        return this;
    }

    public y$a a(String str) {
        if (str == null) {
            throw new NullPointerException("url == null");
        }
        if (str.regionMatches(true, 0, "ws:", 0, 3)) {
            str = "http:" + str.substring(3);
        } else {
            if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
        }
        HttpUrl e = HttpUrl.e(str);
        if (e != null) {
            return a(e);
        }
        throw new IllegalArgumentException("unexpected url: " + str);
    }

    public y$a a(String str, String str2) {
        this.c.c(str, str2);
        return this;
    }

    public y$a b(String str, String str2) {
        this.c.a(str, str2);
        return this;
    }

    public y$a b(String str) {
        this.c.b(str);
        return this;
    }

    public y$a a(s sVar) {
        this.c = sVar.b();
        return this;
    }

    public y$a a(d dVar) {
        String dVar2 = dVar.toString();
        if (dVar2.isEmpty()) {
            return b("Cache-Control");
        }
        return a("Cache-Control", dVar2);
    }

    public y$a a() {
        return a(Constants.HTTP_GET, null);
    }

    public y$a b() {
        return a("HEAD", null);
    }

    public y$a a(z zVar) {
        return a(Constants.HTTP_POST, zVar);
    }

    public y$a b(@Nullable z zVar) {
        return a("DELETE", zVar);
    }

    public y$a c() {
        return b(c.d);
    }

    public y$a a(String str, @Nullable z zVar) {
        if (str == null) {
            throw new NullPointerException("method == null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        } else if (zVar != null && !f.c(str)) {
            throw new IllegalArgumentException("method " + str + " must not have a request body.");
        } else if (zVar == null && f.b(str)) {
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        } else {
            this.b = str;
            this.d = zVar;
            return this;
        }
    }

    public y$a a(Object obj) {
        this.e = obj;
        return this;
    }

    public y d() {
        if (this.a != null) {
            return new y(this);
        }
        throw new IllegalStateException("url == null");
    }
}
