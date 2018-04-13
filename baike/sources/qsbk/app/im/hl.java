package qsbk.app.im;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog$Lofter;

class hl extends BaseBitmapDataSubscriber {
    final /* synthetic */ SplashAdItem a;
    final /* synthetic */ IMMessageListFragment b;

    hl(IMMessageListFragment iMMessageListFragment, SplashAdItem splashAdItem) {
        this.b = iMMessageListFragment;
        this.a = splashAdItem;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.b.F.setVisibility(0);
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("activity_notification_anim_" + this.a.id)) {
            SharePreferenceUtils.setSharePreferencesValue("activity_notification_anim_" + this.a.id, true);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.b.F.getContext(), R.anim.activity_btn_scale);
            loadAnimation.setInterpolator(new ToastAndDialog$Lofter());
            this.b.F.startAnimation(loadAnimation);
        }
        FrescoImageloader.displayImage(this.b.F, this.a.picUrl);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
