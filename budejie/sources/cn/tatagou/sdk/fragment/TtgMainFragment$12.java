package cn.tatagou.sdk.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.b.g;

class TtgMainFragment$12 extends g<Bitmap> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$12(TtgMainFragment ttgMainFragment, int i, int i2) {
        this.a = ttgMainFragment;
        super(i, i2);
    }

    public void onResourceReady(Bitmap bitmap, c cVar) {
        TtgMainFragment.access$800(this.a, bitmap);
    }

    public void onLoadFailed(Exception exception, Drawable drawable) {
        super.onLoadFailed(exception, drawable);
        this.a.hideLoading();
    }
}
