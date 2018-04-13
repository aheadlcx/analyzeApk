package com.facebook.imagepipeline.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.c.b;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.a;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(11)
@ThreadSafe
public class e extends f {
    private final b a;
    private final com.facebook.imagepipeline.i.e b;

    public e(b bVar, com.facebook.imagepipeline.i.e eVar) {
        this.a = bVar;
        this.b = eVar;
    }

    @TargetApi(12)
    public a<Bitmap> a(int i, int i2, Config config) {
        com.facebook.imagepipeline.g.e eVar;
        a a = this.a.a((short) i, (short) i2);
        try {
            eVar = new com.facebook.imagepipeline.g.e(a);
            eVar.a(b.a);
            a<Bitmap> a2 = this.b.a(eVar, config, ((PooledByteBuffer) a.a()).size());
            ((Bitmap) a2.a()).setHasAlpha(true);
            ((Bitmap) a2.a()).eraseColor(0);
            com.facebook.imagepipeline.g.e.d(eVar);
            a.close();
            return a2;
        } catch (Throwable th) {
            a.close();
        }
    }
}
