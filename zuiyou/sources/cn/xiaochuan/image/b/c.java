package cn.xiaochuan.image.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

public interface c {
    void a(Context context, int i, int i2, ImageView imageView, Uri uri);

    void a(Context context, int i, Drawable drawable, ImageView imageView, Uri uri);

    void b(Context context, int i, Drawable drawable, ImageView imageView, Uri uri);
}
