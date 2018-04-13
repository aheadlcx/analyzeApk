package com.facebook.drawee.backends.volley;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder.CacheLevel;

public class VolleyDataSource extends AbstractDataSource<Bitmap> {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ImageContainer mImageContainer;

    public VolleyDataSource(ImageLoader imageLoader, Uri uri, CacheLevel cacheLevel) {
        String uri2 = uri.toString();
        if (cacheLevel == CacheLevel.FULL_FETCH || imageLoader.isCached(uri2, 0, 0)) {
            this.mImageContainer = imageLoader.get(uri2, new ImageListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    VolleyDataSource.this.setFailure(volleyError.getCause());
                }

                public void onResponse(ImageContainer imageContainer, boolean z) {
                    if (imageContainer.getBitmap() != null) {
                        VolleyDataSource.this.setResult(imageContainer.getBitmap(), true);
                    }
                }
            }, 0, 0);
            return;
        }
        this.mImageContainer = null;
        setFailure(new NullPointerException("Image not found in bitmap-cache."));
    }

    public boolean close() {
        if (this.mImageContainer != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (VolleyDataSource.this.mImageContainer != null) {
                        VolleyDataSource.this.mImageContainer.cancelRequest();
                    }
                }
            });
        }
        return super.close();
    }
}
