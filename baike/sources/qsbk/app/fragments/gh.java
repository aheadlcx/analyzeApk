package qsbk.app.fragments;

import android.graphics.Bitmap;
import android.view.ViewGroup.LayoutParams;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.image.FrescoImageloader;

class gh extends BaseBitmapDataSubscriber {
    final /* synthetic */ MyProfileFragment a;

    gh(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.a.m.setVisibility(0);
        LayoutParams layoutParams = this.a.m.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = (int) ((((float) bitmap.getWidth()) * this.a.m.getResources().getDisplayMetrics().density) / 3.0f);
            layoutParams.height = (int) ((((float) bitmap.getHeight()) * this.a.m.getResources().getDisplayMetrics().density) / 3.0f);
            this.a.m.requestLayout();
        }
        FrescoImageloader.displayImage(this.a.m, this.a.p);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
