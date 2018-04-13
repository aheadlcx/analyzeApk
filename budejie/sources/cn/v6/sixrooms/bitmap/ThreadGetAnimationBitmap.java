package cn.v6.sixrooms.bitmap;

import cn.v6.sixrooms.animation.GiftAnimationManager.CallBackGiftBitmap;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;

public class ThreadGetAnimationBitmap extends Thread {
    int a;
    String b;
    CallBackGiftBitmap c;

    public ThreadGetAnimationBitmap(CallBackGiftBitmap callBackGiftBitmap, String str, int i) {
        this.c = callBackGiftBitmap;
        this.b = str;
        this.a = i;
    }

    public void run() {
        super.run();
        FrescoUtil.asyncGetBitmap(this.b, new a(this));
    }
}
