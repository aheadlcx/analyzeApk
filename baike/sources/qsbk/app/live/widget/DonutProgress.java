package qsbk.app.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class DonutProgress extends View {
    private final float A;
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final int F;
    private final int G;
    private final int H;
    private final float I;
    private final float J;
    private final int K;
    protected Paint a;
    protected Paint b;
    private Paint c;
    private Paint d;
    private Paint e;
    private RectF f;
    private RectF g;
    private int h;
    private boolean i;
    private float j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private float r;
    private float s;
    private int t;
    private String u;
    private String v;
    private String w;
    private float x;
    private String y;
    private float z;

    public DonutProgress(Context context) {
        this(context, null);
    }

    public DonutProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DonutProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new RectF();
        this.g = new RectF();
        this.h = 0;
        this.m = 0;
        this.u = "";
        this.v = "";
        this.w = null;
        this.B = Color.parseColor("#3f379e");
        this.C = Color.parseColor("#ffd600");
        this.D = Color.parseColor("#ffffff");
        this.E = Color.rgb(66, 145, 241);
        this.F = Color.parseColor("#3f379e");
        this.G = 20;
        this.H = -90;
        this.I = (float) WindowUtils.sp2px(getContext(), 24.0f);
        this.K = WindowUtils.dp2Px(24);
        this.A = (float) WindowUtils.dp2Px(2);
        this.J = (float) WindowUtils.sp2px(getContext(), 18.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DonutProgress, i, 0);
        a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        a();
    }

    protected void a() {
        if (this.i) {
            this.a = new TextPaint();
            this.a.setColor(this.k);
            this.a.setTextSize(this.j);
            this.a.setAntiAlias(true);
            this.b = new TextPaint();
            this.b.setColor(this.l);
            this.b.setTextSize(this.x);
            this.b.setAntiAlias(true);
        }
        this.c = new Paint();
        this.c.setColor(this.o);
        this.c.setStyle(Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(this.r);
        this.d = new Paint();
        this.d.setColor(this.p);
        this.d.setStyle(Style.STROKE);
        this.d.setAntiAlias(true);
        this.d.setStrokeWidth(this.s);
        this.e = new Paint();
        this.e.setColor(this.t);
        this.e.setAntiAlias(true);
    }

    protected void a(TypedArray typedArray) {
        this.o = typedArray.getColor(R.styleable.DonutProgress_donut_finished_color, this.B);
        this.p = typedArray.getColor(R.styleable.DonutProgress_donut_unfinished_color, this.C);
        this.i = typedArray.getBoolean(R.styleable.DonutProgress_donut_show_text, true);
        this.h = typedArray.getResourceId(R.styleable.DonutProgress_donut_inner_drawable, 0);
        setMax(typedArray.getInt(R.styleable.DonutProgress_donut_max, 20));
        setProgress(typedArray.getInt(R.styleable.DonutProgress_donut_progress, 0));
        this.r = typedArray.getDimension(R.styleable.DonutProgress_donut_finished_stroke_width, this.A);
        this.s = typedArray.getDimension(R.styleable.DonutProgress_donut_unfinished_stroke_width, this.A);
        if (this.i) {
            if (typedArray.getString(R.styleable.DonutProgress_donut_prefix_text) != null) {
                this.u = typedArray.getString(R.styleable.DonutProgress_donut_prefix_text);
            }
            if (typedArray.getString(R.styleable.DonutProgress_donut_suffix_text) != null) {
                this.v = typedArray.getString(R.styleable.DonutProgress_donut_suffix_text);
            }
            if (typedArray.getString(R.styleable.DonutProgress_donut_text) != null) {
                this.w = typedArray.getString(R.styleable.DonutProgress_donut_text);
            }
            this.k = typedArray.getColor(R.styleable.DonutProgress_donut_text_color, this.D);
            this.j = typedArray.getDimension(R.styleable.DonutProgress_donut_text_size, this.I);
            this.x = typedArray.getDimension(R.styleable.DonutProgress_donut_inner_bottom_text_size, this.J);
            this.l = typedArray.getColor(R.styleable.DonutProgress_donut_inner_bottom_text_color, this.E);
            this.y = typedArray.getString(R.styleable.DonutProgress_donut_inner_bottom_text);
        }
        this.x = typedArray.getDimension(R.styleable.DonutProgress_donut_inner_bottom_text_size, this.J);
        this.l = typedArray.getColor(R.styleable.DonutProgress_donut_inner_bottom_text_color, this.E);
        this.y = typedArray.getString(R.styleable.DonutProgress_donut_inner_bottom_text);
        this.q = typedArray.getInt(R.styleable.DonutProgress_donut_circle_starting_degree, -90);
        this.t = typedArray.getColor(R.styleable.DonutProgress_donut_background_color, this.F);
    }

    public void invalidate() {
        a();
        super.invalidate();
    }

    public boolean isShowText() {
        return this.i;
    }

    public void setShowText(boolean z) {
        this.i = z;
    }

    public float getFinishedStrokeWidth() {
        return this.r;
    }

    public void setFinishedStrokeWidth(float f) {
        this.r = f;
        invalidate();
    }

    public float getUnfinishedStrokeWidth() {
        return this.s;
    }

    public void setUnfinishedStrokeWidth(float f) {
        this.s = f;
        invalidate();
    }

    private float getProgressAngle() {
        return (getProgress() / ((float) this.n)) * 360.0f;
    }

    public float getProgress() {
        return (float) this.m;
    }

    public void setProgress(int i) {
        this.m = i;
        if (this.m > getMax()) {
            this.m %= getMax();
        }
        invalidate();
    }

    public int getMax() {
        return this.n;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.n = i;
            invalidate();
        }
    }

    public float getTextSize() {
        return this.j;
    }

    public void setTextSize(float f) {
        this.j = f;
        invalidate();
    }

    public int getTextColor() {
        return this.k;
    }

    public void setTextColor(int i) {
        this.k = i;
        invalidate();
    }

    public int getFinishedStrokeColor() {
        return this.o;
    }

    public void setFinishedStrokeColor(int i) {
        this.o = i;
        invalidate();
    }

    public int getUnfinishedStrokeColor() {
        return this.p;
    }

    public void setUnfinishedStrokeColor(int i) {
        this.p = i;
        invalidate();
    }

    public String getText() {
        return this.w;
    }

    public void setText(String str) {
        this.w = str;
        invalidate();
    }

    public String getSuffixText() {
        return this.v;
    }

    public void setSuffixText(String str) {
        this.v = str;
        invalidate();
    }

    public String getPrefixText() {
        return this.u;
    }

    public void setPrefixText(String str) {
        this.u = str;
        invalidate();
    }

    public int getInnerBackgroundColor() {
        return this.t;
    }

    public void setInnerBackgroundColor(int i) {
        this.t = i;
        invalidate();
    }

    public String getInnerBottomText() {
        return this.y;
    }

    public void setInnerBottomText(String str) {
        this.y = str;
        invalidate();
    }

    public float getInnerBottomTextSize() {
        return this.x;
    }

    public void setInnerBottomTextSize(float f) {
        this.x = f;
        invalidate();
    }

    public int getInnerBottomTextColor() {
        return this.l;
    }

    public void setInnerBottomTextColor(int i) {
        this.l = i;
        invalidate();
    }

    public int getStartingDegree() {
        return this.q;
    }

    public void setStartingDegree(int i) {
        this.q = i;
        invalidate();
    }

    public int getAttributeResourceId() {
        return this.h;
    }

    public void setAttributeResourceId(int i) {
        this.h = i;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i), a(i2));
        this.z = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.K;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float max = Math.max(this.r, this.s);
        this.f.set(max, max, ((float) getWidth()) - max, ((float) getHeight()) - max);
        this.g.set(max, max, ((float) getWidth()) - max, ((float) getHeight()) - max);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - Math.min(this.r, this.s)) + Math.abs(this.r - this.s)) / 2.0f, this.e);
        canvas.drawArc(this.f, (float) getStartingDegree(), getProgressAngle(), false, this.c);
        canvas.drawArc(this.g, getProgressAngle() + ((float) getStartingDegree()), 360.0f - getProgressAngle(), false, this.d);
        if (this.i) {
            String str = this.w != null ? this.w : this.u + (this.n - this.m) + this.v;
            if (!TextUtils.isEmpty(str)) {
                canvas.drawText(str, (((float) getWidth()) - this.a.measureText(str)) / 2.0f, (((float) getWidth()) - (this.a.descent() + this.a.ascent())) / 2.0f, this.a);
            }
            if (!TextUtils.isEmpty(getInnerBottomText())) {
                this.b.setTextSize(this.x);
                canvas.drawText(getInnerBottomText(), (((float) getWidth()) - this.b.measureText(getInnerBottomText())) / 2.0f, (((float) getHeight()) - this.z) - ((this.a.descent() + this.a.ascent()) / 2.0f), this.b);
            }
        }
        if (this.h != 0) {
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), this.h);
            canvas.drawBitmap(decodeResource, ((float) (getWidth() - decodeResource.getWidth())) / 2.0f, ((float) (getHeight() - decodeResource.getHeight())) / 2.0f, null);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("text_size", getTextSize());
        bundle.putFloat("inner_bottom_text_size", getInnerBottomTextSize());
        bundle.putFloat("inner_bottom_text_color", (float) getInnerBottomTextColor());
        bundle.putString("inner_bottom_text", getInnerBottomText());
        bundle.putInt("inner_bottom_text_color", getInnerBottomTextColor());
        bundle.putInt("finished_stroke_color", getFinishedStrokeColor());
        bundle.putInt("unfinished_stroke_color", getUnfinishedStrokeColor());
        bundle.putInt("max", getMax());
        bundle.putInt("starting_degree", getStartingDegree());
        bundle.putFloat(NotificationCompat.CATEGORY_PROGRESS, getProgress());
        bundle.putString("suffix", getSuffixText());
        bundle.putString("prefix", getPrefixText());
        bundle.putString("text", getText());
        bundle.putFloat("finished_stroke_width", getFinishedStrokeWidth());
        bundle.putFloat("unfinished_stroke_width", getUnfinishedStrokeWidth());
        bundle.putInt("inner_background_color", getInnerBackgroundColor());
        bundle.putInt("inner_drawable", getAttributeResourceId());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.k = bundle.getInt("text_color");
            this.j = bundle.getFloat("text_size");
            this.x = bundle.getFloat("inner_bottom_text_size");
            this.y = bundle.getString("inner_bottom_text");
            this.l = bundle.getInt("inner_bottom_text_color");
            this.o = bundle.getInt("finished_stroke_color");
            this.p = bundle.getInt("unfinished_stroke_color");
            this.r = bundle.getFloat("finished_stroke_width");
            this.s = bundle.getFloat("unfinished_stroke_width");
            this.t = bundle.getInt("inner_background_color");
            this.h = bundle.getInt("inner_drawable");
            a();
            setMax(bundle.getInt("max"));
            setStartingDegree(bundle.getInt("starting_degree"));
            setProgress(bundle.getInt(NotificationCompat.CATEGORY_PROGRESS));
            this.u = bundle.getString("prefix");
            this.v = bundle.getString("suffix");
            this.w = bundle.getString("text");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setDonut_progress(String str) {
        if (!TextUtils.isEmpty(str)) {
            setProgress(Integer.parseInt(str));
        }
    }
}
