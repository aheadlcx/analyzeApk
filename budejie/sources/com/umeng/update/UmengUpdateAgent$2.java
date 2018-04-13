package com.umeng.update;

import android.content.Context;
import android.widget.Toast;
import u.upd.k;

class UmengUpdateAgent$2 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$2(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, k.b(this.a), 0).show();
    }
}
