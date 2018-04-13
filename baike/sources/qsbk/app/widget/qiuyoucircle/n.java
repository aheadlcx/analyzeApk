package qsbk.app.widget.qiuyoucircle;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.ParticipateAdapter.SubscribeIcon;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.VerticalImageSpan;

class n extends BaseBitmapDataSubscriber {
    final /* synthetic */ SubscribeIcon a;
    final /* synthetic */ BaseUserCell b;

    n(BaseUserCell baseUserCell, SubscribeIcon subscribeIcon) {
        this.b = baseUserCell;
        this.a = subscribeIcon;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        int dip2px = UIHelper.dip2px(QsbkApp.mContext, 22.0f);
        Drawable bitmapDrawable = new BitmapDrawable(QsbkApp.mContext.getResources(), Bitmap.createScaledBitmap(bitmap, dip2px, dip2px, false));
        CharSequence spannableStringBuilder = new SpannableStringBuilder(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.getText());
        VerticalImageSpan verticalImageSpan = new VerticalImageSpan(bitmapDrawable, UIHelper.dip2px(this.b.getContext(), 9.0f), UIHelper.dip2px(this.b.getContext(), 9.0f));
        verticalImageSpan.setMargin(UIHelper.dip2px(this.b.getContext(), 2.0f), UIHelper.dip2px(this.b.getContext(), 2.0f));
        spannableStringBuilder.setSpan(verticalImageSpan, 0, 1, 33);
        this.b.auditStatusView.setText(spannableStringBuilder);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
