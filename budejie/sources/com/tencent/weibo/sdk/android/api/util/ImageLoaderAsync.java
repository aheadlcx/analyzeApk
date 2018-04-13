package com.tencent.weibo.sdk.android.api.util;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class ImageLoaderAsync {

    public interface callBackImage {
        void callback(Drawable drawable, String str);
    }

    public Drawable loadImage(final String str, final callBackImage callbackimage) {
        final Handler anonymousClass1 = new Handler() {
            public void handleMessage(Message message) {
                callbackimage.callback((Drawable) message.obj, str);
            }
        };
        new Thread() {
            public void run() {
                anonymousClass1.sendMessage(anonymousClass1.obtainMessage(0, Util.loadImageFromUrl(str)));
            }
        }.start();
        return null;
    }
}
