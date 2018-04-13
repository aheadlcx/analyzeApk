package com.baidu.lbsapi.auth;

class e implements Runnable {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void run() {
        b.a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.a.a(new g(this.a.a).a(this.a.b));
    }
}
