package com.sina.weibo.sdk.auth;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

final class a implements RequestListener {
    final /* synthetic */ Context a;
    final /* synthetic */ RequestListener b;

    a(Context context, RequestListener requestListener) {
        this.a = context;
        this.b = requestListener;
    }

    public void onComplete(String str) {
        AccessTokenKeeper.writeAccessToken(this.a, Oauth2AccessToken.parseAccessToken(str));
        if (this.b != null) {
            this.b.onComplete(str);
        }
    }

    public void onWeiboException(WeiboException weiboException) {
        if (this.b != null) {
            this.b.onWeiboException(weiboException);
        }
    }
}
