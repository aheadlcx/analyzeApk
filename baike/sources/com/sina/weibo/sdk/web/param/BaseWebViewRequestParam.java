package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.web.BaseWebViewRequestData;
import com.sina.weibo.sdk.web.WebRequestType;

public abstract class BaseWebViewRequestParam {
    protected Context a;
    private BaseWebViewRequestData b;
    private String c;

    public interface ExtraTaskCallback {
        void onComplete(String str);

        void onException(String str);
    }

    protected abstract void a(Bundle bundle);

    protected abstract void b(Bundle bundle);

    public abstract String getRequestUrl();

    public abstract void updateRequestUrl(String str);

    public BaseWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        this(authInfo, webRequestType, str, 0, str2, str3, context);
    }

    public BaseWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        this.b = new BaseWebViewRequestData(authInfo, webRequestType, str, i, str2, str3);
        this.a = context;
        this.c = String.valueOf(System.currentTimeMillis());
    }

    public void setContext(Context context) {
        this.a = context;
    }

    public Context getContext() {
        return this.a;
    }

    public Bundle fillBundle(Bundle bundle) {
        if (this.b == null) {
            throw new NullPointerException("构造方法错误，请使用全参数的构造方法构建");
        }
        bundle.putParcelable("base", this.b);
        switch (a.a[this.b.getType().ordinal()]) {
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
        bundle.putString(WBConstants.TRAN, this.c);
        a(bundle);
        return bundle;
    }

    public void transformBundle(Bundle bundle) {
        this.b = (BaseWebViewRequestData) bundle.getParcelable("base");
        this.c = bundle.getString(WBConstants.TRAN);
        b(bundle);
    }

    public boolean hasExtraTask() {
        return false;
    }

    public void doExtraTask(ExtraTaskCallback extraTaskCallback) {
    }

    public BaseWebViewRequestData getBaseData() {
        return this.b;
    }
}
