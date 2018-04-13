package cn.xiaochuan.image.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import com.facebook.drawee.drawable.n$b;

@RequiresApi(api = 12)
public class a implements c {
    public void a(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        b.a(context).a(i, i).a(drawable).a(uri).a(imageView);
    }

    public void b(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        b.a(context).a(i, i).a(drawable).a(uri).a(imageView);
    }

    public void a(Context context, int i, int i2, ImageView imageView, Uri uri) {
        b.a(context).a(i, i2).a(uri).a(n$b.c).a(true).a(imageView);
    }
}
