package com.sina.weibo.sdk.web;

import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ WeiboSdkWebActivity a;

    c(WeiboSdkWebActivity weiboSdkWebActivity) {
        this.a = weiboSdkWebActivity;
    }

    public void onClick(View view) {
        this.a.i.closeWeb();
        this.a.d();
    }
}
