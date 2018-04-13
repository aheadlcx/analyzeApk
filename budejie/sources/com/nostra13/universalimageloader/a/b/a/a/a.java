package com.nostra13.universalimageloader.a.b.a.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.a.b.a.c;
import java.util.Collection;
import java.util.Comparator;

public class a implements c {
    private final c a;
    private final Comparator<String> b;

    public a(c cVar, Comparator<String> comparator) {
        this.a = cVar;
        this.b = comparator;
    }

    public boolean a(String str, Bitmap bitmap) {
        synchronized (this.a) {
            for (Object obj : this.a.a()) {
                if (this.b.compare(str, obj) == 0) {
                    break;
                }
            }
            Object obj2 = null;
            if (obj2 != null) {
                this.a.b(obj2);
            }
        }
        return this.a.a(str, bitmap);
    }

    public Bitmap a(String str) {
        return (Bitmap) this.a.a(str);
    }

    public Bitmap b(String str) {
        return (Bitmap) this.a.b(str);
    }

    public void b() {
        this.a.b();
    }

    public Collection<String> a() {
        return this.a.a();
    }
}
