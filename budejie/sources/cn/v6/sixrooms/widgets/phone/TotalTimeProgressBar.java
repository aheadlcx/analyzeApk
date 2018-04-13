package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.R$attr;
import com.budejie.www.R$styleable;

public class TotalTimeProgressBar extends View {
    private RectF A;
    private float B;
    private boolean C;
    private boolean D;
    private boolean E;
    private Context a;
    private int b;
    private long c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private float i;
    private final int j;
    private final int k;
    private final int l;
    private final float m;
    private final float n;
    private final float o;
    private final float p;
    private float q;
    private float r;
    private float s;
    private String t;
    private Paint u;
    private Paint v;
    private Paint w;
    private Paint x;
    private RectF y;
    private RectF z;

    public enum ProgressTextVisibility {
        Visible,
        Invisible
    }

    public TotalTimeProgressBar(Context context) {
        this(context, null);
    }

    public TotalTimeProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.numberProgressBarStyle);
    }

    public TotalTimeProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 600;
        this.c = 0;
        this.j = Color.rgb(66, R$styleable.Theme_Custom_label_pinner_tabs_bg, R$styleable.Theme_Custom_top_navigation_bar_bg_color);
        this.k = Color.rgb(66, R$styleable.Theme_Custom_label_pinner_tabs_bg, R$styleable.Theme_Custom_top_navigation_bar_bg_color);
        this.l = Color.rgb(204, 204, 204);
        this.y = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.z = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.A = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.C = true;
        this.D = true;
        this.E = true;
        this.a = context;
        this.o = dp2px(8.0f);
        this.p = dp2px(8.0f);
        this.n = sp2px(10.0f);
        this.m = dp2px(3.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, cn.v6.sixrooms.R$styleable.NumberProgressBar, i, 0);
        this.d = obtainStyledAttributes.getColor(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_reached_color, this.k);
        this.e = obtainStyledAttributes.getColor(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_unreached_color, this.l);
        this.f = obtainStyledAttributes.getColor(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_text_color, this.j);
        this.g = obtainStyledAttributes.getDimension(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_text_size, this.n);
        this.h = obtainStyledAttributes.getDimension(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_reached_bar_height, this.o);
        this.i = obtainStyledAttributes.getDimension(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_unreached_bar_height, this.p);
        this.B = obtainStyledAttributes.getDimension(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_text_offset, this.m);
        if (obtainStyledAttributes.getInt(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress_text_visibility, 0) != 0) {
            this.E = false;
        }
        setProgress((long) obtainStyledAttributes.getInt(cn.v6.sixrooms.R$styleable.NumberProgressBar_progress, 0));
        setMax(obtainStyledAttributes.getInt(cn.v6.sixrooms.R$styleable.NumberProgressBar_max, 100));
        obtainStyledAttributes.recycle();
        a();
    }

    protected int getSuggestedMinimumWidth() {
        return (int) this.g;
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max((int) this.g, Math.max((int) this.h, (int) this.i));
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i, true), a(i2, false));
    }

    private int a(int i, boolean z) {
        int paddingLeft;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (z) {
            paddingLeft = getPaddingLeft() + getPaddingRight();
        } else {
            paddingLeft = getPaddingTop() + getPaddingBottom();
        }
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumWidth;
        if (z) {
            suggestedMinimumWidth = getSuggestedMinimumWidth();
        } else {
            suggestedMinimumWidth = getSuggestedMinimumHeight();
        }
        suggestedMinimumWidth += paddingLeft;
        if (mode != Integer.MIN_VALUE) {
            return suggestedMinimumWidth;
        }
        if (z) {
            return Math.max(suggestedMinimumWidth, size);
        }
        return Math.min(suggestedMinimumWidth, size);
    }

    protected void onDraw(Canvas canvas) {
        float f = 0.0f;
        if (this.E) {
            this.t = getProgress() + "s";
            this.q = this.w.measureText(this.t);
            if (getProgress() == 0) {
                this.D = false;
                this.r = (float) getPaddingLeft();
            } else {
                this.D = true;
                this.z.left = (float) getPaddingLeft();
                this.z.top = (((float) getHeight()) / 2.0f) - (this.h / 2.0f);
                this.z.right = (((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) - this.B) + ((float) getPaddingLeft());
                this.z.bottom = (((float) getHeight()) / 2.0f) + (this.h / 2.0f);
                this.r = this.z.right;
            }
            this.s = (float) ((int) ((((float) getHeight()) / 2.0f) - ((this.w.descent() + this.w.ascent()) / 2.0f)));
            if (this.r + this.q >= ((float) (getWidth() - getPaddingRight()))) {
                this.r = ((float) (getWidth() - getPaddingRight())) - this.q;
                this.z.right = (float) (getWidth() - getPaddingRight());
            }
            this.A.left = this.r;
            this.A.right = this.r + this.q;
            this.A.top = 0.0f;
            this.A.bottom = (((float) getHeight()) / 2.0f) + (this.h / 2.0f);
            if (getProgress() != 0) {
                f = this.r + this.q;
            }
            if (f >= ((float) (getWidth() - getPaddingRight()))) {
                this.C = false;
            } else {
                this.C = true;
                this.y.left = f;
                this.y.right = (float) (getWidth() - getPaddingRight());
                this.y.top = (((float) getHeight()) / 2.0f) + ((-this.i) / 2.0f);
                this.y.bottom = (((float) getHeight()) / 2.0f) + (this.i / 2.0f);
            }
        } else {
            this.z.left = (float) getPaddingLeft();
            this.z.top = (((float) getHeight()) / 2.0f) - (this.h / 2.0f);
            this.z.right = ((((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / (((float) getMax()) * 1.0f)) * ((float) getProgress())) + ((float) getPaddingLeft());
            this.z.bottom = (((float) getHeight()) / 2.0f) + (this.h / 2.0f);
            this.y.left = this.z.right;
            this.y.right = (float) (getWidth() - getPaddingRight());
            this.y.top = (((float) getHeight()) / 2.0f) + ((-this.i) / 2.0f);
            this.y.bottom = (((float) getHeight()) / 2.0f) + (this.i / 2.0f);
        }
        if (this.D) {
            canvas.drawRect(this.z, this.u);
        }
        if (this.C) {
            canvas.drawRect(this.y, this.v);
        }
        if (this.E && getProgress() > 10 && getProgress() < ((long) this.b)) {
            this.x.setColor(getResources().getColor(R.color.changzhan_show_progress_text_bg_red));
            canvas.drawRect(this.A, this.x);
            canvas.drawText(this.t, this.r, this.s, this.w);
        }
    }

    private void a() {
        this.u = new Paint(1);
        this.u.setColor(this.d);
        this.v = new Paint(1);
        this.v.setColor(this.e);
        this.w = new Paint(1);
        this.w.setColor(this.f);
        this.w.setTextSize(this.g);
        this.w.setFakeBoldText(true);
        this.x = new Paint(1);
    }

    public int getTextColor() {
        return this.f;
    }

    public float getProgressTextSize() {
        return this.g;
    }

    public int getUnreachedBarColor() {
        return this.e;
    }

    public int getReachedBarColor() {
        return this.d;
    }

    public long getProgress() {
        return this.c;
    }

    public int getMax() {
        return this.b;
    }

    public float getReachedBarHeight() {
        return this.h;
    }

    public float getUnreachedBarHeight() {
        return this.i;
    }

    public void setProgressTextSize(float f) {
        this.g = f;
        this.w.setTextSize(this.g);
        invalidate();
    }

    public void setProgressTextColor(int i) {
        this.f = i;
        this.w.setColor(this.f);
        invalidate();
    }

    public void setUnreachedBarColor(int i) {
        this.e = i;
        this.v.setColor(this.d);
        invalidate();
    }

    public void setReachedBarColor(int i) {
        this.d = i;
        this.u.setColor(this.d);
        invalidate();
    }

    public void setMax(int i) {
        if (i > 0) {
            this.b = i;
            invalidate();
        }
    }

    public void incrementProgressBy(int i) {
        if (i > 0) {
            setProgress(getProgress() + ((long) i));
        }
    }

    public void setProgress(long j) {
        if (j <= ((long) getMax()) && j >= 0) {
            this.c = j;
            invalidate();
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getProgressTextSize());
        bundle.putFloat("reached_bar_height", getReachedBarHeight());
        bundle.putFloat("unreached_bar_height", getUnreachedBarHeight());
        bundle.putInt("reached_bar_color", getReachedBarColor());
        bundle.putInt("unreached_bar_color", getUnreachedBarColor());
        bundle.putInt("max", getMax());
        bundle.putLong("progress", getProgress());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f = bundle.getInt("text_color");
            this.g = bundle.getFloat("text_size");
            this.h = bundle.getFloat("reached_bar_height");
            this.i = bundle.getFloat("unreached_bar_height");
            this.d = bundle.getInt("reached_bar_color");
            this.e = bundle.getInt("unreached_bar_color");
            a();
            setMax(bundle.getInt("max"));
            setProgress((long) bundle.getInt("progress"));
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public float dp2px(float f) {
        return (getResources().getDisplayMetrics().density * f) + 0.5f;
    }

    public float sp2px(float f) {
        return getResources().getDisplayMetrics().scaledDensity * f;
    }

    public void setProgressTextVisibility(ProgressTextVisibility progressTextVisibility) {
        if (progressTextVisibility == ProgressTextVisibility.Visible) {
            this.E = true;
        } else {
            this.E = false;
        }
        invalidate();
    }
}
