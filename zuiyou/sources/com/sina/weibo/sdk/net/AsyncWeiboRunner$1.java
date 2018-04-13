package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

class AsyncWeiboRunner$1 extends Thread {
    final /* synthetic */ AsyncWeiboRunner this$0;
    private final /* synthetic */ String val$httpMethod;
    private final /* synthetic */ RequestListener val$listener;
    private final /* synthetic */ WeiboParameters val$params;
    private final /* synthetic */ String val$url;

    AsyncWeiboRunner$1(AsyncWeiboRunner asyncWeiboRunner, String str, String str2, WeiboParameters weiboParameters, RequestListener requestListener) {
        this.this$0 = asyncWeiboRunner;
        this.val$url = str;
        this.val$httpMethod = str2;
        this.val$params = weiboParameters;
        this.val$listener = requestListener;
    }

    public void run() {
        try {
            String openUrl = HttpManager.openUrl(AsyncWeiboRunner.access$0(this.this$0), this.val$url, this.val$httpMethod, this.val$params);
            if (this.val$listener != null) {
                this.val$listener.onComplete(openUrl);
            }
        } catch (WeiboException e) {
            if (this.val$listener != null) {
                this.val$listener.onWeiboException(e);
            }
        }
    }
}
