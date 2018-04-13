package com.sina.weibo.sdk.auth.a;

import android.app.Activity;
import android.content.Intent;
import com.sina.weibo.sdk.auth.BaseSsoHandler;

public class a extends BaseSsoHandler {
    public a(Activity activity) {
        super(activity);
    }

    protected void a(Intent intent, int i) {
        super.a(intent, i);
        if (i == 32974) {
            intent.putExtra("com.sina.weibo.intent.extra.REQUEST_CODE", i);
        }
    }

    protected void a() {
        super.a();
    }
}
