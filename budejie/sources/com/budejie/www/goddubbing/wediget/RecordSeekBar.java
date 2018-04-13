package com.budejie.www.goddubbing.wediget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.budejie.www.util.an;

public class RecordSeekBar extends SeekBar {
    private Paint k;
    private RecordSeekBar$b l;
    private final int m;
    private a n;

    public interface a {
        void c();
    }

    public RecordSeekBar(Context context) {
        this(context, null);
    }

    public RecordSeekBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecordSeekBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = an.x(getContext());
        g();
        this.k = new Paint();
        a(this.k, this.h);
        this.l = RecordSeekBar$b.a;
    }

    protected void onDraw(Canvas canvas) {
        if (this.l != null) {
            switch (RecordSeekBar$2.a[this.l.ordinal()]) {
                case 1:
                    canvas.drawRect(this.d, 0.0f, (float) this.m, this.f, this.b);
                    return;
                case 2:
                    canvas.drawRect(0.0f, 0.0f, this.e, this.f, this.a);
                    canvas.drawRect(this.e, 0.0f, (float) this.m, this.f, this.b);
                    return;
                case 3:
                case 4:
                    a(canvas);
                    return;
                case 5:
                    canvas.drawRect(0.0f, 0.0f, (float) this.m, this.f, this.b);
                    return;
                default:
                    return;
            }
        }
    }

    private void a(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, this.e, this.f, (this.l == RecordSeekBar$b.d ? 1 : null) != null ? this.k : this.a);
        canvas.drawRect(this.e, 0.0f, (float) this.m, this.f, this.b);
    }

    public void a(int i) {
        this.l = RecordSeekBar$b.b;
        this.i = ValueAnimator.ofInt(new int[]{this.j, this.m});
        this.i.setDuration(i < 0 ? 0 : (long) i);
        this.i.addUpdateListener(new RecordSeekBar$1(this));
        this.i.start();
    }

    public void a() {
        super.a();
        this.l = RecordSeekBar$b.c;
        invalidate();
    }

    public void b() {
        this.l = RecordSeekBar$b.d;
        invalidate();
    }

    public void c() {
        this.l = RecordSeekBar$b.e;
        this.j = 0;
        invalidate();
    }

    public void d() {
        this.l = RecordSeekBar$b.a;
        this.j = 0;
        invalidate();
    }

    public void setCallBack(a aVar) {
        this.n = aVar;
    }

    public void e() {
        if (this.i != null) {
            this.i.removeAllUpdateListeners();
        }
    }

    public void f() {
        this.n = null;
    }
}
