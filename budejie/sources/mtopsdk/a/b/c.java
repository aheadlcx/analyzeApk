package mtopsdk.a.b;

import android.support.v4.app.FragmentTransaction;
import java.util.HashMap;
import java.util.Map;

public class c {
    private String a;
    private String b = "GET";
    private Map c = new HashMap();
    private d d;
    private String e;
    private int f = 15000;
    private int g = 15000;
    private int h;
    private int i;

    public b a() {
        if (this.a != null) {
            return new b();
        }
        throw new IllegalStateException("url == null");
    }

    public c a(int i) {
        if (i > 0) {
            this.f = i;
        }
        return this;
    }

    public c a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("url == null");
        }
        this.a = str;
        return this;
    }

    public c a(String str, d dVar) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("method == null || method.length() == 0");
        }
        if (dVar != null) {
            Object obj = (com.taobao.tao.remotebusiness.listener.c.a(str) || str.equals("OPTIONS") || str.equals("DELETE")) ? 1 : null;
            if (obj == null) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
        }
        if (dVar == null && com.taobao.tao.remotebusiness.listener.c.a(str)) {
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }
        this.b = str;
        this.d = dVar;
        return this;
    }

    public c a(Map map) {
        if (map != null) {
            this.c = map;
        }
        return this;
    }

    public c b(int i) {
        if (i > 0) {
            this.g = i;
        }
        return this;
    }

    public c b(String str) {
        this.e = str;
        return this;
    }

    public c c(int i) {
        this.h = i;
        return this;
    }

    public c d(int i) {
        this.i = FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        return this;
    }
}
