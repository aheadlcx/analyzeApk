package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.g;
import java.util.Arrays;

public class RoundedCornersDrawable extends g implements j {
    Type a = Type.OVERLAY_COLOR;
    final float[] c = new float[8];
    final Paint d = new Paint(1);
    private final float[] e = new float[8];
    private boolean f = false;
    private float g = 0.0f;
    private int h = 0;
    private int i = 0;
    private float j = 0.0f;
    private final Path k = new Path();
    private final Path l = new Path();
    private final RectF m = new RectF();

    public enum Type {
        OVERLAY_COLOR,
        CLIPPING
    }

    public RoundedCornersDrawable(Drawable drawable) {
        super((Drawable) g.a((Object) drawable));
    }

    public void a(boolean z) {
        this.f = z;
        b();
        invalidateSelf();
    }

    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.e, 0.0f);
        } else {
            boolean z;
            if (fArr.length == 8) {
                z = true;
            } else {
                z = false;
            }
            g.a(z, (Object) "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.e, 0, 8);
        }
        b();
        invalidateSelf();
    }

    public void a(int i) {
        this.i = i;
        invalidateSelf();
    }

    public void a(int i, float f) {
        this.h = i;
        this.g = f;
        b();
        invalidateSelf();
    }

    public void a(float f) {
        this.j = f;
        b();
        invalidateSelf();
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        b();
    }

    private void b() {
        this.k.reset();
        this.l.reset();
        this.m.set(getBounds());
        this.m.inset(this.j, this.j);
        if (this.f) {
            this.k.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Direction.CW);
        } else {
            this.k.addRoundRect(this.m, this.e, Direction.CW);
        }
        this.m.inset(-this.j, -this.j);
        this.m.inset(this.g / 2.0f, this.g / 2.0f);
        if (this.f) {
            this.l.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Direction.CW);
        } else {
            for (int i = 0; i < this.c.length; i++) {
                this.c[i] = (this.e[i] + this.j) - (this.g / 2.0f);
            }
            this.l.addRoundRect(this.m, this.c, Direction.CW);
        }
        this.m.inset((-this.g) / 2.0f, (-this.g) / 2.0f);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        switch (this.a) {
            case CLIPPING:
                int save = canvas.save();
                this.k.setFillType(FillType.EVEN_ODD);
                canvas.clipPath(this.k);
                super.draw(canvas);
                canvas.restoreToCount(save);
                break;
            case OVERLAY_COLOR:
                super.draw(canvas);
                this.d.setColor(this.i);
                this.d.setStyle(Style.FILL);
                this.k.setFillType(FillType.INVERSE_EVEN_ODD);
                canvas.drawPath(this.k, this.d);
                if (this.f) {
                    float width = (((float) (bounds.width() - bounds.height())) + this.g) / 2.0f;
                    float height = (((float) (bounds.height() - bounds.width())) + this.g) / 2.0f;
                    if (width > 0.0f) {
                        canvas.drawRect((float) bounds.left, (float) bounds.top, ((float) bounds.left) + width, (float) bounds.bottom, this.d);
                        canvas.drawRect(((float) bounds.right) - width, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.d);
                    }
                    if (height > 0.0f) {
                        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, ((float) bounds.top) + height, this.d);
                        canvas.drawRect((float) bounds.left, ((float) bounds.bottom) - height, (float) bounds.right, (float) bounds.bottom, this.d);
                        break;
                    }
                }
                break;
        }
        if (this.h != 0) {
            this.d.setStyle(Style.STROKE);
            this.d.setColor(this.h);
            this.d.setStrokeWidth(this.g);
            this.k.setFillType(FillType.EVEN_ODD);
            canvas.drawPath(this.l, this.d);
        }
    }
}
