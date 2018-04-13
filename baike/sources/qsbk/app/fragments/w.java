package qsbk.app.fragments;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import qsbk.app.model.ImageSize;

class w extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ BrowseGIFVideoFragment c;

    w(BrowseGIFVideoFragment browseGIFVideoFragment, int i, int i2) {
        this.c = browseGIFVideoFragment;
        this.a = i;
        this.b = i2;
    }

    public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(str, imageInfo, animatable);
        if (this.c.getActivity() != null && !this.c.getActivity().isFinishing() && imageInfo != null) {
            int width = imageInfo.getWidth();
            int height = imageInfo.getHeight();
            if (width != 0 && height != 0 && this.c.e.width == 0 && this.c.e.height == 0) {
                ImageSize imageSize = new ImageSize(width, height);
                this.c.setImageLayoutParams(this.c.imageView, imageSize, this.a, this.b);
                this.c.setImageLayoutParams(this.c.videoPlayer, imageSize, this.a, this.b);
                this.c.videoPlayer.setAspectRatio(width, height);
                this.c.videoPlayer.setVisibility(0);
                this.c.a();
            }
        }
    }
}
