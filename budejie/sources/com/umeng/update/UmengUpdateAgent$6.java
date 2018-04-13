package com.umeng.update;

import android.content.Context;
import android.widget.Toast;

class UmengUpdateAgent$6 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$6(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, "Please add Permission in AndroidManifest!", 1).show();
    }
}
