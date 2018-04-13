package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.p;
import java.io.InputStream;

public class f extends p<InputStream> implements d<String> {

    public static class a implements m<String, InputStream> {
        public l<String, InputStream> a(Context context, c cVar) {
            return new f(cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public f(l<Uri, InputStream> lVar) {
        super(lVar);
    }
}
