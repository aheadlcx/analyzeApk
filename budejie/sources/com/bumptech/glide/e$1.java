package com.bumptech.glide;

import com.bumptech.glide.g.e;

class e$1 implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ e b;

    e$1(e eVar, e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void run() {
        if (!this.a.isCancelled()) {
            this.b.a(this.a);
        }
    }
}
