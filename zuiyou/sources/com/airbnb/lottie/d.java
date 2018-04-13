package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class d extends Drawable {
    final a<Path> a = new a<Path>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(Path path) {
            this.a.invalidateSelf();
        }
    };
    final List<d> b = new ArrayList();
    private final a<Integer> c = new a<Integer>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(Integer num) {
            this.a.invalidateSelf();
        }
    };
    private final a<Float> d = new a<Float>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(Float f) {
            this.a.invalidateSelf();
        }
    };
    private final a<aw> e = new a<aw>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(aw awVar) {
            this.a.invalidateSelf();
        }
    };
    private final a<PointF> f = new a<PointF>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(PointF pointF) {
            this.a.invalidateSelf();
        }
    };
    @Nullable
    private d g;
    private final Paint h = new Paint();
    @ColorInt
    private int i;
    private final List<n<?, ?>> j = new ArrayList();
    @FloatRange(from = 0.0d, to = 1.0d)
    private float k = 0.0f;
    private bh l;

    d(Callback callback) {
        setCallback(callback);
        this.h.setAlpha(0);
        this.h.setStyle(Style.FILL);
    }

    void a(@ColorInt int i) {
        this.i = i;
        this.h.setColor(i);
        invalidateSelf();
    }

    void a(n<?, ?> nVar) {
        this.j.add(nVar);
    }

    void b(n<?, ?> nVar) {
        this.j.remove(nVar);
    }

    public void draw(@NonNull Canvas canvas) {
        int save = canvas.save();
        a(canvas, this);
        int alpha = Color.alpha(this.i);
        if (alpha != 0) {
            int intValue;
            if (this.l != null) {
                intValue = (((Integer) this.l.e().b()).intValue() * alpha) / 255;
            } else {
                intValue = alpha;
            }
            this.h.setAlpha(intValue);
            if (intValue > 0) {
                canvas.drawRect(getBounds(), this.h);
            }
        }
        for (alpha = 0; alpha < this.b.size(); alpha++) {
            ((d) this.b.get(alpha)).draw(canvas);
        }
        canvas.restoreToCount(save);
    }

    int a(@Nullable Canvas canvas) {
        if (canvas == null) {
            return 0;
        }
        return canvas.save();
    }

    void a(@Nullable Canvas canvas, d dVar) {
        if (canvas != null && this.l != null) {
            PointF pointF = (PointF) dVar.l.b().b();
            if (!(pointF.x == 0.0f && pointF.y == 0.0f)) {
                canvas.translate(pointF.x, pointF.y);
            }
            float floatValue = ((Float) dVar.l.d().b()).floatValue();
            if (floatValue != 0.0f) {
                canvas.rotate(floatValue);
            }
            aw awVar = (aw) dVar.l.c().b();
            if (!(awVar.a() == 1.0f && awVar.b() == 1.0f)) {
                canvas.scale(awVar.a(), awVar.b());
            }
            pointF = (PointF) dVar.l.a().b();
            if (pointF.x != 0.0f || pointF.y != 0.0f) {
                canvas.translate(-pointF.x, -pointF.y);
            }
        }
    }

    public void setAlpha(int i) {
        throw new IllegalArgumentException("This shouldn't be used.");
    }

    public int getAlpha() {
        float f = 1.0f;
        float intValue = this.l == null ? 1.0f : ((float) ((Integer) this.l.e().b()).intValue()) / 255.0f;
        if (this.g != null) {
            f = ((float) this.g.getAlpha()) / 255.0f;
        }
        return (int) ((intValue * f) * 255.0f);
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    void a(bh bhVar) {
        this.l = bhVar;
        n a = bhVar.a();
        n b = bhVar.b();
        n c = bhVar.c();
        n d = bhVar.d();
        n e = bhVar.e();
        a.a(this.f);
        b.a(this.f);
        c.a(this.e);
        d.a(this.d);
        e.a(this.c);
        a(a);
        a(b);
        a(c);
        a(d);
        a(e);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    void a(d dVar) {
        dVar.g = this;
        this.b.add(dVar);
        dVar.a(this.k);
        invalidateSelf();
    }

    void a() {
        this.b.clear();
        invalidateSelf();
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        int i = 0;
        this.k = f;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            ((n) this.j.get(i2)).a(f);
        }
        while (i < this.b.size()) {
            ((d) this.b.get(i)).a(f);
            i++;
        }
    }

    float b() {
        return this.k;
    }

    aj c() {
        if (getCallback() instanceof aj) {
            return (aj) getCallback();
        }
        return null;
    }
}
