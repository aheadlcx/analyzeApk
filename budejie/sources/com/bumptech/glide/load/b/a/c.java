package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;

public class c extends n<ParcelFileDescriptor> implements b<Integer> {

    public static class a implements m<Integer, ParcelFileDescriptor> {
        public l<Integer, ParcelFileDescriptor> a(Context context, com.bumptech.glide.load.b.c cVar) {
            return new c(context, cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public c(Context context, l<Uri, ParcelFileDescriptor> lVar) {
        super(context, (l) lVar);
    }
}
