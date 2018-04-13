package qsbk.app.core.utils;

import android.graphics.Bitmap;
import qsbk.app.core.ui.base.BaseActivity;

final class u implements Runnable {
    final /* synthetic */ BaseActivity a;
    final /* synthetic */ Bitmap b;

    u(BaseActivity baseActivity, Bitmap bitmap) {
        this.a = baseActivity;
        this.b = bitmap;
    }

    public void run() {
        ScreenShotUtils.saveBitmap(this.a, this.b);
    }
}
