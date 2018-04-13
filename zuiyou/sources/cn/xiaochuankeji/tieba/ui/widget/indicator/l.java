package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import java.util.Arrays;
import java.util.List;

public class l extends View implements h {
    private int a;
    private Interpolator b = new LinearInterpolator();
    private Interpolator c = new LinearInterpolator();
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i;
    private List<n> j;
    private List<Integer> k;
    private RectF l = new RectF();
    private Bitmap m;

    public l(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.i = new Paint(1);
        this.i.setStyle(Style.FILL);
        this.e = i.a(getResources(), 3.0f);
        this.g = i.a(getResources(), 10.0f);
        this.m = ((BitmapDrawable) a.a().b(R.drawable.navbar_select)).getBitmap();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.m, null, this.l, this.i);
    }

    public void a(int i, float f, int i2) {
        if (this.j != null && !this.j.isEmpty()) {
            float f2;
            float f3;
            float f4;
            float f5;
            if (!(this.k == null || this.k.isEmpty())) {
                this.i.setColor(i.a(f, ((Integer) this.k.get(Math.abs(i) % this.k.size())).intValue(), ((Integer) this.k.get(Math.abs(i + 1) % this.k.size())).intValue()));
            }
            n a = d.a(this.j, i);
            n a2 = d.a(this.j, i + 1);
            if (this.a == 0) {
                f2 = ((float) a.a) + this.f;
                f3 = this.f + ((float) a2.a);
                f4 = ((float) a.c) - this.f;
                f5 = ((float) a2.c) - this.f;
            } else if (this.a == 1) {
                f2 = ((float) a.e) + this.f;
                f3 = this.f + ((float) a2.e);
                f4 = ((float) a.g) - this.f;
                f5 = ((float) a2.g) - this.f;
            } else {
                f2 = ((float) a.a) + ((((float) a.a()) - this.g) / 2.0f);
                f3 = ((((float) a2.a()) - this.g) / 2.0f) + ((float) a2.a);
                f4 = ((float) a.a) + ((((float) a.a()) + this.g) / 2.0f);
                f5 = ((float) a2.a) + ((((float) a2.a()) + this.g) / 2.0f);
            }
            this.l.left = ((f3 - f2) * this.b.getInterpolation(f)) + f2;
            this.l.right = ((f5 - f4) * this.c.getInterpolation(f)) + f4;
            this.l.top = (((float) getHeight()) - this.e) - this.d;
            this.l.bottom = ((float) getHeight()) - this.d;
            invalidate();
        }
    }

    public void a(int i) {
    }

    public void b(int i) {
    }

    public void a(List<n> list) {
        this.j = list;
    }

    public float getYOffset() {
        return this.d;
    }

    public void setYOffset(float f) {
        this.d = f;
    }

    public float getXOffset() {
        return this.f;
    }

    public void setXOffset(float f) {
        this.f = f;
    }

    public float getLineHeight() {
        return this.e;
    }

    public void setLineHeight(float f) {
        this.e = f;
    }

    public float getLineWidth() {
        return this.g;
    }

    public void setLineWidth(float f) {
        this.g = f;
    }

    public float getRoundRadius() {
        return this.h;
    }

    public void setRoundRadius(float f) {
        this.h = f;
    }

    public int getMode() {
        return this.a;
    }

    public void setMode(int i) {
        if (i == 2 || i == 0 || i == 1) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("mode " + i + " not supported.");
    }

    public Paint getPaint() {
        return this.i;
    }

    public List<Integer> getColors() {
        return this.k;
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
    }

    public Interpolator getStartInterpolator() {
        return this.b;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.b = interpolator;
        if (this.b == null) {
            this.b = new LinearInterpolator();
        }
    }

    public Interpolator getEndInterpolator() {
        return this.c;
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.c = interpolator;
        if (this.c == null) {
            this.c = new LinearInterpolator();
        }
    }
}
