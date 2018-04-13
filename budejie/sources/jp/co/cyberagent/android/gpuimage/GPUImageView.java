package jp.co.cyberagent.android.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import java.io.File;

public class GPUImageView extends FrameLayout {
    public b a = null;
    private GLSurfaceView b;
    private GPUImage c;
    private ab d;
    private float e = 0.0f;

    private class a extends GLSurfaceView {
        final /* synthetic */ GPUImageView a;

        public a(GPUImageView gPUImageView, Context context, AttributeSet attributeSet) {
            this.a = gPUImageView;
            super(context, attributeSet);
        }

        protected void onMeasure(int i, int i2) {
            if (this.a.a != null) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.a.a, 1073741824), MeasureSpec.makeMeasureSpec(this.a.a.b, 1073741824));
            } else {
                super.onMeasure(i, i2);
            }
        }
    }

    public static class b {
        int a;
        int b;
    }

    public GPUImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = new a(this, context, attributeSet);
        addView(this.b);
        this.c = new GPUImage(getContext());
        this.c.a(this.b);
    }

    protected void onMeasure(int i, int i2) {
        if (this.e != 0.0f) {
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            if (((float) size) / this.e < ((float) size2)) {
                size2 = Math.round(((float) size) / this.e);
            } else {
                size = Math.round(((float) size2) * this.e);
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
            return;
        }
        super.onMeasure(i, i2);
    }

    public GPUImage getGPUImage() {
        return this.c;
    }

    public void setRatio(float f) {
        this.e = f;
        this.b.requestLayout();
        this.c.b();
    }

    public void setScaleType(GPUImage$ScaleType gPUImage$ScaleType) {
        this.c.a(gPUImage$ScaleType);
    }

    public void setRotation(Rotation rotation) {
        this.c.a(rotation);
        a();
    }

    public void setFilter(ab abVar) {
        this.d = abVar;
        this.c.a(abVar);
        a();
    }

    public ab getFilter() {
        return this.d;
    }

    public void setImage(Bitmap bitmap) {
        this.c.a(bitmap);
    }

    public void setImage(Uri uri) {
        this.c.a(uri);
    }

    public void setImage(File file) {
        this.c.a(file);
    }

    public void a() {
        this.b.requestRender();
    }
}
