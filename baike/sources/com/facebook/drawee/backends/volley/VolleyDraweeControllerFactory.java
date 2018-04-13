package com.facebook.drawee.backends.volley;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import java.util.concurrent.Executor;

public class VolleyDraweeControllerFactory {
    private DeferredReleaser mDeferredReleaser;
    private Resources mResources;
    private Executor mUiThreadExecutor;

    public VolleyDraweeControllerFactory(Resources resources, DeferredReleaser deferredReleaser, Executor executor) {
        this.mResources = resources;
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadExecutor = executor;
    }

    public VolleyDraweeController newController(Supplier<DataSource<Bitmap>> supplier, String str, Object obj) {
        return new VolleyDraweeController(this.mResources, this.mDeferredReleaser, this.mUiThreadExecutor, supplier, str, obj);
    }
}
