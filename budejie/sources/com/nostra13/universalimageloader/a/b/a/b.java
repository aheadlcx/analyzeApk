package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import android.support.v4.view.ViewCompat;
import com.nostra13.universalimageloader.b.e;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class b extends a {
    private final int a;
    private final AtomicInteger b;
    private final List<Bitmap> c = Collections.synchronizedList(new LinkedList());

    protected abstract int b(Bitmap bitmap);

    protected abstract Bitmap d();

    public b(int i) {
        this.a = i;
        this.b = new AtomicInteger();
        if (i > ViewCompat.MEASURED_STATE_TOO_SMALL) {
            e.c("You set too large memory cache size (more than %1$d Mb)", new Object[]{Integer.valueOf(16)});
        }
    }

    public boolean a(String str, Bitmap bitmap) {
        boolean z = false;
        int b = b(bitmap);
        int c = c();
        int i = this.b.get();
        if (b < c) {
            int i2 = i;
            while (i2 + b > c) {
                Bitmap d = d();
                if (this.c.remove(d)) {
                    i2 = this.b.addAndGet(-b(d));
                }
            }
            this.c.add(bitmap);
            this.b.addAndGet(b);
            z = true;
        }
        super.a(str, bitmap);
        return z;
    }

    public Bitmap b(String str) {
        Bitmap a = super.a(str);
        if (a != null && this.c.remove(a)) {
            this.b.addAndGet(-b(a));
        }
        return super.b(str);
    }

    public void b() {
        this.c.clear();
        this.b.set(0);
        super.b();
    }

    protected int c() {
        return this.a;
    }
}
