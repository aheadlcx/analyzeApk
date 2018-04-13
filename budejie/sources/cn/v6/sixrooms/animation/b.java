package cn.v6.sixrooms.animation;

import android.os.Handler;
import android.os.Message;

final class b extends Handler {
    final /* synthetic */ GiftAnimationManager a;

    b(GiftAnimationManager giftAnimationManager) {
        this.a = giftAnimationManager;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                GiftAnimationManager.c(this.a);
                return;
            case 1:
                GiftAnimationManager.d(this.a);
                return;
            default:
                return;
        }
    }
}
