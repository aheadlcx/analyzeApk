package com.baidu.location.a;

import com.baidu.location.b.h;
import com.baidu.location.f;

class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public void run() {
        if (h.h() || this.a.a(f.getServiceContext())) {
            this.a.d();
        }
    }
}
