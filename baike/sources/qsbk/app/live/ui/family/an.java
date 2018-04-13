package qsbk.app.live.ui.family;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import qsbk.app.core.utils.blur.Blur;

class an extends BaseBitmapDataSubscriber {
    final /* synthetic */ FamilyDetailActivity a;

    an(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void onNewResultImpl(@Nullable Bitmap bitmap) {
        this.a.mHandler.post(new ao(this, Blur.fastblur(this.a, bitmap, 10)));
    }

    public void onFailureImpl(DataSource dataSource) {
    }
}
