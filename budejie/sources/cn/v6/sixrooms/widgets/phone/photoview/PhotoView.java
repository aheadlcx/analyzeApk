package cn.v6.sixrooms.widgets.phone.photoview;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.v6.sixrooms.widgets.phone.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import cn.v6.sixrooms.widgets.phone.photoview.PhotoViewAttacher.OnPhotoTapListener;
import cn.v6.sixrooms.widgets.phone.photoview.PhotoViewAttacher.OnViewTapListener;

public class PhotoView extends ImageView implements IPhotoView {
    private final PhotoViewAttacher a;
    private ScaleType b;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ScaleType.MATRIX);
        this.a = new PhotoViewAttacher(this);
        if (this.b != null) {
            setScaleType(this.b);
            this.b = null;
        }
    }

    public boolean canZoom() {
        return this.a.canZoom();
    }

    public RectF getDisplayRect() {
        return this.a.getDisplayRect();
    }

    public float getMinScale() {
        return this.a.getMinScale();
    }

    public float getMidScale() {
        return this.a.getMidScale();
    }

    public float getMaxScale() {
        return this.a.getMaxScale();
    }

    public float getScale() {
        return this.a.getScale();
    }

    public ScaleType getScaleType() {
        return this.a.getScaleType();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.a.setAllowParentInterceptOnEdge(z);
    }

    public void setMinScale(float f) {
        this.a.setMinScale(f);
    }

    public void setMidScale(float f) {
        this.a.setMidScale(f);
    }

    public void setMaxScale(float f) {
        this.a.setMaxScale(f);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.a != null) {
            this.a.update();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.a != null) {
            this.a.update();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.a != null) {
            this.a.update();
        }
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.a.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.a.setOnLongClickListener(onLongClickListener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.a.setOnPhotoTapListener(onPhotoTapListener);
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.a.setOnViewTapListener(onViewTapListener);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.a != null) {
            this.a.setScaleType(scaleType);
        } else {
            this.b = scaleType;
        }
    }

    public void setZoomable(boolean z) {
        this.a.setZoomable(z);
    }

    public void zoomTo(float f, float f2, float f3) {
        this.a.zoomTo(f, f2, f3);
    }

    protected void onDetachedFromWindow() {
        this.a.cleanup();
        super.onDetachedFromWindow();
    }
}
