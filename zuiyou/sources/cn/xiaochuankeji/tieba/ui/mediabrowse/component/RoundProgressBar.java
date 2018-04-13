package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import cn.xiaochuankeji.tieba.R;
import com.umeng.analytics.a;

public class RoundProgressBar extends View {
    private static int a = 15;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private float f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private int k;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 0;
        this.b = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundProgressBar);
        this.c = obtainStyledAttributes.getColor(0, 0);
        this.d = obtainStyledAttributes.getColor(1, -16711936);
        this.e = obtainStyledAttributes.getColor(3, -16711936);
        this.f = obtainStyledAttributes.getDimension(4, 15.0f);
        this.g = obtainStyledAttributes.getDimension(2, 5.0f);
        this.h = obtainStyledAttributes.getInteger(5, 100);
        this.j = obtainStyledAttributes.getBoolean(6, true);
        this.k = obtainStyledAttributes.getInt(7, 0);
        obtainStyledAttributes.recycle();
    }

    public void setRoundColor(@ColorInt int i) {
        this.c = i;
    }

    public void setRoundProgressColor(@ColorInt int i) {
        this.d = i;
    }

    public void setRoundWidth(float f) {
        this.g = f;
    }

    public void setTextIsDisplayable(boolean z) {
        this.j = z;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int i = (int) (((float) width) - (this.g / 2.0f));
        RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
        this.b.setColor(this.c);
        this.b.setStyle(Style.STROKE);
        this.b.setStrokeWidth(this.g);
        this.b.setAntiAlias(true);
        canvas.drawCircle((float) width, (float) width, (float) i, this.b);
        this.b.setStrokeWidth(0.0f);
        this.b.setColor(this.e);
        this.b.setTextSize(this.f);
        this.b.setTypeface(Typeface.DEFAULT_BOLD);
        i = (int) ((((float) this.i) / ((float) this.h)) * 100.0f);
        float measureText = this.b.measureText(i + "%");
        if (this.j && i >= 0 && this.k == 0) {
            canvas.drawText(i + "%", ((float) width) - (measureText / 2.0f), ((float) width) + (this.f / 2.0f), this.b);
        }
        this.b.setStrokeWidth(this.g);
        this.b.setColor(this.d);
        switch (this.k) {
            case 0:
                this.b.setStyle(Style.STROKE);
                canvas.drawArc(rectF, 0.0f, (float) ((this.i * a.p) / this.h), false, this.b);
                return;
            case 1:
                this.b.setStyle(Style.FILL_AND_STROKE);
                if (this.i != 0) {
                    canvas.drawArc(rectF, 0.0f, (float) ((this.i * a.p) / this.h), true, this.b);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public int getMax() {
        return this.h;
    }

    public void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.h = i;
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.h) {
            i = this.h;
        }
        if (i <= this.h) {
            this.i = i;
            postInvalidate();
        }
    }

    public int getProgress() {
        return this.i;
    }

    public int getTextColor() {
        return this.e;
    }

    public void setTextColor(int i) {
        this.e = i;
    }

    public float getTextSize() {
        return this.f;
    }

    public void setTextSize(float f) {
        this.f = f;
    }
}
