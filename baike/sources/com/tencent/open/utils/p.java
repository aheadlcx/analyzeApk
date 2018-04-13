package com.tencent.open.utils;

class p implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ a b;

    p(a aVar, Runnable runnable) {
        this.b = aVar;
        this.a = runnable;
    }

    public void run() {
        try {
            this.a.run();
        } finally {
            this.b.a();
        }
    }
}
