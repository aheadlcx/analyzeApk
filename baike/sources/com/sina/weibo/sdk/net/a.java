package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

class a extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ WeiboParameters c;
    final /* synthetic */ RequestListener d;
    final /* synthetic */ AsyncWeiboRunner e;

    a(AsyncWeiboRunner asyncWeiboRunner, String str, String str2, WeiboParameters weiboParameters, RequestListener requestListener) {
        this.e = asyncWeiboRunner;
        this.a = str;
        this.b = str2;
        this.c = weiboParameters;
        this.d = requestListener;
    }

    public void run() {
        try {
            String openUrl = HttpManager.openUrl(AsyncWeiboRunner.a(this.e), this.a, this.b, this.c);
            if (this.d != null) {
                this.d.onComplete(openUrl);
            }
        } catch (WeiboException e) {
            if (this.d != null) {
                this.d.onWeiboException(e);
            }
        }
    }
}
