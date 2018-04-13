package cn.v6.sixrooms.room.view;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;

final class b extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    final /* synthetic */ String a;
    final /* synthetic */ DraweeSpan b;

    b(DraweeSpan draweeSpan, String str) {
        this.b = draweeSpan;
        this.a = str;
    }

    protected final void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        boolean isFinished = dataSource.isFinished();
        CloseableReference closeableReference = (CloseableReference) dataSource.getResult();
        if (closeableReference != null) {
            DraweeSpan.a(this.b, this.a, dataSource, closeableReference, isFinished);
        } else if (isFinished) {
            this.b.a(this.a, (DataSource) dataSource, new NullPointerException(), true);
        }
    }

    protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        this.b.a(this.a, (DataSource) dataSource, dataSource.getFailureCause(), true);
    }
}
