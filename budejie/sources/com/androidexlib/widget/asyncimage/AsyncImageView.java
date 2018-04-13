package com.androidexlib.widget.asyncimage;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.bdj.picture.edit.util.h;
import com.nostra13.universalimageloader.core.c.b;
import com.nostra13.universalimageloader.core.d.c;
import com.nostra13.universalimageloader.core.d.d;
import java.io.File;

public class AsyncImageView extends ImageView {
    public static int a = 37748736;
    public static int b = 104857600;
    public static File c = new File(Environment.getExternalStorageDirectory(), "budejie" + File.separator + "ImageCache");
    private c d;
    private d e;
    private AsyncImageView$a f;

    public AsyncImageView(Context context) {
        super(context);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAsyncCacheImage(String str) {
        if (this.d == null || this.e == null) {
            Log.d("AsyncImageView", "displayImage()");
            com.nostra13.universalimageloader.core.d.a().a(str, new b(this, false), h.a());
            return;
        }
        Log.d("AsyncImageView", "displayImage(listener)");
        com.nostra13.universalimageloader.core.d.a().a(str, new b(this, false), h.a(), this.d, this.e);
    }

    public void setImageListener(AsyncImageView$a asyncImageView$a) {
        setImageListenerSpare(asyncImageView$a);
    }

    public void setImageListenerSpare(AsyncImageView$a asyncImageView$a) {
        this.f = asyncImageView$a;
        if (this.f != null) {
            this.d = new AsyncImageView$1(this);
            this.e = new AsyncImageView$2(this);
        }
    }
}
