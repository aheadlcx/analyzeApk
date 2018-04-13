package com.sprite.ads.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sprite.ads.internal.log.ADLog;

public class a {
    public static void a(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
            ADLog.d("打开链接失败 : " + str);
        }
    }
}
