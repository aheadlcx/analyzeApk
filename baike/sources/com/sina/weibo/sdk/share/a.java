package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.sina.weibo.sdk.constant.WBConstants.Response;

class a extends Handler {
    final /* synthetic */ WbShareTransActivity a;

    a(WbShareTransActivity wbShareTransActivity) {
        this.a = wbShareTransActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(Response.ERRCODE, 1);
        intent.putExtras(bundle);
        intent.setFlags(131072);
        intent.setClassName(this.a, this.a.b);
        this.a.startActivity(intent);
        this.a.finish();
    }
}
