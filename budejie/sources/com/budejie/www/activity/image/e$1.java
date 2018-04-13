package com.budejie.www.activity.image;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import com.budejie.www.activity.image.BitmapCache.a;

class e$1 implements a {
    final /* synthetic */ e a;

    e$1(e eVar) {
        this.a = eVar;
    }

    public void a(ImageView imageView, Bitmap bitmap, Object... objArr) {
        if (imageView == null || bitmap == null) {
            Log.e(this.a.a, "callback, bmp null");
            return;
        }
        String str = (String) objArr[0];
        if (str == null || !str.equals((String) imageView.getTag())) {
            Log.e(this.a.a, "callback, bmp not match");
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
