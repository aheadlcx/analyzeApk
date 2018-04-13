package com.edmodo.cropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.j;
import com.edmodo.cropper.a.c;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.edge.Edge;

public class CropImageView extends FrameLayout {
    private static final Rect a = new Rect();
    private ImageView b;
    private CropOverlayView c;
    private Bitmap d;
    private int e = 0;
    private int f;
    private int g;
    private int h = 1;
    private boolean i = false;
    private int j = 1;
    private int k = 1;
    private int l = 0;

    public CropImageView(Context context) {
        super(context);
        a(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.CropImageView, 0, 0);
        try {
            this.h = obtainStyledAttributes.getInteger(j.CropImageView_guidelines, 1);
            this.i = obtainStyledAttributes.getBoolean(j.CropImageView_fixAspectRatio, false);
            this.j = obtainStyledAttributes.getInteger(j.CropImageView_aspectRatioX, 1);
            this.k = obtainStyledAttributes.getInteger(j.CropImageView_aspectRatioY, 1);
            this.l = obtainStyledAttributes.getResourceId(j.CropImageView_imageResource, 0);
            a(context);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("DEGREES_ROTATED", this.e);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            if (this.d != null) {
                this.e = bundle.getInt("DEGREES_ROTATED");
                int i = this.e;
                a(this.e);
                this.e = i;
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.d != null) {
            this.c.setBitmapRect(c.a(this.d, this));
            return;
        }
        this.c.setBitmapRect(a);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (this.d != null) {
            int width;
            int height;
            super.onMeasure(i, i2);
            if (size2 == 0) {
                size2 = this.d.getHeight();
            }
            double d = Double.POSITIVE_INFINITY;
            double d2 = Double.POSITIVE_INFINITY;
            if (size < this.d.getWidth()) {
                d = ((double) size) / ((double) this.d.getWidth());
            }
            if (size2 < this.d.getHeight()) {
                d2 = ((double) size2) / ((double) this.d.getHeight());
            }
            if (d == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY) {
                width = this.d.getWidth();
                height = this.d.getHeight();
            } else if (d <= d2) {
                height = (int) (d * ((double) this.d.getHeight()));
                width = size;
            } else {
                width = (int) (((double) this.d.getWidth()) * d2);
                height = size2;
            }
            width = a(mode, size, width);
            size2 = a(mode2, size2, height);
            this.f = width;
            this.g = size2;
            this.c.setBitmapRect(c.a(this.d.getWidth(), this.d.getHeight(), this.f, this.g));
            setMeasuredDimension(this.f, this.g);
            return;
        }
        this.c.setBitmapRect(a);
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f > 0 && this.g > 0) {
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.f;
            layoutParams.height = this.g;
            setLayoutParams(layoutParams);
        }
    }

    public int getImageResource() {
        return this.l;
    }

    public void a(Bitmap bitmap, boolean z) {
        this.d = bitmap;
        this.b.setImageBitmap(this.d);
        if (this.c != null) {
            this.c.a();
            this.c.b();
            this.c.setEnabled(z);
        }
    }

    public void setGuidelinesVisibilitytest(int i) {
        if (this.c != null) {
            this.c.setVisibility(i);
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            a(BitmapFactory.decodeResource(getResources(), i), true);
        }
    }

    public Bitmap getCroppedImage() {
        Rect a = c.a(this.d, this.b);
        float width = ((float) this.d.getWidth()) / ((float) a.width());
        float height = ((float) this.d.getHeight()) / ((float) a.height());
        float coordinate = Edge.LEFT.getCoordinate() - ((float) a.left);
        float coordinate2 = Edge.TOP.getCoordinate() - ((float) a.top);
        return Bitmap.createBitmap(this.d, (int) (coordinate * width), (int) (coordinate2 * height), (int) (width * Edge.getWidth()), (int) (height * Edge.getHeight()));
    }

    public RectF getActualCropRect() {
        Rect a = c.a(this.d, this.b);
        float width = ((float) this.d.getWidth()) / ((float) a.width());
        float height = ((float) this.d.getHeight()) / ((float) a.height());
        float coordinate = Edge.LEFT.getCoordinate() - ((float) a.left);
        float coordinate2 = Edge.TOP.getCoordinate() - ((float) a.top);
        coordinate *= width;
        coordinate2 *= height;
        return new RectF(Math.max(0.0f, coordinate), Math.max(0.0f, coordinate2), Math.min((float) this.d.getWidth(), (width * Edge.getWidth()) + coordinate), Math.min((float) this.d.getHeight(), (height * Edge.getHeight()) + coordinate2));
    }

    public void a(boolean z, boolean z2) {
        this.c.a(z, z2);
        if (this.c != null) {
            this.c.setEnabled(true);
        }
    }

    public void setGuidelines(int i) {
        this.c.setGuidelines(i);
    }

    public void a(int i, int i2) {
        this.j = i;
        this.c.setAspectRatioX(this.j);
        this.k = i2;
        this.c.setAspectRatioY(this.k);
        if (this.c != null) {
            this.c.setEnabled(true);
        }
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.a();
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        this.d = Bitmap.createBitmap(this.d, 0, 0, this.d.getWidth(), this.d.getHeight(), matrix, true);
        a(this.d, false);
        this.e += i;
        this.e %= 360;
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(e.crop_image_view, this, true);
        this.b = (ImageView) inflate.findViewById(d.ImageView_image);
        setImageResource(this.l);
        this.c = (CropOverlayView) inflate.findViewById(d.CropOverlayView);
        this.c.a(this.h, this.i, this.j, this.k);
    }

    private static int a(int i, int i2, int i3) {
        if (i == 1073741824) {
            return i2;
        }
        return i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }
}
