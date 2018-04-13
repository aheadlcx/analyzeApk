package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import com.baidu.mobstat.Config;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class ClockedView extends View {
    public static final int FAILED = 3;
    public static final int LOADING = 1;
    public static final int START = 0;
    public static final int SUCCESS = 2;
    private static String a = "00:00";
    private static String b = "0";
    private int A;
    private int B;
    private int C;
    private int D;
    private int E = 2;
    private Context F;
    private Paint G;
    private int H;
    private int I;
    private long[] J;
    private float[] K;
    private float[] L;
    private boolean M = true;
    private BezierPath N;
    private Paint O;
    private String c = "";
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private long q;
    private int r;
    private int s;
    private long t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private long[] z;

    public ClockedView(Context context) {
        super(context);
    }

    public ClockedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public ClockedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    public long[] getClockedTimes() {
        return this.z;
    }

    public void setClockedTimes(long[] jArr, String str) {
        this.c = str;
        this.z = jArr;
        if (jArr.length >= 7) {
            this.y = 7;
        } else {
            this.y = jArr.length;
        }
        this.J = new long[jArr.length];
        getClockedTime();
        this.M = true;
        invalidate();
    }

    public int getStatus() {
        return this.E;
    }

    public void setStatus(int i) {
        this.E = i;
    }

    private void a(Context context, AttributeSet attributeSet) {
        int i;
        this.F = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockedView);
        this.d = obtainStyledAttributes.getColor(0, -1);
        this.e = obtainStyledAttributes.getDimensionPixelOffset(1, UIHelper.dip2px(context, 12.0f));
        this.k = getFontHeight((float) this.e);
        this.f = obtainStyledAttributes.getColor(2, -1);
        int dip2px = UIHelper.dip2px(context, 0.5f);
        this.g = obtainStyledAttributes.getDimensionPixelOffset(3, dip2px > 1 ? dip2px : 1);
        this.h = obtainStyledAttributes.getColor(4, -1);
        if (dip2px <= 1) {
            dip2px = 1;
        }
        this.i = obtainStyledAttributes.getDimensionPixelOffset(5, dip2px);
        dip2px = UIHelper.dip2px(context, 10.0f);
        this.s = obtainStyledAttributes.getColor(7, -1);
        if (dip2px > 1) {
            i = dip2px;
        } else {
            i = 1;
        }
        this.r = obtainStyledAttributes.getDimensionPixelOffset(6, i);
        this.u = obtainStyledAttributes.getColor(9, -1);
        if (dip2px <= 1) {
            dip2px = 1;
        }
        this.v = obtainStyledAttributes.getDimensionPixelOffset(8, dip2px);
        this.l = getFontWidth((float) this.v, a);
        i = UIHelper.dip2px(context, 12.0f);
        this.w = obtainStyledAttributes.getColor(11, -1);
        if (i <= 1) {
            i = 1;
        }
        this.x = obtainStyledAttributes.getDimensionPixelOffset(10, i);
        this.m = getFontHeight((float) this.x);
        this.n = getFontWidth((float) this.x, b);
        i = UIHelper.dip2px(context, 2.0f);
        this.B = obtainStyledAttributes.getColor(13, -1);
        if (i <= 1) {
            i = 1;
        }
        this.A = obtainStyledAttributes.getDimensionPixelOffset(12, i);
        obtainStyledAttributes.recycle();
        this.G = new TextPaint();
        this.G.setAntiAlias(true);
        this.H = UIHelper.dip2px(this.F, 5.0f);
        this.I = UIHelper.dip2px(this.F, 15.0f);
        this.N = new BezierPath();
        this.O = new Paint();
        this.O.setAntiAlias(true);
        this.O.setStyle(Style.STROKE);
        this.O.setStrokeWidth((float) UIHelper.dip2px(this.F, 2.0f));
        this.O.setColor(-1);
        this.O.setShadowLayer(0.0f, 0.0f, (float) UIHelper.dip2px(this.F, 4.0f), -1);
        this.O.setMaskFilter(new BlurMaskFilter((float) UIHelper.dip2px(this.F, 4.0f), Blur.NORMAL));
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        if (MeasureSpec.getMode(i2) == 0) {
            size2 = (size * 9) / 16;
        }
        size2 = resolveSize(size2, i2);
        setMeasuredDimension(size, size2);
        this.C = size2;
        this.D = size;
        this.j = ((float) (((this.D - this.I) - this.H) - this.H)) - this.l;
        this.o = ((((((((float) (this.C - this.H)) - this.k) - ((float) this.H)) - ((float) this.g)) - ((float) this.i)) - ((float) this.H)) - this.m) - ((float) this.H);
        this.p = this.j;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        g(canvas);
        f(canvas);
        h(canvas);
        e(canvas);
        d(canvas);
        c(canvas);
        b(canvas);
        a(canvas);
    }

    private void a(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        for (int i = 0; i < this.K.length; i++) {
            if (i == this.K.length - 1) {
                this.G.setStrokeWidth((float) UIHelper.dip2px(this.F, 2.0f));
                this.G.setColor(-1);
                canvas.drawCircle(this.K[i], this.L[i], (float) UIHelper.dip2px(this.F, 5.0f), this.G);
                this.G.setColor(-17664);
                canvas.drawCircle(this.K[i], this.L[i], (float) UIHelper.dip2px(this.F, 3.0f), this.G);
            } else {
                this.G.setStrokeWidth((float) UIHelper.dip2px(this.F, 1.0f));
                this.G.setColor(-1);
                canvas.drawCircle(this.K[i], this.L[i], (float) UIHelper.dip2px(this.F, 3.0f), this.G);
                this.G.setColor(-17664);
                canvas.drawCircle(this.K[i], this.L[i], (float) UIHelper.dip2px(this.F, 2.0f), this.G);
            }
        }
    }

    private void b(Canvas canvas) {
        int length = this.K.length;
        if (length != 0) {
            this.L = new float[length];
            for (int i = 0; i < length; i++) {
                float f = ((float) (this.J[i] - this.t)) / ((float) (this.q - this.t));
                this.L[i] = ((1.0f - f) * this.o) + (((((float) this.H) + this.k) + ((float) this.H)) + ((float) this.g));
            }
            if (length > 1) {
                this.N.addBezierThroughPoints(this.K, this.L);
                canvas.drawPath(this.N, this.O);
            }
        }
    }

    private void c(Canvas canvas) {
        if (this.y > 0) {
            this.K = new float[this.y];
            float f = this.p / 8.0f;
            this.G.setColor(this.w);
            this.G.setTextSize((float) this.x);
            for (int i = 0; i < 7; i++) {
                if (i < this.y) {
                    this.K[i] = (((float) this.I) + (((float) (i + 1)) * f)) + (this.n / 2.0f);
                }
                canvas.drawText((i + 1) + "", ((float) this.I) + (((float) (i + 1)) * f), ((((((((float) this.H) + this.k) + ((float) this.H)) + ((float) this.g)) + this.o) + ((float) this.i)) + ((float) this.H)) + ((this.k * 2.0f) / 3.0f), this.G);
            }
        }
    }

    private void d(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        this.G.setTextSize((float) this.v);
        this.G.setColor(this.u);
        canvas.drawText(getDes(this.t), (((float) this.I) + this.j) + ((float) this.H), (((((float) this.H) + this.k) + ((float) this.H)) + this.o) + (this.k / 3.0f), this.G);
    }

    private void e(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        this.G.setTextSize((float) this.r);
        this.G.setColor(this.s);
        canvas.drawText(getDes(this.q), (((float) this.I) + this.j) + ((float) this.H), ((((float) this.H) + this.k) + ((float) this.H)) + (this.k / 4.0f), this.G);
    }

    private void f(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        this.G.setColor(this.h);
        this.G.setStrokeWidth((float) this.i);
        Canvas canvas2 = canvas;
        canvas2.drawLine((float) this.I, this.k + ((float) (this.H * 2)), this.j + ((float) this.I), this.k + ((float) (this.H * 2)), this.G);
    }

    private void g(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        this.G.setColor(this.f);
        this.G.setStrokeWidth((float) this.g);
        Canvas canvas2 = canvas;
        canvas2.drawLine((float) this.I, ((float) this.i) + (((((float) (this.H * 2)) + this.k) + this.o) + ((float) this.g)), this.j + ((float) this.I), ((float) this.i) + (((((float) (this.H * 2)) + this.k) + this.o) + ((float) this.g)), this.G);
    }

    private void h(Canvas canvas) {
        this.G.setStyle(Style.FILL);
        this.G.setTextSize((float) this.e);
        this.G.setColor(this.d);
        this.G.setAntiAlias(true);
        canvas.drawText(this.c, (float) this.I, ((float) this.H) + ((this.k * 3.0f) / 4.0f), this.G);
    }

    public void getClockedTime() {
        if (this.z.length == 0) {
            this.t = 0;
            this.q = 0;
            return;
        }
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < this.z.length; i++) {
            long hourAndMinuteFromClockedTime = getHourAndMinuteFromClockedTime(this.z[i]);
            this.J[i] = hourAndMinuteFromClockedTime;
            if (i == 0) {
                j = hourAndMinuteFromClockedTime;
                j2 = hourAndMinuteFromClockedTime;
            } else if (hourAndMinuteFromClockedTime > j2) {
                j2 = hourAndMinuteFromClockedTime;
            } else if (hourAndMinuteFromClockedTime < j) {
                j = hourAndMinuteFromClockedTime;
            }
        }
        if (j2 > j) {
            this.t = j - 3600;
            this.q = j2 + 3600;
        } else if (j2 == j) {
            this.t = j - 3600;
            this.q = j + 3600;
        }
        if (this.q > 86400) {
            this.q = 86400;
        }
        if (this.t < 0) {
            this.t = 0;
        }
    }

    public long getHourAndMinuteFromClockedTime(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(1000 * j));
        int i = instance.get(11);
        return (long) ((instance.get(12) * 60) + ((i * 60) * 60));
    }

    public Pair<Integer, Integer> getHourAndMin(long j) {
        long j2 = (j / 60) / 60;
        return new Pair(Integer.valueOf((int) j2), Integer.valueOf((int) ((j - ((j2 * 60) * 60)) / 60)));
    }

    public String getDes(long j) {
        int i = 24;
        Pair hourAndMin = getHourAndMin(j);
        int intValue = ((Integer) hourAndMin.first).intValue();
        int intValue2 = ((Integer) hourAndMin.second).intValue();
        if (intValue <= 24) {
            i = intValue;
        }
        if (i <= 0) {
            i = 0;
        }
        if (intValue2 > 60) {
            intValue2 = 60;
        }
        if (intValue2 <= 0) {
            intValue2 = 0;
        }
        if (i >= 10) {
            if (intValue2 >= 10) {
                return i + Config.TRACE_TODAY_VISIT_SPLIT + intValue2;
            }
            return i + ":0" + intValue2;
        } else if (intValue2 >= 10) {
            return "0" + i + Config.TRACE_TODAY_VISIT_SPLIT + intValue2;
        } else {
            return "0" + i + ":0" + intValue2;
        }
    }

    public float getFontHeight(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        FontMetrics fontMetrics = paint.getFontMetrics();
        return (float) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top));
    }

    public float getFontWidth(float f, String str) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return paint.measureText(str);
    }
}
