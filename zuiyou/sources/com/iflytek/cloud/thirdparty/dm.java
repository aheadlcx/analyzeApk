package com.iflytek.cloud.thirdparty;

import android.content.Context;

final class dm implements Runnable {
    final /* synthetic */ Context a;

    dm(Context context) {
        this.a = context;
    }

    public final void run() {
        dj.c(this.a);
    }
}
