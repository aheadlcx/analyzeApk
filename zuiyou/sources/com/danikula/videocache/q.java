package com.danikula.videocache;

import android.content.Context;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import com.danikula.videocache.a.f;
import com.danikula.videocache.a.h;
import com.danikula.videocache.b.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.w;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

public class q {
    private Context a;
    private f b;
    private Map<String, List<String>> c;
    private w d;
    private int e;
    private int f;

    private static class a {
        private static final q a = new q();
    }

    public static q a() {
        return a.a;
    }

    private q() {
        this.a = BaseApplication.getAppContext();
        this.d = e();
    }

    public f b() {
        if (this.b == null) {
            this.b = new com.danikula.videocache.f.a(this.a).a(p.a(this.a)).a(new h(524288000)).a(new f()).a(100).a(new b(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public Map<String, String> a(String str) {
                    Map<String, String> hashMap = new HashMap();
                    Object property = System.getProperty("http.agent");
                    if (TextUtils.isEmpty(property)) {
                        property = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36";
                    }
                    hashMap.put("User-Agent", property);
                    return hashMap;
                }
            }).a();
        }
        return this.b;
    }

    public void a(Map<String, List<String>> map) {
        this.c = map;
        com.danikula.videocache.c.a.a().a((Map) map);
    }

    public List<String> a(String str) {
        if (TextUtils.isEmpty(str) || this.c == null || this.c.size() == 0) {
            return null;
        }
        return (List) this.c.get(str);
    }

    private w e() {
        okhttp3.w.a aVar = new okhttp3.w.a();
        aVar.a(com.danikula.videocache.c.a.a());
        aVar.a(new AllowAllHostnameVerifier());
        aVar.a(5, TimeUnit.SECONDS).b(10, TimeUnit.SECONDS).c(10, TimeUnit.SECONDS);
        return aVar.a();
    }

    public void a(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }
}
