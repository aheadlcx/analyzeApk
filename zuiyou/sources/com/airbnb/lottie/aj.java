package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.List;

public class aj extends d implements Callback {
    private ai c;
    private final ValueAnimator d = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private float e = 1.0f;
    @Nullable
    private ab f;
    @Nullable
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public aj() {
        super(null);
        this.d.setRepeatCount(0);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ aj a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (this.a.j) {
                    this.a.d.cancel();
                    this.a.a(1.0f);
                    return;
                }
                this.a.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
    }

    public void a(@Nullable String str) {
        this.g = str;
    }

    public void d() {
        if (this.f != null) {
            this.f.a();
        }
    }

    public boolean a(ai aiVar) {
        if (getCallback() == null) {
            throw new IllegalStateException("You or your view must set a Drawable.Callback before setting the composition. This gets done automatically when added to an ImageView. Either call ImageView.setImageDrawable() before setComposition() or call setCallback(yourView.getCallback()) first.");
        } else if (this.c == aiVar) {
            return false;
        } else {
            k();
            this.c = aiVar;
            b(this.e);
            setBounds(0, 0, aiVar.a().width(), aiVar.a().height());
            b(aiVar);
            a(b());
            return true;
        }
    }

    private void k() {
        d();
        a();
        this.f = null;
    }

    private void b(ai aiVar) {
        int i = 0;
        if (aiVar == null) {
            throw new IllegalStateException("Composition is null");
        }
        ah ahVar;
        LongSparseArray longSparseArray = new LongSparseArray(aiVar.d().size());
        List arrayList = new ArrayList(aiVar.d().size());
        int size = aiVar.d().size() - 1;
        ah ahVar2 = null;
        while (size >= 0) {
            Layer layer = (Layer) aiVar.d().get(size);
            ah ahVar3 = new ah(layer, aiVar, this);
            longSparseArray.put(ahVar3.g(), ahVar3);
            if (ahVar2 != null) {
                ahVar2.b(ahVar3);
                ahVar = null;
            } else {
                arrayList.add(ahVar3);
                if (layer.j() == MatteType.Add) {
                    ahVar = ahVar3;
                } else if (layer.j() == MatteType.Invert) {
                    ahVar = ahVar3;
                } else {
                    ahVar = ahVar2;
                }
            }
            size--;
            ahVar2 = ahVar;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            a((ah) arrayList.get(i2));
        }
        while (i < longSparseArray.size()) {
            ahVar = (ah) longSparseArray.get(longSparseArray.keyAt(i));
            ah ahVar4 = (ah) longSparseArray.get(ahVar.d().k());
            if (ahVar4 != null) {
                ahVar.a(ahVar4);
            }
            i++;
        }
    }

    public void invalidateSelf() {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.c != null) {
            Rect bounds = getBounds();
            Rect a = this.c.a();
            int save = canvas.save();
            if (!bounds.equals(a)) {
                canvas.scale(((float) bounds.width()) / ((float) a.width()), ((float) bounds.height()) / ((float) a.height()));
            }
            canvas.clipRect(getBounds());
            super.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    void e() {
        this.j = true;
    }

    void a(boolean z) {
        this.d.setRepeatCount(z ? -1 : 0);
    }

    boolean f() {
        return this.d.getRepeatCount() == -1;
    }

    boolean g() {
        return this.d.isRunning();
    }

    void h() {
        if (this.b.isEmpty()) {
            this.h = true;
            this.i = false;
            return;
        }
        this.d.setCurrentPlayTime((long) (b() * ((float) this.d.getDuration())));
        this.d.start();
    }

    void i() {
        if (this.b.isEmpty()) {
            this.h = false;
            this.i = true;
            return;
        }
        this.d.setCurrentPlayTime((long) (b() * ((float) this.d.getDuration())));
        this.d.reverse();
    }

    void b(float f) {
        this.e = f;
        if (f < 0.0f) {
            this.d.setFloatValues(new float[]{1.0f, 0.0f});
        } else {
            this.d.setFloatValues(new float[]{0.0f, 1.0f});
        }
        if (this.c != null) {
            this.d.setDuration((long) (((float) this.c.b()) / Math.abs(f)));
        }
    }

    void j() {
        this.h = false;
        this.i = false;
        this.d.cancel();
    }

    void a(d dVar) {
        super.a(dVar);
        if (this.h) {
            this.h = false;
            h();
        }
        if (this.i) {
            this.i = false;
            i();
        }
    }

    public int getIntrinsicWidth() {
        return this.c == null ? -1 : this.c.a().width();
    }

    public int getIntrinsicHeight() {
        return this.c == null ? -1 : this.c.a().height();
    }

    Bitmap b(String str) {
        return l().a(str);
    }

    private ab l() {
        if (!(this.f == null || this.f.a(m()))) {
            this.f.a();
            this.f = null;
        }
        if (this.f == null) {
            this.f = new ab(getCallback(), this.g, this.c.f());
        }
        return this.f;
    }

    @Nullable
    private Context m() {
        Callback callback = getCallback();
        if (callback == null) {
            return null;
        }
        return callback instanceof View ? ((View) callback).getContext() : null;
    }

    public void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }
}
