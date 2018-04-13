package cn.v6.sixrooms.room;

import android.graphics.Bitmap;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.utils.LogUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

final class ac extends BaseBitmapDataSubscriber {
    final /* synthetic */ RoomActivity a;

    ac(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    protected final void onNewResultImpl(Bitmap bitmap) {
        float height = ((float) (bitmap.getHeight() * bitmap.getWidth())) / 40000.0f;
        if (height <= 1.0f) {
            height = 1.0f;
        }
        this.a.runOnUiThread(new ad(this, bitmap, height));
    }

    protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        LogUtils.e(RoomActivity.TAG, "onFailureImpl");
        if (SixRoomsUtils.threadInMain()) {
            this.a.F.setVisibility(8);
        } else {
            VLScheduler.instance.schedule(0, 0, new ae(this));
        }
    }
}
