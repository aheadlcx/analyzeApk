package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import com.facebook.common.references.c;
import com.facebook.d.a;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public class BitmapCounter {
    @GuardedBy
    private int mCount;
    private final int mMaxCount;
    private final int mMaxSize;
    @GuardedBy
    private long mSize;
    private final c<Bitmap> mUnpooledBitmapsReleaser;

    public BitmapCounter(int i, int i2) {
        boolean z = true;
        g.a(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        g.a(z);
        this.mMaxCount = i;
        this.mMaxSize = i2;
        this.mUnpooledBitmapsReleaser = new c<Bitmap>() {
            public void release(Bitmap bitmap) {
                try {
                    BitmapCounter.this.decrease(bitmap);
                } finally {
                    bitmap.recycle();
                }
            }
        };
    }

    public synchronized boolean increase(Bitmap bitmap) {
        boolean z;
        int a = a.a(bitmap);
        if (this.mCount >= this.mMaxCount || this.mSize + ((long) a) > ((long) this.mMaxSize)) {
            z = false;
        } else {
            this.mCount++;
            this.mSize = ((long) a) + this.mSize;
            z = true;
        }
        return z;
    }

    public synchronized void decrease(Bitmap bitmap) {
        boolean z = true;
        synchronized (this) {
            int a = a.a(bitmap);
            g.a(this.mCount > 0, (Object) "No bitmaps registered.");
            if (((long) a) > this.mSize) {
                z = false;
            }
            g.a(z, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(a), Long.valueOf(this.mSize));
            this.mSize -= (long) a;
            this.mCount--;
        }
    }

    public synchronized int getCount() {
        return this.mCount;
    }

    public synchronized long getSize() {
        return this.mSize;
    }

    public c<Bitmap> getReleaser() {
        return this.mUnpooledBitmapsReleaser;
    }

    public List<com.facebook.common.references.a<Bitmap>> associateBitmapsWithBitmapCounter(List<Bitmap> list) {
        Bitmap bitmap;
        int i = 0;
        while (i < list.size()) {
            try {
                bitmap = (Bitmap) list.get(i);
                if (VERSION.SDK_INT < 21) {
                    Bitmaps.a(bitmap);
                }
                if (increase(bitmap)) {
                    i++;
                } else {
                    throw new TooManyBitmapsException();
                }
            } catch (Throwable e) {
                Throwable th = e;
                if (list != null) {
                    for (Bitmap bitmap2 : list) {
                        int i2 = i - 1;
                        if (i > 0) {
                            decrease(bitmap2);
                        }
                        bitmap2.recycle();
                        i = i2;
                    }
                }
                throw k.b(th);
            }
        }
        List<com.facebook.common.references.a<Bitmap>> arrayList = new ArrayList(list.size());
        for (Bitmap bitmap22 : list) {
            arrayList.add(com.facebook.common.references.a.a(bitmap22, this.mUnpooledBitmapsReleaser));
        }
        return arrayList;
    }
}
