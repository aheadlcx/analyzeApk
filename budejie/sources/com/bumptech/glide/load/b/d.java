package com.bumptech.glide.load.b;

import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class d {
    private final URL a;
    private final e b;
    private final String c;
    private String d;
    private URL e;

    public d(URL url) {
        this(url, e.b);
    }

    public d(String str) {
        this(str, e.b);
    }

    public d(URL url, e eVar) {
        if (url == null) {
            throw new IllegalArgumentException("URL must not be null!");
        } else if (eVar == null) {
            throw new IllegalArgumentException("Headers must not be null");
        } else {
            this.a = url;
            this.c = null;
            this.b = eVar;
        }
    }

    public d(String str, e eVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("String url must not be empty or null: " + str);
        } else if (eVar == null) {
            throw new IllegalArgumentException("Headers must not be null");
        } else {
            this.c = str;
            this.a = null;
            this.b = eVar;
        }
    }

    public URL a() throws MalformedURLException {
        return d();
    }

    private URL d() throws MalformedURLException {
        if (this.e == null) {
            this.e = new URL(e());
        }
        return this.e;
    }

    private String e() {
        if (TextUtils.isEmpty(this.d)) {
            String str = this.c;
            if (TextUtils.isEmpty(str)) {
                str = this.a.toString();
            }
            this.d = Uri.encode(str, "@#&=*+-_.,:!?()/~'%");
        }
        return this.d;
    }

    public Map<String, String> b() {
        return this.b.a();
    }

    public String c() {
        return this.c != null ? this.c : this.a.toString();
    }

    public String toString() {
        return c() + '\n' + this.b.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (c().equals(dVar.c()) && this.b.equals(dVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (c().hashCode() * 31) + this.b.hashCode();
    }
}
