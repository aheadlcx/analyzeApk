package com.sina.weibo.sdk.web.param;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.web.WebPicUploadResult;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam.ExtraTaskCallback;

class b implements RequestListener {
    final /* synthetic */ ExtraTaskCallback a;
    final /* synthetic */ ShareWebViewRequestParam b;

    b(ShareWebViewRequestParam shareWebViewRequestParam, ExtraTaskCallback extraTaskCallback) {
        this.b = shareWebViewRequestParam;
        this.a = extraTaskCallback;
    }

    public void onWeiboException(WeiboException weiboException) {
        if (this.a != null) {
            this.a.onException("upload pic fail");
        }
    }

    public void onComplete(String str) {
        WebPicUploadResult parse = WebPicUploadResult.parse(str);
        if (parse != null && parse.getCode() == 1 && !TextUtils.isEmpty(parse.getPicId())) {
            this.b.c = parse.getPicId();
            if (this.a != null) {
                this.a.onComplete(this.b.c);
            }
        } else if (this.a != null) {
            this.a.onException("upload pic fail");
        }
    }
}
