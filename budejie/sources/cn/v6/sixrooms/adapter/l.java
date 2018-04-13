package cn.v6.sixrooms.adapter;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

final class l extends BaseBitmapDataSubscriber {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ ViewerAdapter c;

    l(ViewerAdapter viewerAdapter, a aVar, String str) {
        this.c = viewerAdapter;
        this.a = aVar;
        this.b = str;
    }

    protected final void onNewResultImpl(Bitmap bitmap) {
        this.a.c.setImageBitmap(bitmap);
        this.c.a(this.a.c, this.b, bitmap);
    }

    protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
