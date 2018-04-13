package cn.v6.sixrooms.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.v6.sixrooms.utils.SmilyEncUtils.ImageLoadingListener;
import java.lang.ref.SoftReference;

final class y implements Runnable {
    Bitmap a = null;
    final /* synthetic */ String b;
    final /* synthetic */ ImageLoadingListener c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ SmilyEncUtils e;

    y(SmilyEncUtils smilyEncUtils, String str, ImageLoadingListener imageLoadingListener, ImageView imageView) {
        this.e = smilyEncUtils;
        this.b = str;
        this.c = imageLoadingListener;
        this.d = imageView;
    }

    public final void run() {
        if (this.e.c.containsKey(this.b)) {
            this.a = (Bitmap) ((SoftReference) this.e.c.get(this.b)).get();
            LogUtils.i(SmilyEncUtils.e, "get expression from cache");
        }
        if (this.a == null) {
            LogUtils.i(SmilyEncUtils.e, "get new expression from file");
            this.a = this.e.loadBitmap(this.b, 1.0f);
        }
        this.e.f.post(new z(this));
    }
}
