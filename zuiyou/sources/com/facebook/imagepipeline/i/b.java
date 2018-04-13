package com.facebook.imagepipeline.i;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.memory.BitmapCounter;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.nativecode.Bitmaps;

abstract class b implements e {
    protected static final byte[] a = new byte[]{(byte) -1, (byte) -39};
    private final BitmapCounter b = BitmapCounterProvider.get();

    abstract Bitmap a(a<PooledByteBuffer> aVar, int i, Options options);

    abstract Bitmap a(a<PooledByteBuffer> aVar, Options options);

    b() {
    }

    public a<Bitmap> a(e eVar, Config config) {
        Options a = a(eVar.i(), config);
        a c = eVar.c();
        g.a((Object) c);
        try {
            a<Bitmap> a2 = a(a(c, a));
            return a2;
        } finally {
            a.c(c);
        }
    }

    public a<Bitmap> a(e eVar, Config config, int i) {
        Options a = a(eVar.i(), config);
        a c = eVar.c();
        g.a((Object) c);
        try {
            a<Bitmap> a2 = a(a(c, i, a));
            return a2;
        } finally {
            a.c(c);
        }
    }

    private static Options a(int i, Config config) {
        Options options = new Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        if (VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    protected static boolean a(a<PooledByteBuffer> aVar, int i) {
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) aVar.a();
        return i >= 2 && pooledByteBuffer.read(i - 2) == (byte) -1 && pooledByteBuffer.read(i - 1) == (byte) -39;
    }

    public a<Bitmap> a(Bitmap bitmap) {
        try {
            Bitmaps.a(bitmap);
            if (this.b.increase(bitmap)) {
                return a.a(bitmap, this.b.getReleaser());
            }
            bitmap.recycle();
            throw new TooManyBitmapsException();
        } catch (Throwable e) {
            bitmap.recycle();
            throw k.b(e);
        }
    }
}
