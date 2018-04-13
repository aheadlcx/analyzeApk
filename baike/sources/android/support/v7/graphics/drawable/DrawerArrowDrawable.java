package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float b = ((float) Math.toRadians(45.0d));
    private final Paint a = new Paint();
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private final Path h = new Path();
    private final int i;
    private boolean j = false;
    private float k;
    private float l;
    private int m = 2;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeJoin(Join.MITER);
        this.a.setStrokeCap(Cap.BUTT);
        this.a.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(obtainStyledAttributes.getColor(R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(obtainStyledAttributes.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize((float) Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.d = (float) Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.c = (float) Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.e = obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setArrowHeadLength(float f) {
        if (this.c != f) {
            this.c = f;
            invalidateSelf();
        }
    }

    public float getArrowHeadLength() {
        return this.c;
    }

    public void setArrowShaftLength(float f) {
        if (this.e != f) {
            this.e = f;
            invalidateSelf();
        }
    }

    public float getArrowShaftLength() {
        return this.e;
    }

    public float getBarLength() {
        return this.d;
    }

    public void setBarLength(float f) {
        if (this.d != f) {
            this.d = f;
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i) {
        if (i != this.a.getColor()) {
            this.a.setColor(i);
            invalidateSelf();
        }
    }

    @ColorInt
    public int getColor() {
        return this.a.getColor();
    }

    public void setBarThickness(float f) {
        if (this.a.getStrokeWidth() != f) {
            this.a.setStrokeWidth(f);
            this.l = (float) (((double) (f / 2.0f)) * Math.cos((double) b));
            invalidateSelf();
        }
    }

    public float getBarThickness() {
        return this.a.getStrokeWidth();
    }

    public float getGapSize() {
        return this.f;
    }

    public void setGapSize(float f) {
        if (f != this.f) {
            this.f = f;
            invalidateSelf();
        }
    }

    public void setDirection(int i) {
        if (i != this.m) {
            this.m = i;
            invalidateSelf();
        }
    }

    public boolean isSpinEnabled() {
        return this.g;
    }

    public void setSpinEnabled(boolean z) {
        if (this.g != z) {
            this.g = z;
            invalidateSelf();
        }
    }

    public int getDirection() {
        return this.m;
    }

    public void setVerticalMirror(boolean z) {
        if (this.j != z) {
            this.j = z;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        int i;
        float f;
        float f2;
        Rect bounds = getBounds();
        switch (this.m) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 1;
                break;
            case 3:
                if (DrawableCompat.getLayoutDirection(this) != 0) {
                    i = 0;
                    break;
                } else {
                    i = 1;
                    break;
                }
            default:
                if (DrawableCompat.getLayoutDirection(this) != 1) {
                    i = 0;
                    break;
                } else {
                    i = 1;
                    break;
                }
        }
        float a = a(this.d, (float) Math.sqrt((double) ((this.c * this.c) * 2.0f)), this.k);
        float a2 = a(this.d, this.e, this.k);
        float round = (float) Math.round(a(0.0f, this.l, this.k));
        float a3 = a(0.0f, b, this.k);
        if (i != 0) {
            f = 0.0f;
        } else {
            f = -180.0f;
        }
        if (i != 0) {
            f2 = 180.0f;
        } else {
            f2 = 0.0f;
        }
        f2 = a(f, f2, this.k);
        f = (float) Math.round(((double) a) * Math.cos((double) a3));
        a = (float) Math.round(((double) a) * Math.sin((double) a3));
        this.h.rewind();
        a3 = a(this.f + this.a.getStrokeWidth(), -this.l, this.k);
        float f3 = (-a2) / 2.0f;
        this.h.moveTo(f3 + round, 0.0f);
        this.h.rLineTo(a2 - (round * 2.0f), 0.0f);
        this.h.moveTo(f3, a3);
        this.h.rLineTo(f, a);
        this.h.moveTo(f3, -a3);
        this.h.rLineTo(f, -a);
        this.h.close();
        canvas.save();
        f = this.a.getStrokeWidth();
        canvas.translate((float) bounds.centerX(), ((f * 1.5f) + this.f) + ((float) ((((int) ((((float) bounds.height()) - (3.0f * f)) - (this.f * 2.0f))) / 4) * 2)));
        if (this.g) {
            canvas.rotate(((float) ((i ^ this.j) != 0 ? -1 : 1)) * f2);
        } else if (i != 0) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.h, this.a);
        canvas.restore();
    }

    public void setAlpha(int i) {
        if (i != this.a.getAlpha()) {
            this.a.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.i;
    }

    public int getIntrinsicWidth() {
        return this.i;
    }

    public int getOpacity() {
        return -3;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.k;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.k != f) {
            this.k = f;
            invalidateSelf();
        }
    }

    public final Paint getPaint() {
        return this.a;
    }

    private static float a(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }
}
