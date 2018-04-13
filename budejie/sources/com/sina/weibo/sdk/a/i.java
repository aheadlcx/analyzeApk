package com.sina.weibo.sdk.a;

import android.app.AlertDialog.Builder;
import android.content.Context;

public class i {
    public static int a(int i, Context context) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static void a(Context context, String str, String str2) {
        if (context != null) {
            new Builder(context).setTitle(str).setMessage(str2).create().show();
        }
    }
}
