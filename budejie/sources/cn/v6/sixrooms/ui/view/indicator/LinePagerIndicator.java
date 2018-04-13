package cn.v6.sixrooms.ui.view.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import cn.v6.sixrooms.utils.DensityUtil;
import java.util.Arrays;
import java.util.List;

public class LinePagerIndicator extends View implements IPagerIndicator {
    public static final int MODE_EXACTLY = 2;
    public static final int MODE_MATCH_EDGE = 0;
    public static final int MODE_WRAP_CONTENT = 1;
    private int a;
    private Interpolator b = new LinearInterpolator();
    private Interpolator c = new LinearInterpolator();
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i = new Paint(1);
    private List<PositionData> j;
    private List<Integer> k;
    private RectF l = new RectF();

    public LinePagerIndicator(Context context) {
        super(context);
        this.i.setStyle(Style.FILL);
        this.e = (float) DensityUtil.dip2px(3.0f);
        this.g = (float) DensityUtil.dip2px(10.0f);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(this.l, this.h, this.h, this.i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.j != null && !this.j.isEmpty()) {
            float f2;
            float f3;
            float f4;
            float f5;
            if (this.k != null && this.k.size() > 0) {
                this.i.setColor(ArgbEvaluatorHolder.eval(f, ((Integer) this.k.get(i % this.k.size())).intValue(), ((Integer) this.k.get((i + 1) % this.k.size())).intValue()));
            }
            int min = Math.min(this.j.size() - 1, i);
            PositionData positionData = (PositionData) this.j.get(min);
            PositionData positionData2 = (PositionData) this.j.get(Math.min(this.j.size() - 1, i + 1));
            float f6;
            float f7;
            if (this.a == 0) {
                f2 = this.f + ((float) positionData.mLeft);
                f6 = ((float) positionData2.mLeft) + this.f;
                f3 = ((float) positionData.mRight) - this.f;
                f4 = f6;
                f7 = f2;
                f2 = ((float) positionData2.mRight) - this.f;
                f5 = f7;
            } else if (this.a == 1) {
                f2 = this.f + ((float) positionData.mContentLeft);
                f6 = ((float) positionData2.mContentLeft) + this.f;
                f3 = ((float) positionData.mContentRight) - this.f;
                f4 = f6;
                f7 = f2;
                f2 = ((float) positionData2.mContentRight) - this.f;
                f5 = f7;
            } else {
                f2 = ((((float) positionData.width()) - this.g) / 2.0f) + ((float) positionData.mLeft);
                f4 = ((((float) positionData.width()) + this.g) / 2.0f) + ((float) positionData.mLeft);
                f6 = ((float) positionData2.mLeft) + ((((float) positionData2.width()) - this.g) / 2.0f);
                f3 = f4;
                f4 = f6;
                f7 = f2;
                f2 = ((((float) positionData2.width()) + this.g) / 2.0f) + ((float) positionData2.mLeft);
                f5 = f7;
            }
            this.l.left = ((f4 - f5) * this.b.getInterpolation(f)) + f5;
            this.l.right = ((f2 - f3) * this.c.getInterpolation(f)) + f3;
            this.l.top = (((float) getHeight()) - this.e) - this.d;
            this.l.bottom = ((float) getHeight()) - this.d;
            invalidate();
        }
    }

    public void onPageSelected(int i) {
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPositionDataProvide(List<PositionData> list) {
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
