package qsbk.app.widget;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.utils.BlurUtil;

class dd extends BaseBitmapDataSubscriber {
    final /* synthetic */ PersonalInfoView a;

    dd(PersonalInfoView personalInfoView) {
        this.a = personalInfoView;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        PersonalInfoView.e(this.a).setImageBitmap(BlurUtil.fastblur(bitmap, 3));
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
