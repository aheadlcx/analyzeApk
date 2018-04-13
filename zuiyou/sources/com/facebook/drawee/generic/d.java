package com.facebook.drawee.generic;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.g;
import com.facebook.drawee.drawable.q;
import com.facebook.drawee.drawable.r;
import javax.annotation.Nullable;

public class d extends g implements q {
    @Nullable
    Drawable a = null;
    @Nullable
    private r c;

    public d(Drawable drawable) {
        super(drawable);
    }

    public int getIntrinsicWidth() {
        return -1;
    }

    public int getIntrinsicHeight() {
        return -1;
    }

    public void a(@Nullable r rVar) {
        this.c = rVar;
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.c != null) {
            this.c.a(z);
        }
        return super.setVisible(z, z2);
    }

    @SuppressLint({"WrongCall"})
    public void draw(Canvas canvas) {
        if (isVisible()) {
            if (this.c != null) {
                this.c.a();
            }
            super.draw(canvas);
            if (this.a != null) {
                this.a.setBounds(getBounds());
                this.a.draw(canvas);
            }
        }
    }

    public void d(@Nullable Drawable drawable) {
        this.a = drawable;
        invalidateSelf();
    }
}
