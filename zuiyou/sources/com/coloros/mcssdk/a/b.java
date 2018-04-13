package com.coloros.mcssdk.a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.c.a;
import com.coloros.mcssdk.e.c;
import com.sina.weibo.sdk.constant.WBConstants;

public final class b extends c {
    public final c a(Context context, int i, Intent intent) {
        return 4105 == i ? a(intent) : null;
    }

    public final c a(Intent intent) {
        try {
            c bVar = new com.coloros.mcssdk.e.b();
            bVar.a(Integer.parseInt(a.a(intent.getStringExtra("command"))));
            bVar.b(Integer.parseInt(a.a(intent.getStringExtra("code"))));
            bVar.c(a.a(intent.getStringExtra("content")));
            bVar.a(a.a(intent.getStringExtra(WBConstants.SSO_APP_KEY)));
            bVar.b(a.a(intent.getStringExtra("appSecret")));
            bVar.f(a.a(intent.getStringExtra("appPackage")));
            com.coloros.mcssdk.c.c.a("OnHandleIntent-message:" + bVar.toString());
            return bVar;
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }
}
