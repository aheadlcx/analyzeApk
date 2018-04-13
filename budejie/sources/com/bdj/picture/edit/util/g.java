package com.bdj.picture.edit.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.b.c;
import com.nostra13.universalimageloader.core.d.b;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import pl.droidsonroids.gif.GifDrawable;

public class g {
    private static com.nostra13.universalimageloader.core.d.a a;

    private static class a extends b {
        static final List<String> a = Collections.synchronizedList(new LinkedList());

        private a() {
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            ImageView imageView = (ImageView) view;
            if (bitmap != null || gifDrawable != null) {
                if ((!a.contains(str) ? 1 : null) != null) {
                    c.a(imageView, 100);
                    a.add(str);
                }
            }
        }
    }

    public static com.nostra13.universalimageloader.core.c a(com.nostra13.universalimageloader.core.b.a aVar) {
        return new com.nostra13.universalimageloader.core.c.a().a(true).b(true).c(true).a(Config.RGB_565).a(aVar).a();
    }

    public static com.nostra13.universalimageloader.core.d.a a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }
}
