package com.umeng.update;

import android.content.Context;
import android.widget.Toast;

class UmengUpdateAgent$4 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$4(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, "Please add Activity in AndroidManifest!", 1).show();
    }
}
