package com.sina.weibo.sdk.web.b;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.web.WebRequestType;

public class a extends b {
    public a(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, str2, str3, context);
    }

    public boolean a() {
        return super.a();
    }

    protected void a(Bundle bundle) {
    }

    protected void b(Bundle bundle) {
    }

    public String b() {
        return c().getUrl();
    }
}
