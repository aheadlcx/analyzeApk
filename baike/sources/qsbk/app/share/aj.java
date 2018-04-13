package qsbk.app.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import qsbk.app.R;

class aj extends BaseBitmapDataSubscriber {
    final /* synthetic */ ImageObject a;
    final /* synthetic */ WebpageObject b;
    final /* synthetic */ WeiboMultiMessage c;
    final /* synthetic */ WeiboShareActivity d;

    aj(WeiboShareActivity weiboShareActivity, ImageObject imageObject, WebpageObject webpageObject, WeiboMultiMessage weiboMultiMessage) {
        this.d = weiboShareActivity;
        this.a = imageObject;
        this.b = webpageObject;
        this.c = weiboMultiMessage;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        a(bitmap);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        a(BitmapFactory.decodeResource(this.d.getResources(), R.drawable.share_default_icon));
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            this.a.setImageObject(Bitmap.createBitmap(bitmap));
            this.b.setThumbImage(bitmap);
        }
        this.d.b.shareMessage(this.c, false);
    }
}
