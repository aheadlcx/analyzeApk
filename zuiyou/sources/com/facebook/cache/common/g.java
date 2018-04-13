package com.facebook.cache.common;

import android.net.Uri;

public class g implements b {
    final String a;

    public g(String str) {
        this.a = (String) com.facebook.common.internal.g.a((Object) str);
    }

    public String toString() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        return this.a.equals(((g) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean a(Uri uri) {
        return this.a.contains(uri.toString());
    }

    public String a() {
        return this.a;
    }
}
