package com.alibaba.baichuan.android.trade.utils;

import android.content.Context;

public class i {
    public static int a(Context context, String str) {
        return a(context, "layout", str);
    }

    public static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }
}
