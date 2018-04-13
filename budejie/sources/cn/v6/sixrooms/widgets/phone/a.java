package cn.v6.sixrooms.widgets.phone;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.v6.sixrooms.utils.SmilyEncUtils.ImageLoadingListener;

final class a implements ImageLoadingListener {
    final /* synthetic */ ExpressionGroup a;

    a(ExpressionGroup expressionGroup) {
        this.a = expressionGroup;
    }

    public final void onLoadingComplete(String str, Bitmap bitmap, ImageView imageView) {
        imageView.setImageBitmap(bitmap);
    }
}
