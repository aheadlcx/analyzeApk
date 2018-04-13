package com.umeng.update;

import android.content.Context;
import android.widget.Toast;

class UmengUpdateAgent$3 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$3(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, "Please set umeng appkey!", 1).show();
    }
}
