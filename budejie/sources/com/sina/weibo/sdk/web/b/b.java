package com.sina.weibo.sdk.web.b;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.web.BaseWebViewRequestData;
import com.sina.weibo.sdk.web.WebRequestType;

public abstract class b {
    protected Context a;
    private BaseWebViewRequestData b;
    private String c;

    public interface a {
        void a(String str);

        void b(String str);
    }

    protected abstract void a(Bundle bundle);

    public abstract String b();

    protected abstract void b(Bundle bundle);

    public b(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        this(authInfo, webRequestType, str, 0, str2, str3, context);
    }

    public b(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        this.b = new BaseWebViewRequestData(authInfo, webRequestType, str, i, str2, str3);
        this.a = context;
        this.c = String.valueOf(System.currentTimeMillis());
    }

    public void a(Context context) {
        this.a = context;
    }

    public Bundle c(Bundle bundle) {
        if (this.b == null) {
            throw new NullPointerException("构造方法错误，请使用全参数的构造方法构建");
        }
        bundle.putSerializable("base", this.b);
        switch (b$1.a[this.b.getType().ordinal()]) {
            case 1:
                bundle.putInt("type", 0);
                break;
            case 2:
                bundle.putInt("type", 1);
                break;
            case 3:
                bundle.putInt("type", 2);
                break;
        }
        bundle.putString("_weibo_transaction", this.c);
        a(bundle);
        return bundle;
    }

    public void d(Bundle bundle) {
        this.b = (BaseWebViewRequestData) bundle.getSerializable("base");
        this.c = bundle.getString("_weibo_transaction");
        b(bundle);
    }

    public boolean a() {
        return false;
    }

    public void a(a aVar) {
    }

    public BaseWebViewRequestData c() {
        return this.b;
    }
}
