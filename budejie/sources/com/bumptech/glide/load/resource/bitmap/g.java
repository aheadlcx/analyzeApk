package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.f.b;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import java.io.File;

public class g implements b<ParcelFileDescriptor, Bitmap> {
    private final d<File, Bitmap> a;
    private final h b;
    private final b c = new b();
    private final a<ParcelFileDescriptor> d = com.bumptech.glide.load.resource.a.b();

    public g(c cVar, DecodeFormat decodeFormat) {
        this.a = new com.bumptech.glide.load.resource.b.c(new o(cVar, decodeFormat));
        this.b = new h(cVar, decodeFormat);
    }

    public d<File, Bitmap> a() {
        return this.a;
    }

    public d<ParcelFileDescriptor, Bitmap> b() {
        return this.b;
    }

    public a<ParcelFileDescriptor> c() {
        return this.d;
    }

    public e<Bitmap> d() {
        return this.c;
    }
}
