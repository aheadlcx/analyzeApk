package com.xiaomi.push.service;

import com.xiaomi.stats.h;
import java.util.List;

final class t implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;

    t(List list, boolean z) {
        this.a = list;
        this.b = z;
    }

    public void run() {
        boolean a = ae.b("www.baidu.com:80");
        boolean z = a;
        for (String a2 : this.a) {
            a = z || ae.b(a2);
            if (a && !this.b) {
                break;
            }
            z = a;
        }
        a = z;
        h.a(a ? 1 : 2);
    }
}
