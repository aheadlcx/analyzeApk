package cn.v6.sixrooms.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.v6.sixrooms.utils.SmilyEncUtils.ImageLoadingListener;
import java.lang.ref.SoftReference;

final class aa implements Runnable {
    Bitmap a = null;
    final /* synthetic */ String b;
    final /* synthetic */ float c;
    final /* synthetic */ ImageLoadingListener d;
    final /* synthetic */ ImageView e;
    final /* synthetic */ SmilyEncUtils f;

    aa(SmilyEncUtils smilyEncUtils, String str, float f, ImageLoadingListener imageLoadingListener, ImageView imageView) {
        this.f = smilyEncUtils;
        this.b = str;
        this.c = f;
        this.d = imageLoadingListener;
        this.e = imageView;
    }

    public final void run() {
        if (this.f.c.containsKey(this.b)) {
            this.a = (Bitmap) ((SoftReference) this.f.c.get(this.b)).get();
        }
        if (this.a == null) {
            this.a = this.f.loadBitmap(this.b, this.c);
        }
        this.f.f.post(new ab(this));
    }
}
