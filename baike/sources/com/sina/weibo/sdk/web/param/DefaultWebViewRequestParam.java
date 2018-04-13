package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.web.WebRequestType;

public class DefaultWebViewRequestParam extends BaseWebViewRequestParam {
    public DefaultWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, str2, str3, context);
    }

    public boolean hasExtraTask() {
        return super.hasExtraTask();
    }

    protected void a(Bundle bundle) {
    }

    protected void b(Bundle bundle) {
    }

    public String getRequestUrl() {
        return getBaseData().getUrl();
    }

    public void updateRequestUrl(String str) {
    }
}
