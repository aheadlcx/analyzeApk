package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.IXAdContainer;
import java.util.HashMap;

class t implements Runnable {
    final /* synthetic */ IXAdContainer a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ p c;

    t(p pVar, IXAdContainer iXAdContainer, HashMap hashMap) {
        this.c = pVar;
        this.a = iXAdContainer;
        this.b = hashMap;
    }

    public void run() {
        this.c.b.b(this.a, this.b);
    }
}
