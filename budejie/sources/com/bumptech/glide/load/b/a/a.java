package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.b;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.File;

public class a extends b<ParcelFileDescriptor> implements b<File> {

    public static class a implements m<File, ParcelFileDescriptor> {
        public l<File, ParcelFileDescriptor> a(Context context, c cVar) {
            return new a(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public a(l<Uri, ParcelFileDescriptor> lVar) {
        super(lVar);
    }
}
