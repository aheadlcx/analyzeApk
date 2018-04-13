package com.sina.weibo.sdk.web;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ WeiboSdkWebActivity a;

    d(WeiboSdkWebActivity weiboSdkWebActivity) {
        this.a = weiboSdkWebActivity;
    }

    public void onClick(View view) {
        this.a.j = 0;
        this.a.f();
        this.a.c.reload();
    }
}
