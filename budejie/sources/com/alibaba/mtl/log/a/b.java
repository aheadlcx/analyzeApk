package com.alibaba.mtl.log.a;

import com.alibaba.mtl.log.e.e;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import com.alibaba.mtl.log.e.r;
import com.alibaba.mtl.log.e.t;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static String P = "http://adashx.m.taobao.com/rest/gc2";
    private static b a = new b();

    class a implements Runnable {
        final /* synthetic */ b b;

        a(b bVar) {
            this.b = bVar;
        }

        public void run() {
            if (l.isConnected()) {
                for (int i = 0; i < 8; i++) {
                    Map hashMap = new HashMap();
                    String b = a.b("b01n15");
                    String b2 = a.b("b01na");
                    hashMap.put("_b01n15", b);
                    hashMap.put("_b01na", b2);
                    try {
                        i.a("ConfigMgr", "config:" + t.b(b.P, hashMap, null));
                        com.alibaba.mtl.log.e.e.a a = e.a(1, r0, null, false);
                        if (a.e != null) {
                            a.h(new String(a.e, 0, a.e.length));
                            a.q();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }

    public static b a() {
        return a;
    }

    public void r() {
        r.a().b(new a(this));
    }
}
