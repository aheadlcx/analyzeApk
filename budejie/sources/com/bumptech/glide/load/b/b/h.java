package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.d;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.r;
import java.io.InputStream;
import java.net.URL;

public class h extends r<InputStream> {

    public static class a implements m<URL, InputStream> {
        public l<URL, InputStream> a(Context context, c cVar) {
            return new h(cVar.a(d.class, InputStream.class));
        }

        public void a() {
        }
    }

    public h(l<d, InputStream> lVar) {
        super(lVar);
    }
}
