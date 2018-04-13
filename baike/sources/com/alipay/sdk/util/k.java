package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.cons.a;

public final class k {
    public static String a(Context context) {
        if (EnvUtils.isSandBox()) {
            return a.b;
        }
        if (context == null) {
            return a.a;
        }
        String str = a.a;
        if (TextUtils.isEmpty(str)) {
            return a.a;
        }
        return str;
    }
}
