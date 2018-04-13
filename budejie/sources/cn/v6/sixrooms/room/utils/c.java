package cn.v6.sixrooms.room.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class c extends Handler {
    final /* synthetic */ GiftAnimQueue a;

    c(GiftAnimQueue giftAnimQueue, Looper looper) {
        this.a = giftAnimQueue;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.dipatchGift();
                return;
            default:
                return;
        }
    }
}
