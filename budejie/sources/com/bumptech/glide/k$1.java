package com.bumptech.glide;

import com.bumptech.glide.d.g;

class k$1 implements Runnable {
    final /* synthetic */ g a;
    final /* synthetic */ k b;

    k$1(k kVar, g gVar) {
        this.b = kVar;
        this.a = gVar;
    }

    public void run() {
        this.a.a(this.b);
    }
}
