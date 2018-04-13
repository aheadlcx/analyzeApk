package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.b;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.File;
import java.io.InputStream;

public class c extends b<InputStream> implements d<File> {

    public static class a implements m<File, InputStream> {
        public l<File, InputStream> a(Context context, com.bumptech.glide.load.b.c cVar) {
            return new c(cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public c(l<Uri, InputStream> lVar) {
        super(lVar);
    }
}
