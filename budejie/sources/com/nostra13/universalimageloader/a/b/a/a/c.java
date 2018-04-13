package com.nostra13.universalimageloader.a.b.a.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.a.b.a.b;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class c extends b {
    private final Map<Bitmap, Integer> a = Collections.synchronizedMap(new HashMap());

    public c(int i) {
        super(i);
    }

    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.a.put(bitmap, Integer.valueOf(0));
        return true;
    }

    public Bitmap a(String str) {
        Bitmap a = super.a(str);
        if (a != null) {
            Integer num = (Integer) this.a.get(a);
            if (num != null) {
                this.a.put(a, Integer.valueOf(num.intValue() + 1));
            }
        }
        return a;
    }

    public Bitmap b(String str) {
        Bitmap a = super.a(str);
        if (a != null) {
            this.a.remove(a);
        }
        return super.b(str);
    }

    public void b() {
        this.a.clear();
        super.b();
    }

    protected int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    protected Bitmap d() {
        Bitmap bitmap = null;
        Set<Entry> entrySet = this.a.entrySet();
        synchronized (this.a) {
            Integer num = null;
            for (Entry entry : entrySet) {
                Bitmap bitmap2;
                Integer num2;
                if (bitmap == null) {
                    bitmap2 = (Bitmap) entry.getKey();
                    num2 = (Integer) entry.getValue();
                } else {
                    Integer num3 = (Integer) entry.getValue();
                    if (num3.intValue() < num.intValue()) {
                        Bitmap bitmap3 = (Bitmap) entry.getKey();
                        num2 = num3;
                        bitmap2 = bitmap3;
                    } else {
                        bitmap2 = bitmap;
                        num2 = num;
                    }
                }
                bitmap = bitmap2;
                num = num2;
            }
        }
        this.a.remove(bitmap);
        return bitmap;
    }

    protected Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
