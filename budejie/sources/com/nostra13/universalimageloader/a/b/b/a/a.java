package com.nostra13.universalimageloader.a.b.b.a;

import java.util.Collection;
import java.util.Comparator;
import pl.droidsonroids.gif.GifDrawable;

public class a implements com.nostra13.universalimageloader.a.b.b.a {
    private final com.nostra13.universalimageloader.a.b.b.a a;
    private final Comparator<String> b;

    public a(com.nostra13.universalimageloader.a.b.b.a aVar, Comparator<String> comparator) {
        this.a = aVar;
        this.b = comparator;
    }

    public boolean a(String str, GifDrawable gifDrawable) {
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
        return this.a.a(str, gifDrawable);
    }

    public GifDrawable a(String str) {
        return (GifDrawable) this.a.a(str);
    }

    public GifDrawable b(String str) {
        return (GifDrawable) this.a.b(str);
    }

    public void b() {
        this.a.b();
    }

    public Collection<String> a() {
        return this.a.a();
    }
}
