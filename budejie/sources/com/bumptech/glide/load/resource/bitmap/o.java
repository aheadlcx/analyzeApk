package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import java.io.InputStream;

public class o implements d<InputStream, Bitmap> {
    private final f a;
    private c b;
    private DecodeFormat c;
    private String d;

    public o(c cVar, DecodeFormat decodeFormat) {
        this(f.a, cVar, decodeFormat);
    }

    public o(f fVar, c cVar, DecodeFormat decodeFormat) {
        this.a = fVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public j<Bitmap> a(InputStream inputStream, int i, int i2) {
        return c.a(this.a.a(inputStream, this.b, i, i2, this.c), this.b);
    }

    public String a() {
        if (this.d == null) {
            this.d = "StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap" + this.a.a() + this.c.name();
        }
        return this.d;
    }
}
