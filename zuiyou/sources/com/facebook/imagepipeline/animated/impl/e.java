package com.facebook.imagepipeline.animated.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.facebook.imagepipeline.animated.a.a;
import com.facebook.imagepipeline.animated.base.f;

public class e implements f {
    private static final Class<?> a = f.class;
    private final a b;
    private final DisplayMetrics c;
    private final TextPaint d = new TextPaint();
    private final StringBuilder e = new StringBuilder();
    private final g f = new g();
    private final g g = new g();
    private com.facebook.imagepipeline.animated.base.e h;
    private long i;

    public e(a aVar, DisplayMetrics displayMetrics) {
        this.b = aVar;
        this.c = displayMetrics;
        this.d.setColor(-16776961);
        this.d.setTextSize((float) c(14));
    }

    public void a(com.facebook.imagepipeline.animated.base.e eVar) {
        this.h = eVar;
    }

    public void a() {
        this.i = SystemClock.uptimeMillis();
    }

    public void b() {
        long uptimeMillis = SystemClock.uptimeMillis() - this.i;
        if (uptimeMillis > 3) {
            com.facebook.common.c.a.a(a, "onStart took %d", Long.valueOf(uptimeMillis));
        }
    }

    public void c() {
        this.i = SystemClock.uptimeMillis();
    }

    public void d() {
        long uptimeMillis = SystemClock.uptimeMillis() - this.i;
        if (uptimeMillis > 3) {
            com.facebook.common.c.a.a(a, "onNextFrame took %d", Long.valueOf(uptimeMillis));
        }
    }

    public void a(int i) {
        this.f.a(i);
        if (i > 0) {
            com.facebook.common.c.a.a(a, "Dropped %d frames", Integer.valueOf(i));
        }
    }

    public void b(int i) {
        this.g.a(i);
    }

    public void e() {
        this.i = SystemClock.uptimeMillis();
    }

    public void f() {
        com.facebook.common.c.a.a(a, "draw took %d", Long.valueOf(SystemClock.uptimeMillis() - this.i));
    }

    public void a(Canvas canvas, Rect rect) {
        int i;
        int b = this.f.b(10);
        int b2 = this.g.b(10);
        b += b2;
        int c = c(10);
        int c2 = c(20);
        int c3 = c(5);
        if (b > 0) {
            b = (b2 * 100) / b;
            this.e.setLength(0);
            this.e.append(b);
            this.e.append("%");
            canvas.drawText(this.e, 0, this.e.length(), (float) c, (float) c2, this.d);
            b = ((int) (((float) c) + this.d.measureText(this.e, 0, this.e.length()))) + c3;
        } else {
            b = c;
        }
        b2 = this.h.j();
        this.e.setLength(0);
        this.b.a(this.e, b2);
        float measureText = this.d.measureText(this.e, 0, this.e.length());
        if (((float) b) + measureText > ((float) rect.width())) {
            c2 = (int) (((float) c2) + (this.d.getTextSize() + ((float) c3)));
            i = c;
        } else {
            i = b;
        }
        canvas.drawText(this.e, 0, this.e.length(), (float) i, (float) c2, this.d);
        b = ((int) (((float) i) + measureText)) + c3;
        this.e.setLength(0);
        this.h.a(this.e);
        if (this.d.measureText(this.e, 0, this.e.length()) + ((float) b) > ((float) rect.width())) {
            c2 = (int) (((float) c2) + (this.d.getTextSize() + ((float) c3)));
        } else {
            c = b;
        }
        canvas.drawText(this.e, 0, this.e.length(), (float) c, (float) c2, this.d);
    }

    private int c(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.c);
    }
}
