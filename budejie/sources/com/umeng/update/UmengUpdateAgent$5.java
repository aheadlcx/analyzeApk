package com.umeng.update;

import android.content.Context;
import android.widget.Toast;

class UmengUpdateAgent$5 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$5(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, "Please add Service in AndroidManifest!", 1).show();
    }
}
