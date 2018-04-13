package qsbk.app.widget;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;
import android.widget.ImageView.ScaleType;

public class RoundedDrawable extends Drawable {
    public static final int DEFAULT_BORDER_COLOR = -16777216;
    public static final String TAG = "RoundedDrawable";
    private final RectF a = new RectF();
    private final RectF b = new RectF();
    private final RectF c = new RectF();
    private final BitmapShader d;
    private final Paint e;
    private final int f;
    private final int g;
    private final RectF h = new RectF();
    private final Paint i;
    private final Matrix j = new Matrix();
    private float k = 0.0f;
    private boolean l = true;
    private float m = 0.0f;
    private ColorStateList n = ColorStateList.valueOf(-16777216);
    private ScaleType o = ScaleType.FIT_CENTER;

    public RoundedDrawable(Bitmap bitmap) {
        this.f = bitmap.getWidth();
        this.g = bitmap.getHeight();
        this.c.set(0.0f, 0.0f, (float) this.f, (float) this.g);
        this.d = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.d.setLocalMatrix(this.j);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setAntiAlias(true);
        this.e.setShader(this.d);
        this.i = new Paint();
        this.i.setStyle(Style.STROKE);
        this.i.setAntiAlias(true);
        this.i.setColor(this.n.getColorForState(getState(), -16777216));
        this.i.setStrokeWidth(this.m);
    }

    public static RoundedDrawable fromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    public static Drawable fromDrawable(Drawable drawable) {
        if (drawable == null || (drawable instanceof RoundedDrawable)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                Drawable drawable2 = layerDrawable.getDrawable(i);
                if (layerDrawable.getId(i) <= 0) {
                    layerDrawable.setId(i, i + 1);
                }
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), fromDrawable(drawable2));
            }
            return layerDrawable;
        } else if (drawable instanceof NinePatchDrawable) {
            return drawable;
        } else {
            Bitmap drawableToBitmap = drawableToBitmap(drawable);
            if (drawableToBitmap != null) {
                return new RoundedDrawable(drawableToBitmap);
            }
            Log.w(TAG, "Failed to create bitmap from drawable!");
            return drawable;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isStateful() {
        return this.n.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        int colorForState = this.n.getColorForState(iArr, 0);
        if (this.i.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.i.setColor(colorForState);
        return true;
    }

    private void a() {
        float f = 0.0f;
        float height;
        float width;
        switch (en.a[this.o.ordinal()]) {
            case 1:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.set(null);
                this.j.setTranslate((float) ((int) (((this.h.width() - ((float) this.f)) * 0.5f) + 0.5f)), (float) ((int) (((this.h.height() - ((float) this.g)) * 0.5f) + 0.5f)));
                break;
            case 2:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.set(null);
                if (((float) this.f) * this.h.height() > this.h.width() * ((float) this.g)) {
                    height = this.h.height() / ((float) this.g);
                    width = (this.h.width() - (((float) this.f) * height)) * 0.5f;
                } else {
                    height = this.h.width() / ((float) this.f);
                    width = 0.0f;
                    f = (this.h.height() - (((float) this.g) * height)) * 0.5f;
                }
                this.j.setScale(height, height);
                this.j.postTranslate(((float) ((int) (width + 0.5f))) + this.m, ((float) ((int) (f + 0.5f))) + this.m);
                break;
            case 3:
                this.j.set(null);
                if (((float) this.f) > this.a.width() || ((float) this.g) > this.a.height()) {
                    f = Math.min(this.a.width() / ((float) this.f), this.a.height() / ((float) this.g));
                } else {
                    f = 1.0f;
                }
                width = (float) ((int) (((this.a.width() - (((float) this.f) * f)) * 0.5f) + 0.5f));
                height = (float) ((int) (((this.a.height() - (((float) this.g) * f)) * 0.5f) + 0.5f));
                this.j.setScale(f, f);
                this.j.postTranslate(width, height);
                this.h.set(this.c);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 5:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.END);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 6:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.START);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 7:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.set(null);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            default:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.CENTER);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
        }
        this.b.set(this.h);
        this.d.setLocalMatrix(this.j);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.a.set(rect);
        a();
    }

    public void draw(Canvas canvas) {
        if (this.l) {
            if (this.m > 0.0f) {
                canvas.drawOval(this.b, this.e);
                canvas.drawOval(this.h, this.i);
                return;
            }
            canvas.drawOval(this.b, this.e);
        } else if (this.m > 0.0f) {
            canvas.drawRoundRect(this.b, Math.max(this.k, 0.0f), Math.max(this.k, 0.0f), this.e);
            canvas.drawRoundRect(this.h, this.k, this.k, this.i);
        } else {
            canvas.drawRoundRect(this.b, this.k, this.k, this.e);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.e.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.e.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.e.setFilterBitmap(z);
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.f;
    }

    public int getIntrinsicHeight() {
        return this.g;
    }

    public float getCornerRadius() {
        return this.k;
    }

    public RoundedDrawable setCornerRadius(float f) {
        this.k = f;
        return this;
    }

    public float getBorderWidth() {
        return this.m;
    }

    public RoundedDrawable setBorderWidth(float f) {
        this.m = f;
        this.i.setStrokeWidth(this.m);
        return this;
    }

    public int getBorderColor() {
        return this.n.getDefaultColor();
    }

    public RoundedDrawable setBorderColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.n = colorStateList;
        this.i.setColor(this.n.getColorForState(getState(), -16777216));
        return this;
    }

    public RoundedDrawable setBorderColor(int i) {
        return setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.n;
    }

    public boolean isOval() {
        return this.l;
    }

    public RoundedDrawable setOval(boolean z) {
        this.l = z;
        return this;
    }

    public ScaleType getScaleType() {
        return this.o;
    }

    public RoundedDrawable setScaleType(ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ScaleType.FIT_CENTER;
        }
        if (this.o != scaleType) {
            this.o = scaleType;
            a();
        }
        return this;
    }

    public Bitmap toBitmap() {
        return drawableToBitmap(this);
    }
}
