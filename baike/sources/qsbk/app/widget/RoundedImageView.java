package qsbk.app.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class RoundedImageView extends ImageView {
    public static final float DEFAULT_BORDER_WIDTH = 0.0f;
    public static final float DEFAULT_RADIUS = 0.0f;
    public static final String TAG = "RoundedImageView";
    static final /* synthetic */ boolean a = (!RoundedImageView.class.desiredAssertionStatus());
    private static final ScaleType[] b = new ScaleType[]{ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE};
    private static final ColorMatrixColorFilter c = new ColorMatrixColorFilter(new float[]{0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    private float d;
    private float e;
    private ColorStateList f;
    private boolean g;
    private boolean h;
    private int i;
    private Drawable j;
    private Drawable k;
    private ScaleType l;

    public RoundedImageView(Context context) {
        super(context);
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = ColorStateList.valueOf(-16777216);
        this.g = false;
        this.h = false;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = ColorStateList.valueOf(-16777216);
        this.g = false;
        this.h = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, -1);
        if (i2 >= 0) {
            setScaleType(b[i2]);
        } else {
            setScaleType(ScaleType.FIT_CENTER);
        }
        this.d = (float) obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.e = (float) obtainStyledAttributes.getDimensionPixelSize(2, -1);
        if (this.d < 0.0f) {
            this.d = 0.0f;
        }
        if (this.e < 0.0f) {
            this.e = 0.0f;
        }
        this.f = obtainStyledAttributes.getColorStateList(3);
        if (this.f == null) {
            this.f = ColorStateList.valueOf(-16777216);
        }
        this.h = obtainStyledAttributes.getBoolean(4, false);
        this.g = obtainStyledAttributes.getBoolean(5, false);
        b();
        a(true);
        obtainStyledAttributes.recycle();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public ScaleType getScaleType() {
        return this.l;
    }

    public void setScaleType(ScaleType scaleType) {
        if (!a && scaleType == null) {
            throw new AssertionError();
        } else if (this.l != scaleType) {
            this.l = scaleType;
            switch (eo.a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            b();
            a(false);
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        this.i = 0;
        this.j = RoundedDrawable.fromDrawable(drawable);
        b();
        super.setImageDrawable(this.j);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.i = 0;
        this.j = RoundedDrawable.fromBitmap(bitmap);
        b();
        if (UIHelper.isNightTheme() && this.j != null) {
            this.j.setColorFilter(c);
        }
        super.setImageDrawable(this.j);
    }

    public void setImageResource(int i) {
        if (this.i != i) {
            this.i = i;
            this.j = a();
            b();
            super.setImageDrawable(this.j);
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable a() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.i != 0) {
            try {
                drawable = resources.getDrawable(this.i);
            } catch (Throwable e) {
                Log.w(TAG, "Unable to find resource: " + this.i, e);
                this.i = 0;
            }
        }
        return RoundedDrawable.fromDrawable(drawable);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    private void b() {
        a(this.j);
    }

    private void a(boolean z) {
        if (this.h) {
            if (z) {
                this.k = RoundedDrawable.fromDrawable(this.k);
            }
            a(this.k);
        }
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                ((RoundedDrawable) drawable).setScaleType(this.l).setCornerRadius(this.d).setBorderWidth(this.e).setBorderColor(this.f).setOval(this.g);
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    a(layerDrawable.getDrawable(i));
                }
            }
        }
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.k = drawable;
        a(true);
        super.setBackgroundDrawable(this.k);
    }

    public float getCornerRadius() {
        return this.d;
    }

    public void setCornerRadius(float f) {
        if (this.d != f) {
            this.d = f;
            b();
            a(false);
        }
    }

    public void setCornerRadius(int i) {
        setCornerRadius(getResources().getDimension(i));
    }

    public float getBorderWidth() {
        return this.e;
    }

    public void setBorderWidth(float f) {
        if (this.e != f) {
            this.e = f;
            b();
            a(false);
            invalidate();
        }
    }

    public void setBorderWidth(int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public int getBorderColor() {
        return this.f.getDefaultColor();
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.f.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(-16777216);
            }
            this.f = colorStateList;
            b();
            a(false);
            if (this.e > 0.0f) {
                invalidate();
            }
        }
    }

    public void setBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.f;
    }

    public boolean isOval() {
        return this.g;
    }

    public void setOval(boolean z) {
        this.g = z;
        b();
        a(false);
        invalidate();
    }

    public boolean isMutateBackground() {
        return this.h;
    }

    public void setMutateBackground(boolean z) {
        if (this.h != z) {
            this.h = z;
            a(true);
            invalidate();
        }
    }
}
