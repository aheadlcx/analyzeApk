package com.iflytek.common;

import android.content.Context;
import com.iflytek.cloud.thirdparty.dj;
import com.iflytek.cloud.thirdparty.do;
import com.iflytek.cloud.thirdparty.dr;

public final class a {
    private static do a = c.a();

    public static void a(Context context) {
        dj.a(context);
        if (a != null) {
            a.a(context);
        }
    }

    public static void a(Context context, String str, String str2) {
        dj.a(context, str, str2);
        if (a != null) {
            a.a(context, str, str2);
        }
    }

    public static void a(boolean z) {
        dr.a(z);
    }
}
