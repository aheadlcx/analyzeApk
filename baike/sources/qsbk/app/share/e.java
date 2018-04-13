package qsbk.app.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import qsbk.app.R;

class e extends BaseBitmapDataSubscriber {
    final /* synthetic */ WXMediaMessage a;
    final /* synthetic */ int b;
    final /* synthetic */ FakeWXEntryActivity c;

    e(FakeWXEntryActivity fakeWXEntryActivity, WXMediaMessage wXMediaMessage, int i) {
        this.c = fakeWXEntryActivity;
        this.a = wXMediaMessage;
        this.b = i;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        a(bitmap);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        a(BitmapFactory.decodeResource(this.c.getResources(), R.drawable.share_default_icon));
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            this.a.setThumbImage(bitmap);
        }
        BaseReq req = new Req();
        req.transaction = System.currentTimeMillis() + "";
        req.message = this.a;
        req.scene = this.b;
        this.c.a.sendReq(req);
        this.c.finish();
    }
}
