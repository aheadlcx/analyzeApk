package qsbk.app.core.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.core.model.TitleData;

class g extends BaseBitmapDataSubscriber {
    final /* synthetic */ TitleData a;
    final /* synthetic */ e b;

    g(e eVar, TitleData titleData) {
        this.b = eVar;
        this.a = titleData;
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.b.b.mTitleDrawables.put(this.a.tk, new BitmapDrawable(AppUtils.getInstance().getAppContext().getResources(), bitmap.copy(Config.ARGB_8888, false)));
    }
}
