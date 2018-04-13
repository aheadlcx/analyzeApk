package com.budejie.www.activity.video.barrage.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.reflect.Array;
import tv.cjump.jni.NativeBitmapFactory;

public class e {
    public Canvas a;
    public Bitmap b;
    public Bitmap[][] c;
    public Object d;
    public int e;
    public int f;
    private int g;

    public void a(int i, int i2, int i3, boolean z) {
        int i4 = 1;
        if (z) {
            if (!(i == this.e && i2 == this.f)) {
                i4 = 0;
            }
        } else if (i > this.e || i2 > this.f) {
            i4 = 0;
        }
        if (i4 == 0 || this.b == null) {
            if (this.b != null) {
                a();
            }
            this.e = i;
            this.f = i2;
            this.b = NativeBitmapFactory.a(i, i2, Config.ARGB_8888);
            if (i3 > 0) {
                this.g = i3;
                this.b.setDensity(i3);
            }
            if (this.a == null) {
                this.a = new Canvas(this.b);
                this.a.setDensity(i3);
                return;
            }
            this.a.setBitmap(this.b);
            return;
        }
        this.a.setBitmap(null);
        this.b.eraseColor(0);
        this.a.setBitmap(this.b);
        b();
    }

    public synchronized void a() {
        this.f = 0;
        this.e = 0;
        if (this.b != null) {
            this.b.recycle();
            this.b = null;
        }
        b();
        this.d = null;
    }

    @SuppressLint({"NewApi"})
    public void a(int i, int i2, int i3, int i4) {
        b();
        if (this.e > 0 && this.f > 0 && this.b != null) {
            if (this.e > i3 || this.f > i4) {
                int min = Math.min(i3, i);
                int min2 = Math.min(i4, i2);
                int i5 = (this.e / min) + (this.e % min == 0 ? 0 : 1);
                int i6 = (this.f / min2) + (this.f % min2 == 0 ? 0 : 1);
                int i7 = this.e / i5;
                int i8 = this.f / i6;
                Bitmap[][] bitmapArr = (Bitmap[][]) Array.newInstance(Bitmap.class, new int[]{i6, i5});
                if (this.a == null) {
                    this.a = new Canvas();
                    if (this.g > 0) {
                        this.a.setDensity(this.g);
                    }
                }
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                for (int i9 = 0; i9 < i6; i9++) {
                    for (min2 = 0; min2 < i5; min2++) {
                        Bitmap[] bitmapArr2 = bitmapArr[i9];
                        Bitmap a = NativeBitmapFactory.a(i7, i8, Config.ARGB_8888);
                        bitmapArr2[min2] = a;
                        if (this.g > 0) {
                            a.setDensity(this.g);
                        }
                        this.a.setBitmap(a);
                        int i10 = min2 * i7;
                        int i11 = i9 * i8;
                        rect.set(i10, i11, i10 + i7, i11 + i8);
                        rect2.set(0, 0, a.getWidth(), a.getHeight());
                        this.a.drawBitmap(this.b, rect, rect2, null);
                    }
                }
                this.a.setBitmap(this.b);
                this.c = bitmapArr;
            }
        }
    }

    private void b() {
        if (this.c != null) {
            for (int i = 0; i < this.c.length; i++) {
                for (int i2 = 0; i2 < this.c[i].length; i2++) {
                    if (this.c[i][i2] != null) {
                        this.c[i][i2].recycle();
                        this.c[i][i2] = null;
                    }
                }
            }
            this.c = (Bitmap[][]) null;
        }
    }

    public final synchronized boolean a(Canvas canvas, float f, float f2, Paint paint) {
        boolean z = true;
        synchronized (this) {
            if (this.c != null) {
                for (int i = 0; i < this.c.length; i++) {
                    for (int i2 = 0; i2 < this.c[i].length; i2++) {
                        Bitmap bitmap = this.c[i][i2];
                        if (bitmap != null) {
                            float width = ((float) (bitmap.getWidth() * i2)) + f;
                            if (width <= ((float) canvas.getWidth()) && ((float) bitmap.getWidth()) + width >= 0.0f) {
                                float height = ((float) (bitmap.getHeight() * i)) + f2;
                                if (height <= ((float) canvas.getHeight()) && ((float) bitmap.getHeight()) + height >= 0.0f) {
                                    canvas.drawBitmap(bitmap, width, height, paint);
                                }
                            }
                        }
                    }
                }
            } else if (this.b != null) {
                canvas.drawBitmap(this.b, f, f2, paint);
            } else {
                z = false;
            }
        }
        return z;
    }
}
