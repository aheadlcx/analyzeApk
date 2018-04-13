package com.sina.weibo.sdk.web;

import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam.ExtraTaskCallback;

class b implements ExtraTaskCallback {
    final /* synthetic */ WeiboSdkWebActivity a;

    b(WeiboSdkWebActivity weiboSdkWebActivity) {
        this.a = weiboSdkWebActivity;
    }

    public void onComplete(String str) {
        this.a.c.loadUrl(this.a.h.getRequestUrl());
    }

    public void onException(String str) {
    }
}
