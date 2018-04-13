package com.iflytek.cloud.thirdparty;

import android.content.Context;

final class f implements Runnable {
    private final /* synthetic */ Context a;

    f(Context context) {
        this.a = context;
    }

    public final void run() {
        String a = a.a(this.a);
        if (a != null && !a.trim().equals("")) {
            b.h(this.a, "#lxapkmd5", a);
        }
    }
}
