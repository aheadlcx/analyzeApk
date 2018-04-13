package cn.v6.sixrooms.room.fragment;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

final class n extends BaseBitmapDataSubscriber {
    final /* synthetic */ FullScreenRoomFragment a;

    n(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    protected final void onNewResultImpl(Bitmap bitmap) {
    }

    protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
