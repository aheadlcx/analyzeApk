package com.tencent.connect.common;

import android.os.Handler;
import android.os.Message;
import com.tencent.open.a.f;

class a extends Handler {
    final /* synthetic */ AssistActivity a;

    a(AssistActivity assistActivity) {
        this.a = assistActivity;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (!this.a.isFinishing()) {
                    f.d("openSDK_LOG.AssistActivity", "-->finish by timeout");
                    this.a.finish();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
