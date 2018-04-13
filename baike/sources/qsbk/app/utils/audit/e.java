package qsbk.app.utils.audit;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.ImageSize;

class e implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ int[] e;
    final /* synthetic */ Drawable f;
    final /* synthetic */ ProgressBar g;
    final /* synthetic */ SimpleImageLoader h;

    e(SimpleImageLoader simpleImageLoader, byte[] bArr, int i, int i2, ImageView imageView, int[] iArr, Drawable drawable, ProgressBar progressBar) {
        this.h = simpleImageLoader;
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = imageView;
        this.e = iArr;
        this.f = drawable;
        this.g = progressBar;
    }

    public void run() {
        Bitmap decodeBitmap = BitmapUtil.decodeBitmap(this.a, this.b, this.c, false);
        if (decodeBitmap != null) {
            int i;
            LayoutParams layoutParams = this.d.getLayoutParams();
            if (this.e != null) {
                ImageSizeHelper.calWidthAndHeight(this.b, this.c, this.e, new ImageSize(this.e[0], this.e[1]));
                i = this.e[1];
            } else {
                i = BitmapUtil.calDesiredHeight(this.a, this.b, this.c);
            }
            if (layoutParams == null) {
                layoutParams = new LayoutParams(this.b, i);
            } else {
                layoutParams.width = this.b;
                layoutParams.height = i;
            }
            this.d.setLayoutParams(layoutParams);
            this.d.setImageBitmap(decodeBitmap);
        } else {
            this.d.setImageDrawable(this.f);
        }
        if (this.g != null) {
            this.g.setProgress(this.g.getMax());
            this.g.setVisibility(4);
        }
    }
}
