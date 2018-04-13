package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import com.bumptech.glide.i.d;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.j;
import java.io.OutputStream;

public class b implements e<Bitmap> {
    private CompressFormat a;
    private int b;

    public b() {
        this(null, 90);
    }

    public b(CompressFormat compressFormat, int i) {
        this.a = compressFormat;
        this.b = i;
    }

    public boolean a(j<Bitmap> jVar, OutputStream outputStream) {
        Bitmap bitmap = (Bitmap) jVar.b();
        long a = d.a();
        CompressFormat a2 = a(bitmap);
        bitmap.compress(a2, this.b, outputStream);
        if (Log.isLoggable("BitmapEncoder", 2)) {
            Log.v("BitmapEncoder", "Compressed with type: " + a2 + " of size " + h.a(bitmap) + " in " + d.a(a));
        }
        return true;
    }

    public String a() {
        return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
    }

    private CompressFormat a(Bitmap bitmap) {
        if (this.a != null) {
            return this.a;
        }
        if (bitmap.hasAlpha()) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }
}
