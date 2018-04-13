package com.facebook.imagepipeline.producers;

import java.util.concurrent.atomic.AtomicBoolean;

class ac$3 extends e {
    final /* synthetic */ AtomicBoolean a;
    final /* synthetic */ ac b;

    ac$3(ac acVar, AtomicBoolean atomicBoolean) {
        this.b = acVar;
        this.a = atomicBoolean;
    }

    public void a() {
        this.a.set(true);
    }
}
