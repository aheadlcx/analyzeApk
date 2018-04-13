package com.liulishuo.filedownloader.download;

import android.text.TextUtils;
import com.liulishuo.filedownloader.d.b;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class a {
    final int a;
    final String b;
    final b c;
    private b d;
    private String e;
    private Map<String, List<String>> f;
    private List<String> g;

    static class a {
        private Integer a;
        private String b;
        private String c;
        private b d;
        private b e;

        a() {
        }

        public a a(int i) {
            this.a = Integer.valueOf(i);
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a a(b bVar) {
            this.d = bVar;
            return this;
        }

        public a a(b bVar) {
            this.e = bVar;
            return this;
        }

        a a() {
            if (this.a != null && this.e != null && this.b != null) {
                return new a(this.e, this.a.intValue(), this.b, this.c, this.d);
            }
            throw new IllegalArgumentException();
        }
    }

    private a(b bVar, int i, String str, String str2, b bVar2) {
        this.a = i;
        this.b = str;
        this.e = str2;
        this.c = bVar2;
        this.d = bVar;
    }

    void a(long j) {
        if (j == this.d.b) {
            d.d(this, "no data download, no need to update", new Object[0]);
            return;
        }
        long j2 = this.d.d - (j - this.d.b);
        this.d = com.liulishuo.filedownloader.download.b.a.a(this.d.a, j, this.d.c, j2);
        if (d.a) {
            d.b(this, "after update profile:%s", this.d);
        }
    }

    com.liulishuo.filedownloader.a.b a() throws IOException, IllegalAccessException {
        com.liulishuo.filedownloader.a.b a = c.a().a(this.b);
        a(a);
        b(a);
        c(a);
        this.f = a.b();
        if (d.a) {
            d.c(this, "<---- %s request header %s", Integer.valueOf(this.a), this.f);
        }
        a.d();
        this.g = new ArrayList();
        a = com.liulishuo.filedownloader.a.d.a(this.f, a, this.g);
        if (d.a) {
            d.c(this, "----> %s response header %s", Integer.valueOf(this.a), a.c());
        }
        return a;
    }

    private void a(com.liulishuo.filedownloader.a.b bVar) {
        if (this.c != null) {
            HashMap a = this.c.a();
            if (a != null) {
                if (d.a) {
                    d.e(this, "%d add outside header: %s", Integer.valueOf(this.a), a);
                }
                for (Entry entry : a.entrySet()) {
                    String str = (String) entry.getKey();
                    List<String> list = (List) entry.getValue();
                    if (list != null) {
                        for (String a2 : list) {
                            bVar.a(str, a2);
                        }
                    }
                }
            }
        }
    }

    private void b(com.liulishuo.filedownloader.a.b bVar) throws ProtocolException {
        if (!bVar.a(this.e, this.d.a)) {
            if (!TextUtils.isEmpty(this.e)) {
                bVar.a("If-Match", this.e);
            }
            this.d.a(bVar);
        }
    }

    private void c(com.liulishuo.filedownloader.a.b bVar) {
        if (this.c == null || this.c.a().get("User-Agent") == null) {
            bVar.a("User-Agent", f.e());
        }
    }

    boolean b() {
        return this.d.b > 0;
    }

    String c() {
        if (this.g == null || this.g.isEmpty()) {
            return null;
        }
        return (String) this.g.get(this.g.size() - 1);
    }

    public Map<String, List<String>> d() {
        return this.f;
    }

    public b e() {
        return this.d;
    }
}
