package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;

public class h implements d<ParcelFileDescriptor, Bitmap> {
    private final q a;
    private final c b;
    private DecodeFormat c;

    public h(c cVar, DecodeFormat decodeFormat) {
        this(new q(), cVar, decodeFormat);
    }

    public h(q qVar, c cVar, DecodeFormat decodeFormat) {
        this.a = qVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public j<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws IOException {
        return c.a(this.a.a(parcelFileDescriptor, this.b, i, i2, this.c), this.b);
    }

    public String a() {
        return "FileDescriptorBitmapDecoder.com.bumptech.glide.load.data.bitmap";
    }
}
