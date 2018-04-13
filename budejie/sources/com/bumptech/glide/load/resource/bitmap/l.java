package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;
import java.io.InputStream;

public class l implements d<g, Bitmap> {
    private final d<InputStream, Bitmap> a;
    private final d<ParcelFileDescriptor, Bitmap> b;

    public l(d<InputStream, Bitmap> dVar, d<ParcelFileDescriptor, Bitmap> dVar2) {
        this.a = dVar;
        this.b = dVar2;
    }

    public j<Bitmap> a(g gVar, int i, int i2) throws IOException {
        j<Bitmap> a;
        ParcelFileDescriptor b;
        InputStream a2 = gVar.a();
        if (a2 != null) {
            try {
                a = this.a.a(a2, i, i2);
            } catch (Throwable e) {
                if (Log.isLoggable("ImageVideoDecoder", 2)) {
                    Log.v("ImageVideoDecoder", "Failed to load image from stream, trying FileDescriptor", e);
                }
            }
            if (a == null) {
                return a;
            }
            b = gVar.b();
            if (b == null) {
                return this.b.a(b, i, i2);
            }
            return a;
        }
        a = null;
        if (a == null) {
            return a;
        }
        b = gVar.b();
        if (b == null) {
            return a;
        }
        return this.b.a(b, i, i2);
    }

    public String a() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
