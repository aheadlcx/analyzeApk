package cn.v6.sixrooms.room;

import android.graphics.Bitmap;
import cn.v6.sixrooms.utils.BlurUtils;

final class ad implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ float b;
    final /* synthetic */ ac c;

    ad(ac acVar, Bitmap bitmap, float f) {
        this.c = acVar;
        this.a = bitmap;
        this.b = f;
    }

    public final void run() {
        RoomActivity.a(this.c.a, BlurUtils.blurView(this.a, RoomActivity.H(this.c.a), 5.0f, this.b));
        RoomActivity.d(this.c.a).setVisibility(8);
    }
}
