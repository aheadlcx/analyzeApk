package com.facebook.cache.common;

import android.net.Uri;
import java.util.List;

public class d implements b {
    final List<b> a;

    public List<b> b() {
        return this.a;
    }

    public String toString() {
        return "MultiCacheKey:" + this.a.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        return this.a.equals(((d) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean a(Uri uri) {
        for (int i = 0; i < this.a.size(); i++) {
            if (((b) this.a.get(i)).a(uri)) {
                return true;
            }
        }
        return false;
    }

    public String a() {
        return ((b) this.a.get(0)).a();
    }
}
