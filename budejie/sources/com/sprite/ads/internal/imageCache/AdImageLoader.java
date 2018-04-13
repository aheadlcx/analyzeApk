package com.sprite.ads.internal.imageCache;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.b.d;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.e;
import com.sprite.ads.internal.log.ADLog;

public class AdImageLoader {
    private static AdImageLoader loader = new AdImageLoader();

    public static AdImageLoader getInstance() {
        return loader;
    }

    public c SpritedisplayImageOptions() {
        return new a().a(ImageScaleType.EXACTLY).a(true).b(true).c(true).a(Config.RGB_565).a(new d()).a();
    }

    public void displayImage(String str, ImageView imageView) {
        com.nostra13.universalimageloader.core.d.a().a(str, imageView, displayImageOptions());
    }

    public void displayImage(String str, ImageView imageView, c cVar, AdImageLoadingListener adImageLoadingListener) {
        com.nostra13.universalimageloader.core.d.a().a(str, imageView, cVar, adImageLoadingListener);
    }

    public void displayImage(String str, ImageView imageView, AdImageLoadingListener adImageLoadingListener) {
        com.nostra13.universalimageloader.core.d.a().a(str, imageView, displayImageOptions(), adImageLoadingListener);
    }

    public void displayImage(String str, com.nostra13.universalimageloader.core.c.a aVar, c cVar, AdImageLoadingListener adImageLoadingListener) {
        com.nostra13.universalimageloader.core.d.a().a(str, aVar, cVar, adImageLoadingListener);
    }

    public c displayImageOptions() {
        return new a().a(true).b(true).c(true).a(Config.RGB_565).a(new d()).a();
    }

    public void init(Context context) {
        e c = new e.a(context).b(5).a(new AuthImageDownloader(context)).a(new com.nostra13.universalimageloader.a.a.b.c()).a(4).c();
        if (!com.nostra13.universalimageloader.core.d.a().b()) {
            ADLog.d("ad image load init");
            com.nostra13.universalimageloader.core.d.a().a(c);
        }
    }

    public void preCacheImage(String str) {
        com.nostra13.universalimageloader.core.d.a().a(str, displayImageOptions(), new AdImageLoadingListener());
    }
}
