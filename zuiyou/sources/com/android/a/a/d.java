package com.android.a.a;

import android.annotation.TargetApi;
import android.view.Window;

class d implements a {
    d() {
    }

    @TargetApi(21)
    public void a(Window window, int i) {
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
    }
}
