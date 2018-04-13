package com.budejie.www.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.budejie.www.R$styleable;
import com.budejie.www.util.aa;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VoiceLineView extends View {
    private float A = 7.0f;
    private int B = 1;
    private List<Rect> C;
    private long D = 0;
    private int E = 90;
    List<Path> a = null;
    private final int b = 0;
    private final int c = 1;
    private final int d = 7;
    private final int e = 7;
    private final int f = 7;
    private final int g = 1;
    private final int h = 10;
    private int i = -16777216;
    private int j = -16777216;
    private float k = 4.0f;
    private Paint l;
    private Paint m;
    private int n;
    private int o = 4;
    private float p = 100.0f;
    private float q = 0.0f;
    private boolean r = false;
    private float s = 1.0f;
    private float t = 10.0f;
    private int u = 1;
    private float v = 1.0f;
    private long w = 0;
    private float x = 14.0f;
    private float y = 7.0f;
    private float z = 7.0f;

    public VoiceLineView(Context context) {
        super(context);
    }

    public VoiceLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public VoiceLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        int i = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.voiceView);
        this.n = obtainStyledAttributes.getInt(9, 0);
        this.j = obtainStyledAttributes.getColor(2, -16777216);
        this.p = obtainStyledAttributes.getFloat(8, 100.0f);
        this.o = obtainStyledAttributes.getInt(10, 4);
        if (this.n == 1) {
            this.y = obtainStyledAttributes.getDimension(4, 7.0f);
            this.z = obtainStyledAttributes.getDimension(5, 7.0f);
            this.A = obtainStyledAttributes.getDimension(6, 7.0f);
            this.B = obtainStyledAttributes.getInt(7, 1);
            this.x = this.y + this.z;
        } else {
            this.i = obtainStyledAttributes.getColor(0, -16777216);
            this.k = obtainStyledAttributes.getDimension(1, 4.0f);
            this.E = obtainStyledAttributes.getInt(3, 90);
            this.u = obtainStyledAttributes.getInt(11, 1);
            this.a = new ArrayList(20);
            while (i < 20) {
                this.a.add(new Path());
                i++;
            }
        }
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        if (this.n == 1) {
            c(canvas);
        } else {
            a(canvas);
            b(canvas);
        }
        a();
    }

    private void a(Canvas canvas) {
        if (this.l == null) {
            this.l = new Paint();
            this.l.setColor(this.i);
            this.l.setAntiAlias(true);
        }
        canvas.save();
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, ((float) (getHeight() / 2)) - (this.k / 2.0f), (float) getWidth(), (this.k / 2.0f) + ((float) (getHeight() / 2)), this.l);
        canvas.restore();
    }

    private void b(Canvas canvas) {
        int i;
        int i2 = 0;
        c();
        if (this.m == null) {
            this.m = new Paint();
            this.m.setColor(this.j);
            this.m.setAntiAlias(true);
            this.m.setStyle(Style.STROKE);
            this.m.setStrokeWidth(2.0f);
        }
        canvas.save();
        int height = getHeight() / 2;
        for (i = 0; i < this.a.size(); i++) {
            ((Path) this.a.get(i)).reset();
            ((Path) this.a.get(i)).moveTo((float) getWidth(), (float) (getHeight() / 2));
        }
        float width = (float) (getWidth() - 1);
        while (width >= 0.0f) {
            this.s = (((this.t * 4.0f) * width) / ((float) getWidth())) - (((((this.t * 4.0f) * width) * width) / ((float) getWidth())) / ((float) getWidth()));
            for (i = 1; i <= this.a.size(); i++) {
                float sin = ((float) Math.sin((((((double) width) - Math.pow(1.22d, (double) i)) * 3.141592653589793d) / 180.0d) - ((double) this.q))) * this.s;
                ((Path) this.a.get(i - 1)).lineTo(width, (((((float) (i * 2)) * sin) / ((float) this.a.size())) - ((sin * 15.0f) / ((float) this.a.size()))) + ((float) height));
            }
            width -= (float) this.u;
        }
        while (i2 < this.a.size()) {
            if (i2 == this.a.size() - 1) {
                this.m.setAlpha(255);
            } else {
                this.m.setAlpha((i2 * 130) / this.a.size());
            }
            if (this.m.getAlpha() > 0) {
                canvas.drawPath((Path) this.a.get(i2), this.m);
            }
            i2++;
        }
        canvas.restore();
    }

    private void c(Canvas canvas) {
        aa.b("VoiceLine", "drawVoiceRect");
        if (this.m == null) {
            this.m = new Paint();
            this.m.setColor(this.j);
            this.m.setAntiAlias(true);
            this.m.setStyle(Style.FILL);
        }
        b();
        canvas.translate((float) this.w, 0.0f);
        for (int size = this.C.size() - 1; size >= 0; size--) {
            canvas.drawRect((Rect) this.C.get(size), this.m);
        }
        d();
    }

    private void b() {
        int i;
        if (this.C == null) {
            this.C = new LinkedList();
            for (i = 0; i < 10; i++) {
                b();
                d();
            }
        }
        i = (int) (this.z + this.y);
        if (((float) (this.w % ((long) i))) < this.x) {
            int width;
            if (this.B == 2) {
                width = (int) ((((-this.y) - ((float) this.w)) + ((float) (this.w % ((long) i)))) + ((float) getWidth()));
                i = (int) (((-this.w) + (this.w % ((long) i))) + ((long) getWidth()));
            } else {
                width = (int) ((-this.w) + (this.w % ((long) i)));
                i = (int) (((float) (this.w % ((long) i))) + (this.y - ((float) this.w)));
            }
            Rect rect = new Rect(width, (int) ((((float) (getHeight() / 2)) - (this.A / 2.0f)) - this.v), i, (int) ((((float) (getHeight() / 2)) + (this.A / 2.0f)) + this.v));
            if (this.C.size() > 10) {
                this.C.remove(0);
            }
            this.C.add(rect);
        }
    }

    public void setVolume(int i) {
        aa.b("VoiceLine", "setVolume volume=" + i);
        if (((float) i) > (this.p * ((float) this.o)) / 25.0f) {
            this.r = true;
            this.v = ((float) ((getHeight() * i) / 2)) / this.p;
            return;
        }
        this.v = 0.0f;
    }

    private void c() {
        if (this.D == 0) {
            this.D = System.currentTimeMillis();
            this.q = (float) (((double) this.q) + 1.5d);
        } else if (System.currentTimeMillis() - this.D > ((long) this.E)) {
            this.D = System.currentTimeMillis();
            this.q = (float) (((double) this.q) + 1.5d);
        } else {
            return;
        }
        if (this.t >= this.v || !this.r) {
            this.r = false;
            if (this.t <= 10.0f) {
                this.t = 10.0f;
                return;
            } else if (this.t < ((float) (getHeight() / 30))) {
                this.t -= (float) (getHeight() / 60);
                return;
            } else {
                this.t -= (float) (getHeight() / 30);
                return;
            }
        }
        this.t += (float) (getHeight() / 30);
    }

    private void d() {
        if (this.B == 2) {
            this.w = (long) (((float) this.w) - this.x);
        } else {
            this.w = (long) (((float) this.w) + this.x);
        }
        if (this.t >= this.v || !this.r) {
            this.r = false;
            if (this.t <= 10.0f) {
                this.t = 10.0f;
                return;
            } else if (this.t < ((float) (getHeight() / 30))) {
                this.t -= (float) (getHeight() / 60);
                return;
            } else {
                this.t -= (float) (getHeight() / 30);
                return;
            }
        }
        this.t += (float) (getHeight() / 30);
    }

    public void a() {
        if (this.n == 1) {
            postInvalidateDelayed(50);
        } else {
            invalidate();
        }
    }
}
