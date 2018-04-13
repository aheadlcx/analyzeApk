package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

class m {
    static m a = new m();

    m() {
    }

    public synchronized void a(Context context) {
        String l = de.l(context);
        if (!TextUtils.isEmpty(l)) {
            y.AP_LIST.a(System.currentTimeMillis(), l);
        }
    }
}
