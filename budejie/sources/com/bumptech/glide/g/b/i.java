package com.bumptech.glide.g.b;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import com.bumptech.glide.load.resource.a.b;

public class i extends b {
    private b a;
    private a b;
    private boolean c;

    static class a extends ConstantState {
        private final ConstantState a;
        private final int b;

        a(a aVar) {
            this(aVar.a, aVar.b);
        }

        a(ConstantState constantState, int i) {
            this.a = constantState;
            this.b = i;
        }

        public Drawable newDrawable() {
            return newDrawable(null);
        }

        public Drawable newDrawable(Resources resources) {
            return new i(this, null, resources);
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }

    public i(b bVar, int i) {
        this(new a(bVar.getConstantState(), i), bVar, null);
    }

    i(a aVar, b bVar, Resources resources) {
        this.b = aVar;
        if (bVar != null) {
            this.a = bVar;
        } else if (resources != null) {
            this.a = (b) aVar.a.newDrawable(resources);
        } else {
            this.a = (b) aVar.a.newDrawable();
        }
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.a.setBounds(i, i2, i3, i4);
    }

    public void setBounds(Rect rect) {
        super.setBounds(rect);
        this.a.setBounds(rect);
    }

    public void setChangingConfigurations(int i) {
        this.a.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        this.a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.a.setFilterBitmap(z);
    }

    @TargetApi(11)
    public Callback getCallback() {
        return this.a.getCallback();
    }

    @TargetApi(19)
    public int getAlpha() {
        return this.a.getAlpha();
    }

    public void setColorFilter(int i, Mode mode) {
        this.a.setColorFilter(i, mode);
    }

    public void clearColorFilter() {
        this.a.clearColorFilter();
    }

    public Drawable getCurrent() {
        return this.a.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.a.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.b.b;
    }

    public int getIntrinsicHeight() {
        return this.b.b;
    }

    public int getMinimumWidth() {
        return this.a.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.a.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.a.getPadding(rect);
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        this.a.invalidateSelf();
    }

    public void unscheduleSelf(Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.a.unscheduleSelf(runnable);
    }

    public void scheduleSelf(Runnable runnable, long j) {
        super.scheduleSelf(runnable, j);
        this.a.scheduleSelf(runnable, j);
    }

    public void draw(Canvas canvas) {
        this.a.draw(canvas);
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.a.getOpacity();
    }

    public boolean a() {
        return this.a.a();
    }

    public void a(int i) {
        this.a.a(i);
    }

    public void start() {
        this.a.start();
    }

    public void stop() {
        this.a.stop();
    }

    public boolean isRunning() {
        return this.a.isRunning();
    }

    public Drawable mutate() {
        if (!this.c && super.mutate() == this) {
            this.a = (b) this.a.mutate();
            this.b = new a(this.b);
            this.c = true;
        }
        return this;
    }

    public ConstantState getConstantState() {
        return this.b;
    }
}
