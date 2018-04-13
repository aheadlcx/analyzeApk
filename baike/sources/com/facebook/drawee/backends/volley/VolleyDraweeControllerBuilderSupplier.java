package com.facebook.drawee.backends.volley;

import android.content.Context;
import com.android.volley.toolbox.ImageLoader;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import java.util.Set;

public class VolleyDraweeControllerBuilderSupplier implements Supplier<VolleyDraweeControllerBuilder> {
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Context mContext;
    private final ImageLoader mImageLoader;
    private final VolleyDraweeControllerFactory mVolleyDraweeControllerFactory;

    public VolleyDraweeControllerBuilderSupplier(Context context, ImageLoader imageLoader) {
        this(context, imageLoader, null);
    }

    public VolleyDraweeControllerBuilderSupplier(Context context, ImageLoader imageLoader, Set<ControllerListener> set) {
        this.mContext = context;
        this.mImageLoader = imageLoader;
        this.mVolleyDraweeControllerFactory = new VolleyDraweeControllerFactory(context.getResources(), DeferredReleaser.getInstance(), UiThreadImmediateExecutorService.getInstance());
        this.mBoundControllerListeners = set;
    }

    public VolleyDraweeControllerBuilder get() {
        return new VolleyDraweeControllerBuilder(this.mContext, this.mImageLoader, this.mVolleyDraweeControllerFactory, this.mBoundControllerListeners);
    }
}
