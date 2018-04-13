package cn.v6.sixrooms.animation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import cn.v6.sixrooms.animation.GiftAnimationManager.CallBackGiftBitmap;

final class a implements CallBackGiftBitmap {
    final /* synthetic */ GiftAnimationManager a;

    a(GiftAnimationManager giftAnimationManager) {
        this.a = giftAnimationManager;
    }

    public final void onBitmapGet(int i, String str, Bitmap bitmap) {
        try {
            if (this.a.d == null) {
                bitmap.recycle();
                return;
            }
            Message message = new Message();
            message.obj = bitmap;
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            message.setData(bundle);
            message.what = 1;
            message.arg1 = i;
            this.a.c.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap.recycle();
        }
    }
}
