package com.scwang.smartrefresh.layout.d.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class b extends Drawable {
    protected static final Region j = new Region();
    protected static final Region k = new Region(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Paint a = new Paint();
    protected List<Path> b;
    protected List<Integer> c;
    protected int d = 1;
    protected int e = 1;
    protected int f = 0;
    protected int g = 0;
    protected int h;
    protected int i;
    protected List<Path> l;
    protected List<String> m;
    private Bitmap n;
    private boolean o;

    public b() {
        this.a.setColor(-15614977);
        this.a.setStyle(Style.FILL);
        this.a.setAntiAlias(true);
    }

    protected void a() {
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        int i = 0;
        if (this.b != null) {
            num = null;
            num2 = null;
            num3 = null;
            num4 = null;
            for (Path path : this.b) {
                j.setPath(path, k);
                Rect bounds = j.getBounds();
                num4 = Integer.valueOf(Math.min(num4 == null ? bounds.top : num4.intValue(), bounds.top));
                num3 = Integer.valueOf(Math.min(num3 == null ? bounds.left : num3.intValue(), bounds.left));
                num2 = Integer.valueOf(Math.max(num2 == null ? bounds.right : num2.intValue(), bounds.right));
                num = Integer.valueOf(Math.max(num == null ? bounds.bottom : num.intValue(), bounds.bottom));
            }
        } else {
            num = null;
            num2 = null;
            num3 = null;
            num4 = null;
        }
        this.f = num3 == null ? 0 : num3.intValue();
        this.g = num4 == null ? 0 : num4.intValue();
        this.d = num2 == null ? 0 : num2.intValue() - this.f;
        if (num != null) {
            i = num.intValue() - this.g;
        }
        this.e = i;
        if (this.h == 0) {
            this.h = this.d;
        }
        if (this.i == 0) {
            this.i = this.e;
        }
        Rect bounds2 = getBounds();
        super.setBounds(bounds2.left, bounds2.top, bounds2.left + this.d, bounds2.top + this.e);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.l == null || this.l.size() <= 0 || (i5 == this.d && i6 == this.e)) {
            super.setBounds(i, i2, i3, i4);
            return;
        }
        this.b = a.a((((float) i5) * 1.0f) / ((float) this.h), (((float) i6) * 1.0f) / ((float) this.i), this.l, this.m);
        a();
    }

    public void setBounds(@NonNull Rect rect) {
        setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void a(String... strArr) {
        int i = 0;
        this.i = 0;
        this.h = 0;
        this.m = new ArrayList();
        List arrayList = new ArrayList();
        this.l = arrayList;
        this.b = arrayList;
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            this.m.add(str);
            this.l.add(a.a(str));
            i++;
        }
        a();
    }

    public void a(int... iArr) {
        this.c = new ArrayList();
        for (int valueOf : iArr) {
            this.c.add(Integer.valueOf(valueOf));
        }
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (this.a.getAlpha() == 255) {
            canvas.save();
            canvas.translate((float) (bounds.left - this.f), (float) (bounds.top - this.g));
            if (this.b != null) {
                width = 0;
                while (width < this.b.size()) {
                    if (this.c != null && width < this.c.size()) {
                        this.a.setColor(((Integer) this.c.get(width)).intValue());
                    }
                    canvas.drawPath((Path) this.b.get(width), this.a);
                    width++;
                }
                this.a.setAlpha(255);
            }
            canvas.restore();
            return;
        }
        b(width, height);
        if (!b()) {
            a(width, height);
            c();
        }
        canvas.drawBitmap(this.n, (float) bounds.left, (float) bounds.top, this.a);
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    public void a(int i, int i2) {
        this.n.eraseColor(0);
        a(new Canvas(this.n));
    }

    private void a(Canvas canvas) {
        canvas.translate((float) (-this.f), (float) (-this.g));
        if (this.b != null) {
            int i = 0;
            while (i < this.b.size()) {
                if (this.c != null && i < this.c.size()) {
                    this.a.setColor(((Integer) this.c.get(i)).intValue());
                }
                canvas.drawPath((Path) this.b.get(i), this.a);
                i++;
            }
        }
    }

    public void b(int i, int i2) {
        if (this.n == null || !c(i, i2)) {
            this.n = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.o = true;
        }
    }

    public boolean c(int i, int i2) {
        if (i == this.n.getWidth() && i2 == this.n.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean b() {
        if (this.o) {
            return false;
        }
        return true;
    }

    public void c() {
        this.o = false;
    }
}
