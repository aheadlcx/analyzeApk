package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.a.h;
import com.bumptech.glide.load.a.i;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.d;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.q;
import java.io.InputStream;

public class g extends q<InputStream> implements d<Uri> {

    public static class a implements m<Uri, InputStream> {
        public l<Uri, InputStream> a(Context context, c cVar) {
            return new g(context, cVar.a(d.class, InputStream.class));
        }

        public void a() {
        }
    }

    public g(Context context, l<d, InputStream> lVar) {
        super(context, lVar);
    }

    protected com.bumptech.glide.load.a.c<InputStream> a(Context context, Uri uri) {
        return new i(context, uri);
    }

    protected com.bumptech.glide.load.a.c<InputStream> a(Context context, String str) {
        return new h(context.getApplicationContext().getAssets(), str);
    }
}
