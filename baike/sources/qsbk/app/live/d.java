package qsbk.app.live;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.provider.ImageProvider;
import qsbk.app.core.provider.ImageProvider$BitmapCallback;

class d extends ImageProvider {
    final /* synthetic */ QsbkLiveApp a;

    d(QsbkLiveApp qsbkLiveApp) {
        this.a = qsbkLiveApp;
    }

    public void loadAvatar(ImageView imageView, String str, boolean z) {
        Resources resources = this.a.getResources();
        Drawable colorDrawable = new ColorDrawable(resources.getColor(R.color.transparent));
        Drawable colorDrawable2 = new ColorDrawable(resources.getColor(R.color.yellow));
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(resources);
        genericDraweeHierarchyBuilder.setFadeDuration(300).setOverlay(colorDrawable).setPlaceholderImage(colorDrawable2).setActualImageScaleType(ScaleType.CENTER_CROP);
        if (z) {
            genericDraweeHierarchyBuilder.setRoundingParams(RoundingParams.asCircle());
        }
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
        simpleDraweeView.setHierarchy(genericDraweeHierarchyBuilder.build());
        if (!TextUtils.isEmpty(str)) {
            simpleDraweeView.setImageURI(Uri.parse(str));
        }
    }

    public void loadAvatar(ImageView imageView, String str, int i) {
        loadAvatar(imageView, str, true);
    }

    public void loadGift(ImageView imageView, String str, boolean z) {
        loadAvatar(imageView, str, false);
    }

    public void loadImage(ImageView imageView, String str) {
        ((SimpleDraweeView) imageView).setImageURI(Uri.parse(str));
    }

    public void loadWebpImage(ImageView imageView, String str) {
        ((SimpleDraweeView) imageView).setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setAutoPlayAnimations(true)).build());
    }

    public void clearMemoryCaches() {
    }

    public void getCacheBitmap(Activity activity, String str, ImageProvider$BitmapCallback imageProvider$BitmapCallback) {
    }
}
