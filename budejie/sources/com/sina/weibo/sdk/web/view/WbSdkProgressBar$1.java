package com.sina.weibo.sdk.web.view;

import android.os.Handler;
import android.os.Message;

class WbSdkProgressBar$1 extends Handler {
    final /* synthetic */ WbSdkProgressBar a;

    WbSdkProgressBar$1(WbSdkProgressBar wbSdkProgressBar) {
        this.a = wbSdkProgressBar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 0:
                WbSdkProgressBar.a(this.a, false);
                return;
            default:
                return;
        }
    }
}
