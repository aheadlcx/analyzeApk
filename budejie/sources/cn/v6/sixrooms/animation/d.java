package cn.v6.sixrooms.animation;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.v6.sixrooms.bitmap.AnimationBitmapManager;

final class d extends Handler {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                AnimationBitmapManager.addBitmap(message.arg1, message.getData().getString("url"), (Bitmap) message.obj);
                return;
            case 2:
                Looper.myLooper().quit();
                return;
            case 4:
                GiftAnimationManager.e(this.a.a);
                GiftAnimationManager.f(this.a.a);
                return;
            case 5:
                this.a.a.e = this.a.a.e + 1;
                GiftAnimationManager.h(this.a.a);
                GiftAnimationManager.i(this.a.a);
                return;
            case 6:
                this.a.a.a();
                return;
            default:
                return;
        }
    }
}
