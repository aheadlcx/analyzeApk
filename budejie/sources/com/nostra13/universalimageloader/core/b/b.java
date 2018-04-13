package com.nostra13.universalimageloader.core.b;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.d;
import pl.droidsonroids.gif.GifDrawable;

public class b implements a {
    protected final int a;

    public static class a extends Drawable {
        protected final int a;
        protected final RectF b = new RectF();
        protected final RectF c;
        protected final BitmapShader d;
        protected final Paint e;

        public a(Bitmap bitmap, int i) {
            this.a = i;
            this.d = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            this.c = new RectF((float) i, (float) i, (float) (bitmap.getWidth() - i), (float) (bitmap.getHeight() - i));
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setShader(this.d);
        }

        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.b.set((float) this.a, (float) this.a, (float) (rect.width() - this.a), (float) (rect.height() - this.a));
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.c, this.b, ScaleToFit.FILL);
            this.d.setLocalMatrix(matrix);
        }

        public void draw(Canvas canvas) {
            canvas.drawCircle(this.b.width() / 2.0f, this.b.height() / 2.0f, this.b.width() / 2.0f, this.e);
        }

        public int getOpacity() {
            return -3;
        }

        public void setAlpha(int i) {
            this.e.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.e.setColorFilter(colorFilter);
        }
    }

    public b() {
        this(0);
    }

    public b(int i) {
        this.a = i;
    }

    public void a(Bitmap bitmap, com.nostra13.universalimageloader.core.c.a aVar, LoadedFrom loadedFrom) {
        if (aVar instanceof com.nostra13.universalimageloader.core.c.b) {
            aVar.a(new a(bitmap, this.a));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }

    public void a(GifDrawable gifDrawable, com.nostra13.universalimageloader.core.c.a aVar, LoadedFrom loadedFrom) {
        gifDrawable.a(true);
        d.a().a(gifDrawable);
        aVar.a(gifDrawable);
    }
}
