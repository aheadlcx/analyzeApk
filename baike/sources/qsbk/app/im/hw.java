package qsbk.app.im;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.im.IMNotifyManager.NotificationBuilt;
import qsbk.app.utils.UIHelper;

class hw extends BaseBitmapDataSubscriber {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ NotificationBuilt c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ PendingIntent f;
    final /* synthetic */ String g;
    final /* synthetic */ IMNotifyManager h;

    hw(IMNotifyManager iMNotifyManager, Context context, int i, NotificationBuilt notificationBuilt, String str, String str2, PendingIntent pendingIntent, String str3) {
        this.h = iMNotifyManager;
        this.a = context;
        this.b = i;
        this.c = notificationBuilt;
        this.d = str;
        this.e = str2;
        this.f = pendingIntent;
        this.g = str3;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        a(bitmap);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        a(BitmapFactory.decodeResource(this.a.getResources(), UIHelper.getDefaultAvatar()));
    }

    private void a(Bitmap bitmap) {
        this.c.onBuilt(IMNotifyManager.a(this.a, this.d, this.e, this.f, this.h.a(bitmap, this.b), IMNotifyManager.isNewMsgSound(this.a), IMNotifyManager.isNewMsgVibrate(this.a), this.g));
    }
}
