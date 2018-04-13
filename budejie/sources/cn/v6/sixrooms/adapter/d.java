package cn.v6.sixrooms.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.v6.sixrooms.utils.SmilyEncUtils.ImageLoadingListener;

final class d implements ImageLoadingListener {
    final /* synthetic */ ExpressionItemAdapter a;

    d(ExpressionItemAdapter expressionItemAdapter) {
        this.a = expressionItemAdapter;
    }

    public final void onLoadingComplete(String str, Bitmap bitmap, ImageView imageView) {
        imageView.setImageBitmap(bitmap);
    }
}
