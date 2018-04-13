package com.facebook.drawee.backends.volley;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class VolleyDraweeController extends AbstractDraweeController<Bitmap, Bitmap> {
    private Supplier<DataSource<Bitmap>> mDataSourceSupplier;
    private final Resources mResources;

    public VolleyDraweeController(Resources resources, DeferredReleaser deferredReleaser, Executor executor, Supplier<DataSource<Bitmap>> supplier, String str, Object obj) {
        super(deferredReleaser, executor, str, obj);
        this.mResources = resources;
        init(supplier);
    }

    public void initialize(Supplier<DataSource<Bitmap>> supplier, String str, Object obj) {
        super.initialize(str, obj);
        init(supplier);
    }

    private void init(Supplier<DataSource<Bitmap>> supplier) {
        this.mDataSourceSupplier = supplier;
    }

    protected Resources getResources() {
        return this.mResources;
    }

    protected DataSource<Bitmap> getDataSource() {
        return (DataSource) this.mDataSourceSupplier.get();
    }

    protected Drawable createDrawable(Bitmap bitmap) {
        return new BitmapDrawable(this.mResources, (Bitmap) Preconditions.checkNotNull(bitmap));
    }

    protected Bitmap getImageInfo(Bitmap bitmap) {
        return bitmap;
    }

    protected int getImageHash(@Nullable Bitmap bitmap) {
        return bitmap != null ? bitmap.hashCode() : 0;
    }

    protected void releaseImage(@Nullable Bitmap bitmap) {
    }

    protected void releaseDrawable(@Nullable Drawable drawable) {
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("super", super.toString()).add("dataSourceSupplier", this.mDataSourceSupplier).toString();
    }
}
