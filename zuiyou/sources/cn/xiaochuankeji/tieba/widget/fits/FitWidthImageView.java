package cn.xiaochuankeji.tieba.widget.fits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;
import c.a.i.i;

public class FitWidthImageView extends i {
    private int a;
    private int b;
    private int c;
    private int d;
    private float e;

    public FitWidthImageView(Context context) {
        this(context, null);
    }

    public FitWidthImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FitWidthImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setScaleType(ScaleType.MATRIX);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.a = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        this.b = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        this.b = (int) ((((float) this.a) / ((float) this.c)) * ((float) this.d));
        setMeasuredDimension(this.a, this.b);
        a(false);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a = i;
        this.b = i2;
    }

    public void setImageDrawable(Drawable drawable) {
        a();
        if (drawable != null) {
            this.c = drawable.getIntrinsicWidth();
            this.d = drawable.getIntrinsicHeight();
            super.setImageDrawable(drawable);
            b();
            return;
        }
        super.setImageDrawable(null);
    }

    public void setImageResource(int i) {
        a();
        super.setImageResource(i);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            this.c = drawable.getIntrinsicWidth();
            this.d = drawable.getIntrinsicHeight();
            b();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        a();
        if (bitmap != null) {
            this.c = bitmap.getWidth();
            this.d = bitmap.getHeight();
            super.setImageBitmap(bitmap);
            b();
            return;
        }
        super.setImageBitmap(null);
    }

    public float getScaleRate() {
        return this.e;
    }

    public int getIntrinsicWidth() {
        return this.c;
    }

    public int getIntrinsicHeight() {
        return this.d;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    private void a() {
        if (getDrawingCache() != null) {
            Bitmap drawingCache = getDrawingCache();
            setImageBitmap(null);
            drawingCache.recycle();
        }
    }

    private void b() {
        a(true);
    }

    private void a(boolean z) {
        Matrix matrix = new Matrix();
        float f = ((float) this.a) / ((float) this.c);
        this.e = f;
        matrix.postScale(f, f);
        setImageMatrix(matrix);
        if (z) {
            requestLayout();
        }
    }
}
