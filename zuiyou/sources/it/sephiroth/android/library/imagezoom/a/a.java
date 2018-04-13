package it.sephiroth.android.library.imagezoom.a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class a extends Drawable {
    protected Bitmap a;
    protected Paint b;
    protected int c;
    protected int d;

    public a(Bitmap bitmap) {
        this.a = bitmap;
        if (this.a != null) {
            this.c = this.a.getWidth();
            this.d = this.a.getHeight();
        } else {
            this.c = 0;
            this.d = 0;
        }
        this.b = new Paint();
        this.b.setDither(true);
        this.b.setFilterBitmap(true);
    }

    public void draw(Canvas canvas) {
        if (this.a != null && !this.a.isRecycled()) {
            canvas.drawBitmap(this.a, 0.0f, 0.0f, this.b);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
    }

    public int getIntrinsicWidth() {
        return this.c;
    }

    public int getIntrinsicHeight() {
        return this.d;
    }

    public int getMinimumWidth() {
        return this.c;
    }

    public int getMinimumHeight() {
        return this.d;
    }
}
