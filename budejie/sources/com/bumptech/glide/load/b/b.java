package com.bumptech.glide.load.b;

import android.net.Uri;
import com.bumptech.glide.load.a.c;
import java.io.File;

public class b<T> implements l<File, T> {
    private final l<Uri, T> a;

    public b(l<Uri, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(File file, int i, int i2) {
        return this.a.a(Uri.fromFile(file), i, i2);
    }
}
