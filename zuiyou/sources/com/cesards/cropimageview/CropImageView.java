package com.cesards.cropimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.cesards.cropimageview.f.a;
import java.util.HashMap;
import java.util.Map;

public class CropImageView extends ImageView {
    private c a;
    private CropType b;

    public enum CropType {
        NONE(-1),
        LEFT_TOP(0),
        LEFT_CENTER(1),
        LEFT_BOTTOM(2),
        RIGHT_TOP(3),
        RIGHT_CENTER(4),
        RIGHT_BOTTOM(5),
        CENTER_TOP(6),
        CENTER_BOTTOM(7);
        
        private static final Map<Integer, CropType> a = null;
        final int cropType;

        static {
            a = new HashMap();
            CropType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CropType cropType = values[i];
                a.put(Integer.valueOf(cropType.getCrop()), cropType);
                i++;
            }
        }

        private CropType(int i) {
            this.cropType = i;
        }

        public int getCrop() {
            return this.cropType;
        }

        public static CropType get(int i) {
            return (CropType) a.get(Integer.valueOf(i));
        }
    }

    public CropImageView(Context context) {
        super(context);
        this.b = CropType.NONE;
        a();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = CropType.NONE;
        a(attributeSet);
        a();
    }

    @TargetApi(21)
    public CropImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = CropType.NONE;
        a(attributeSet);
        a();
    }

    public void setCropType(CropType cropType) {
        if (cropType == null) {
            throw new NullPointerException();
        } else if (this.b != cropType) {
            this.b = cropType;
            setWillNotCacheDrawing(false);
            requestLayout();
            invalidate();
        }
    }

    public CropType getCropType() {
        return this.b;
    }

    private void a() {
        this.a = new d().a(this);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, a.CropImageView);
        int i = obtainStyledAttributes.getInt(a.CropImageView_crop, CropType.NONE.getCrop());
        if (i >= 0) {
            setScaleType(ScaleType.MATRIX);
            this.b = CropType.get(i);
        }
        obtainStyledAttributes.recycle();
    }

    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (!isInEditMode()) {
            b();
        }
        return frame;
    }

    private void b() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (this.b != CropType.NONE && height > 0 && width > 0) {
            Matrix a = this.a.a();
            int intrinsicWidth = getDrawable().getIntrinsicWidth();
            int intrinsicHeight = getDrawable().getIntrinsicHeight();
            float f = ((float) height) / ((float) intrinsicHeight);
            float f2 = ((float) width) / ((float) intrinsicWidth);
            float f3 = f2 > f ? f2 : f;
            a.setScale(f3, f3);
            boolean z = f2 > f;
            a.postTranslate(b(this.b, width, ((float) intrinsicWidth) * f3, z), a(this.b, height, f3 * ((float) intrinsicHeight), z));
            setImageMatrix(a);
        }
    }

    private float a(CropType cropType, int i, float f, boolean z) {
        if (z) {
            switch (cropType) {
                case CENTER_BOTTOM:
                case LEFT_BOTTOM:
                case RIGHT_BOTTOM:
                    return ((float) i) - f;
                case LEFT_CENTER:
                case RIGHT_CENTER:
                    return (((float) i) - f) / 2.0f;
            }
        }
        return 0.0f;
    }

    private float b(CropType cropType, int i, float f, boolean z) {
        if (!z) {
            switch (cropType) {
                case CENTER_BOTTOM:
                case CENTER_TOP:
                    return (((float) i) - f) / 2.0f;
                case RIGHT_BOTTOM:
                case RIGHT_CENTER:
                case RIGHT_TOP:
                    return ((float) i) - f;
            }
        }
        return 0.0f;
    }
}
