package com.xiaomi.push.service;

import com.xiaomi.stats.h;
import java.util.List;

final class ak implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;

    ak(List list, boolean z) {
        this.a = list;
        this.b = z;
    }

    public void run() {
        boolean a = aj.b("www.baidu.com:80");
        boolean z = a;
        for (String a2 : this.a) {
            a = z || aj.b(a2);
            if (a && !this.b) {
                break;
            }
            z = a;
        }
        a = z;
        h.a(a ? 1 : 2);
    }
}
