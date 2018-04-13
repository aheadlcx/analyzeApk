package qsbk.app;

import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.provider.ImageProvider;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;

class j extends ImageProvider {
    final /* synthetic */ QsbkApp a;

    j(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void loadAvatar(ImageView imageView, String str, boolean z) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            FrescoImageloader.displayAvatar(imageView, str, z);
        }
    }

    public void loadImage(ImageView imageView, String str) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            FrescoImageloader.displayImage(imageView, str);
        }
    }

    public void loadGift(ImageView imageView, String str, boolean z) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            FrescoImageloader.displayImage(imageView, str);
        }
    }

    public void loadAvatar(ImageView imageView, String str, int i) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            FrescoImageloader.displayAvatar(imageView, str, UIHelper.getDefaultAvatar(), false, i);
        }
    }
}
