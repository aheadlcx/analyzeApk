package com.baidu.mobads.production;

import com.baidu.mobads.vo.d;

class m implements Runnable {
    final /* synthetic */ d a;
    final /* synthetic */ a b;

    m(a aVar, d dVar) {
        this.b = aVar;
        this.a = dVar;
    }

    public void run() {
        this.b.b(this.a);
    }
}
