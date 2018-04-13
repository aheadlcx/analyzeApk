package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import java.io.InputStream;

public class e extends n<InputStream> implements d<Integer> {

    public static class a implements m<Integer, InputStream> {
        public l<Integer, InputStream> a(Context context, c cVar) {
            return new e(context, cVar.a(Uri.class, InputStream.class));
        }

        public void a() {
        }
    }

    public e(Context context, l<Uri, InputStream> lVar) {
        super(context, (l) lVar);
    }
}
