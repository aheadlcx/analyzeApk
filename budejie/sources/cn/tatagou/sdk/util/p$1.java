package cn.tatagou.sdk.util;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import cn.tatagou.sdk.pojo.Special;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.b.g;

class p$1 extends g<Bitmap> {
    final /* synthetic */ ImageView a;
    final /* synthetic */ Special b;
    final /* synthetic */ int c;

    p$1(ImageView imageView, Special special, int i) {
        this.a = imageView;
        this.b = special;
        this.c = i;
    }

    public void onResourceReady(Bitmap bitmap, c<? super Bitmap> cVar) {
        if (bitmap != null && this.a != null) {
            this.a.setImageBitmap(bitmap);
            double width = ((double) bitmap.getWidth()) / ((double) bitmap.getHeight());
            this.b.setCoverImgScale(width);
            if (this.c != -1) {
                LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
                layoutParams.width = this.c;
                layoutParams.height = (int) (((double) this.c) / width);
                this.a.setLayoutParams(layoutParams);
            }
        }
    }
}
