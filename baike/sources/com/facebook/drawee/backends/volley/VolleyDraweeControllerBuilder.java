package com.facebook.drawee.backends.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.android.volley.toolbox.ImageLoader;
import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder.CacheLevel;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import java.util.Set;

public class VolleyDraweeControllerBuilder extends AbstractDraweeControllerBuilder<VolleyDraweeControllerBuilder, Uri, Bitmap, Bitmap> {
    private final ImageLoader mImageLoader;
    private final VolleyDraweeControllerFactory mVolleyDraweeControllerFactory;

    public VolleyDraweeControllerBuilder(Context context, ImageLoader imageLoader, VolleyDraweeControllerFactory volleyDraweeControllerFactory, Set<ControllerListener> set) {
        super(context, set);
        this.mImageLoader = imageLoader;
        this.mVolleyDraweeControllerFactory = volleyDraweeControllerFactory;
    }

    protected VolleyDraweeController obtainController() {
        DraweeController oldController = getOldController();
        if (!(oldController instanceof VolleyDraweeController)) {
            return this.mVolleyDraweeControllerFactory.newController(obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), getCallerContext());
        }
        VolleyDraweeController volleyDraweeController = (VolleyDraweeController) oldController;
        volleyDraweeController.initialize(obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), getCallerContext());
        return volleyDraweeController;
    }

    protected DataSource<Bitmap> getDataSourceForRequest(Uri uri, Object obj, CacheLevel cacheLevel) {
        return new VolleyDataSource(this.mImageLoader, uri, cacheLevel);
    }

    public VolleyDraweeControllerBuilder setUri(Uri uri) {
        return (VolleyDraweeControllerBuilder) setImageRequest(uri);
    }

    public VolleyDraweeControllerBuilder setUri(String str) {
        Preconditions.checkNotNull(str);
        return (VolleyDraweeControllerBuilder) setImageRequest(Uri.parse(str));
    }
}
