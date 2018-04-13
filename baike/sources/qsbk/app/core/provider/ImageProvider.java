package qsbk.app.core.provider;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

public abstract class ImageProvider {
    public abstract void loadAvatar(ImageView imageView, String str, boolean z);

    public abstract void loadGift(ImageView imageView, String str, boolean z);

    public abstract void loadImage(ImageView imageView, String str);

    public void loadAvatar(ImageView imageView, String str) {
        loadAvatar(imageView, str, true);
    }

    public void loadAvatar(ImageView imageView, String str, int i) {
    }

    public void loadGift(ImageView imageView, String str) {
        loadGift(imageView, str, true);
    }

    public void loadWebpImage(ImageView imageView, String str) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            ((SimpleDraweeView) imageView).setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setAutoPlayAnimations(true)).build());
        }
    }

    public void clearMemoryCaches() {
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    public void getCacheBitmap(Activity activity, String str, ImageProvider$BitmapCallback imageProvider$BitmapCallback) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str), activity.getApplicationContext()).subscribe(new a(this, imageProvider$BitmapCallback), CallerThreadExecutor.getInstance());
    }
}
