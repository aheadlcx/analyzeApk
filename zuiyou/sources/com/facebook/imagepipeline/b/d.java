package com.facebook.imagepipeline.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.c.b;
import com.facebook.common.g.a;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;

public class d implements a {
    private final b a;
    private final FlexByteArrayPool b;

    public d(PoolFactory poolFactory) {
        this.b = poolFactory.getFlexByteArrayPool();
        this.a = new b(poolFactory.getPooledByteBufferFactory());
    }

    @TargetApi(12)
    public Bitmap a(int i, int i2, Config config) {
        Throwable th;
        e eVar;
        com.facebook.common.references.a aVar = null;
        com.facebook.common.references.a a = this.a.a((short) i, (short) i2);
        try {
            e eVar2 = new e(a);
            try {
                eVar2.a(b.a);
                Options a2 = a(eVar2.i(), config);
                int size = ((PooledByteBuffer) a.a()).size();
                PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) a.a();
                com.facebook.common.references.a aVar2 = this.b.get(size + 2);
                try {
                    byte[] bArr = (byte[]) aVar2.a();
                    pooledByteBuffer.read(0, bArr, 0, size);
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, size, a2);
                    decodeByteArray.setHasAlpha(true);
                    decodeByteArray.eraseColor(0);
                    com.facebook.common.references.a.c(aVar2);
                    e.d(eVar2);
                    com.facebook.common.references.a.c(a);
                    return decodeByteArray;
                } catch (Throwable th2) {
                    th = th2;
                    aVar = aVar2;
                    eVar = eVar2;
                    com.facebook.common.references.a.c(aVar);
                    e.d(eVar);
                    com.facebook.common.references.a.c(a);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                eVar = eVar2;
                com.facebook.common.references.a.c(aVar);
                e.d(eVar);
                com.facebook.common.references.a.c(a);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            eVar = null;
            com.facebook.common.references.a.c(aVar);
            e.d(eVar);
            com.facebook.common.references.a.c(a);
            throw th;
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
}
