package qsbk.app.im;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.utils.UIHelper;

class hz extends BaseBitmapDataSubscriber {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ PendingIntent c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ IMNotifyManager g;

    hz(IMNotifyManager iMNotifyManager, Context context, int i, PendingIntent pendingIntent, String str, String str2, String str3) {
        this.g = iMNotifyManager;
        this.a = context;
        this.b = i;
        this.c = pendingIntent;
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        a(bitmap);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        a(BitmapFactory.decodeResource(this.a.getResources(), UIHelper.getDefaultAvatar()));
    }

    private void a(Bitmap bitmap) {
        this.g.b(this.a, this.c, this.d, this.e, this.g.a(bitmap, this.b), this.f);
    }
}
