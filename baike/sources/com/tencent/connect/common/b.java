package com.tencent.connect.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.UiError;

class b extends Handler {
    final /* synthetic */ BaseApi a;
    final /* synthetic */ TempRequestListener b;

    b(TempRequestListener tempRequestListener, Looper looper, BaseApi baseApi) {
        this.b = tempRequestListener;
        this.a = baseApi;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (message.what == 0) {
            this.b.b.onComplete(message.obj);
        } else {
            this.b.b.onError(new UiError(message.what, (String) message.obj, null));
        }
    }
}
