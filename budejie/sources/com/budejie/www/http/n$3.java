package com.budejie.www.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.util.j;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.g.b;

class n$3 extends Handler {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Handler d;
    final /* synthetic */ n e;

    n$3(n nVar, Context context, String str, String str2, Handler handler) {
        this.e = nVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = handler;
    }

    public void handleMessage(Message message) {
        if (message.what == 816) {
            CharSequence string = ((Bundle) message.obj).getString(j.c);
            if (!TextUtils.isEmpty(string) && "0".equals(string)) {
                b.a(this.a, this.b, this.c, "topic");
                TipPopUp.a(this.e.a, TypeControl.share, TypeControl.tiezi);
            }
        }
        this.d.sendMessage(Message.obtain(message));
    }
}
