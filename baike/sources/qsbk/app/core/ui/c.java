package qsbk.app.core.ui;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;

class c extends BaseBitmapDataSubscriber {
    final /* synthetic */ BrowseLargeImageActivity a;

    c(BrowseLargeImageActivity browseLargeImageActivity) {
        this.a = browseLargeImageActivity;
    }

    public void onNewResultImpl(@Nullable Bitmap bitmap) {
        this.a.e = bitmap;
    }

    public void onFailureImpl(DataSource dataSource) {
    }
}
