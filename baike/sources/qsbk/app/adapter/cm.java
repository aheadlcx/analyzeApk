package qsbk.app.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.QsbkApp;
import qsbk.app.utils.UIHelper;

final class cm extends BaseBitmapDataSubscriber {
    final /* synthetic */ TextView a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;

    cm(TextView textView, String str, int i, int i2, int i3, int i4) {
        this.a = textView;
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        int dip2px = UIHelper.dip2px(QsbkApp.mContext, 14.0f);
        Drawable bitmapDrawable = new BitmapDrawable(QsbkApp.mContext.getResources(), Bitmap.createScaledBitmap(bitmap, (int) ((((double) bitmap.getWidth()) / ((double) bitmap.getHeight())) * ((double) dip2px)), dip2px, true));
        Object tag = this.a.getTag();
        if (tag != null && tag.equals(this.b)) {
            this.a.setCompoundDrawablesWithIntrinsicBounds(bitmapDrawable, null, null, null);
            this.a.setPadding(this.c, this.d, this.e, this.f);
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        this.a.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.a.setPadding(this.c, this.d, this.e, this.f);
    }
}
