package com.baidu.mobads.production;

class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.h.resize(this.a.getProdBase().getWidth(), this.a.getProdBase().getHeight());
    }
}
