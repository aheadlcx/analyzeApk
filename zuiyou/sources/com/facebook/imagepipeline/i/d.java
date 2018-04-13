package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.facebook.common.internal.g;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(19)
@ThreadSafe
public class d extends b {
    private final FlexByteArrayPool b;

    public /* bridge */ /* synthetic */ a a(Bitmap bitmap) {
        return super.a(bitmap);
    }

    public d(FlexByteArrayPool flexByteArrayPool) {
        this.b = flexByteArrayPool;
    }

    protected Bitmap a(a<PooledByteBuffer> aVar, Options options) {
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) aVar.a();
        int size = pooledByteBuffer.size();
        a aVar2 = this.b.get(size);
        try {
            byte[] bArr = (byte[]) aVar2.a();
            pooledByteBuffer.read(0, bArr, 0, size);
            Bitmap bitmap = (Bitmap) g.a(BitmapFactory.decodeByteArray(bArr, 0, size, options), (Object) "BitmapFactory returned null");
            return bitmap;
        } finally {
            a.c(aVar2);
        }
    }

    protected Bitmap a(a<PooledByteBuffer> aVar, int i, Options options) {
        boolean z = false;
        byte[] bArr = b.a((a) aVar, i) ? null : a;
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) aVar.a();
        if (i <= pooledByteBuffer.size()) {
            z = true;
        }
        g.a(z);
        a aVar2 = this.b.get(i + 2);
        try {
            byte[] bArr2 = (byte[]) aVar2.a();
            pooledByteBuffer.read(0, bArr2, 0, i);
            if (bArr != null) {
                a(bArr2, i);
                i += 2;
            }
            Bitmap bitmap = (Bitmap) g.a(BitmapFactory.decodeByteArray(bArr2, 0, i, options), (Object) "BitmapFactory returned null");
            return bitmap;
        } finally {
            a.c(aVar2);
        }
    }

    private static void a(byte[] bArr, int i) {
        bArr[i] = (byte) -1;
        bArr[i + 1] = (byte) -39;
    }
}
