package com.tencent.connect.auth;

import android.app.Activity;

class e implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ d b;

    e(d dVar, Activity activity) {
        this.b = dVar;
        this.a = activity;
    }

    public void run() {
        a aVar = new a(this.a, "action_login", this.b.a, this.b.b, this.b.c.b);
        if (this.a != null && !this.a.isFinishing()) {
            aVar.show();
        }
    }
}
