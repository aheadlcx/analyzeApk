package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.IXAdContainer;
import java.util.HashMap;

class s implements Runnable {
    final /* synthetic */ IXAdContainer a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ p c;

    s(p pVar, IXAdContainer iXAdContainer, HashMap hashMap) {
        this.c = pVar;
        this.a = iXAdContainer;
        this.b = hashMap;
    }

    public void run() {
        this.c.b.a(this.a, this.b);
    }
}
