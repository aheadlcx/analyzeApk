package com.android.a.a;

import android.annotation.TargetApi;
import android.view.View;
import android.view.Window;

class e implements a {
    e() {
    }

    @TargetApi(23)
    public void a(Window window, int i) {
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
        View findViewById = window.findViewById(16908290);
        if (findViewById != null) {
            findViewById.setForeground(null);
        }
    }
}
