package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.common.f.b;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.g.e;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class a implements e {
    private static final byte[] c = new byte[]{(byte) -1, (byte) -39};
    final SynchronizedPool<ByteBuffer> a;
    private final com.facebook.imagepipeline.memory.a b;

    public a(com.facebook.imagepipeline.memory.a aVar, int i, SynchronizedPool synchronizedPool) {
        this.b = aVar;
        this.a = synchronizedPool;
        for (int i2 = 0; i2 < i; i2++) {
            this.a.release(ByteBuffer.allocate(16384));
        }
    }

    public com.facebook.common.references.a<Bitmap> a(e eVar, Config config) {
        Options b = b(eVar, config);
        Object obj = b.inPreferredConfig != Config.ARGB_8888 ? 1 : null;
        try {
            return a(eVar.d(), b);
        } catch (RuntimeException e) {
            if (obj != null) {
                return a(eVar, Config.ARGB_8888);
            }
            throw e;
        }
    }

    public com.facebook.common.references.a<Bitmap> a(e eVar, Config config, int i) {
        InputStream aVar;
        boolean e = eVar.e(i);
        Options b = b(eVar, config);
        InputStream d = eVar.d();
        g.a((Object) d);
        if (eVar.k() > i) {
            aVar = new com.facebook.common.f.a(d, i);
        } else {
            aVar = d;
        }
        if (e) {
            d = aVar;
        } else {
            d = new b(aVar, c);
        }
        Object obj = b.inPreferredConfig != Config.ARGB_8888 ? 1 : null;
        try {
            return a(d, b);
        } catch (RuntimeException e2) {
            if (obj != null) {
                return a(eVar, Config.ARGB_8888);
            }
            throw e2;
        }
    }

    protected com.facebook.common.references.a<Bitmap> a(InputStream inputStream, Options options) {
        ByteBuffer allocate;
        g.a((Object) inputStream);
        Bitmap bitmap = (Bitmap) this.b.get(com.facebook.d.a.a(options.outWidth, options.outHeight, options.inPreferredConfig));
        if (bitmap == null) {
            throw new NullPointerException("BitmapPool.get returned null");
        }
        options.inBitmap = bitmap;
        ByteBuffer byteBuffer = (ByteBuffer) this.a.acquire();
        if (byteBuffer == null) {
            allocate = ByteBuffer.allocate(16384);
        } else {
            allocate = byteBuffer;
        }
        try {
            options.inTempStorage = allocate.array();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
            this.a.release(allocate);
            if (bitmap == decodeStream) {
                return com.facebook.common.references.a.a(decodeStream, this.b);
            }
            this.b.release(bitmap);
            decodeStream.recycle();
            throw new IllegalStateException();
        } catch (RuntimeException e) {
            this.b.release(bitmap);
            throw e;
        } catch (Throwable th) {
            this.a.release(allocate);
        }
    }

    private static Options b(e eVar, Config config) {
        Options options = new Options();
        options.inSampleSize = eVar.i();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(eVar.d(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }
}
