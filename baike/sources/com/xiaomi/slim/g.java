package com.xiaomi.slim;

class g extends Thread {
    final /* synthetic */ f a;

    g(f fVar, String str) {
        this.a = fVar;
        super(str);
    }

    public void run() {
        try {
            f.a(this.a).a();
        } catch (Exception e) {
            this.a.c(9, e);
        }
    }
}
