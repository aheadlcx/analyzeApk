package qsbk.app.push;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.utils.UIHelper;

class g extends BaseBitmapDataSubscriber {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ Intent c;
    final /* synthetic */ String d;
    final /* synthetic */ PushMessageProcessor e;

    g(PushMessageProcessor pushMessageProcessor, Context context, int i, Intent intent, String str) {
        this.e = pushMessageProcessor;
        this.a = context;
        this.b = i;
        this.c = intent;
        this.d = str;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        a(bitmap);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        a(BitmapFactory.decodeResource(this.a.getResources(), UIHelper.getDefaultAvatar()));
    }

    private void a(Bitmap bitmap) {
        PushMessageProcessor.notification(this.c, this.a, "我的新粉丝", this.d, 100, this.e.a(bitmap, this.b));
    }
}
