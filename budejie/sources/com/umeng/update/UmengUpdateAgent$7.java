package com.umeng.update;

import android.content.Context;
import android.widget.Toast;

class UmengUpdateAgent$7 implements Runnable {
    private final /* synthetic */ Context a;

    UmengUpdateAgent$7(Context context) {
        this.a = context;
    }

    public void run() {
        Toast.makeText(this.a, "Please copy all resources (res/) from SDK to your project!", 1).show();
    }
}
