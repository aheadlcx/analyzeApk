package com.bumptech.glide.load.b;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.a.c;
import java.io.File;

public class p<T> implements l<String, T> {
    private final l<Uri, T> a;

    public p(l<Uri, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Object a;
        if (str.startsWith("/")) {
            a = a(str);
        } else {
            a = Uri.parse(str);
            if (a.getScheme() == null) {
                a = a(str);
            }
        }
        return this.a.a(a, i, i2);
    }

    private static Uri a(String str) {
        return Uri.fromFile(new File(str));
    }
}
