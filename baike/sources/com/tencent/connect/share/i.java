package com.tencent.connect.share;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.open.utils.c;

final class i extends Handler {
    final /* synthetic */ c a;

    i(Looper looper, c cVar) {
        this.a = cVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 101:
                this.a.a(0, message.getData().getStringArrayList("images"));
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
