package qsbk.app.core.ui;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import qsbk.app.core.R;
import qsbk.app.core.utils.ToastUtil;

class b extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ BrowseLargeImageActivity a;

    b(BrowseLargeImageActivity browseLargeImageActivity) {
        this.a = browseLargeImageActivity;
    }

    public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(str, imageInfo, animatable);
        this.a.c.setVisibility(8);
    }

    public void onFailure(String str, Throwable th) {
        ToastUtil.Short(this.a.getString(R.string.browse_large_image_get_avatar_fail));
        this.a.c.setVisibility(8);
    }
}
