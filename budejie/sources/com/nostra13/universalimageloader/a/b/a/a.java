package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class a implements c {
    private final Map<String, Reference<Bitmap>> a = Collections.synchronizedMap(new HashMap());

    protected abstract Reference<Bitmap> a(Bitmap bitmap);

    public Bitmap a(String str) {
        Reference reference = (Reference) this.a.get(str);
        if (reference != null) {
            return (Bitmap) reference.get();
        }
        return null;
    }

    public boolean a(String str, Bitmap bitmap) {
        this.a.put(str, a(bitmap));
        return true;
    }

    public Bitmap b(String str) {
        Reference reference = (Reference) this.a.remove(str);
        return reference == null ? null : (Bitmap) reference.get();
    }

    public Collection<String> a() {
        Collection hashSet;
        synchronized (this.a) {
            hashSet = new HashSet(this.a.keySet());
        }
        return hashSet;
    }

    public void b() {
        this.a.clear();
    }
}
