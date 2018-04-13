package com.facebook.imagepipeline.i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.os.MemoryFile;
import com.facebook.common.internal.b;
import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.h;
import com.facebook.common.references.a;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public class c extends b {
    private static Method b;

    protected Bitmap a(a<PooledByteBuffer> aVar, Options options) {
        return a(aVar, ((PooledByteBuffer) aVar.a()).size(), null, options);
    }

    protected Bitmap a(a<PooledByteBuffer> aVar, int i, Options options) {
        return a(aVar, i, b.a((a) aVar, i) ? null : a, options);
    }

    private static MemoryFile a(a<PooledByteBuffer> aVar, int i, @Nullable byte[] bArr) throws IOException {
        InputStream aVar2;
        Closeable outputStream;
        Throwable th;
        InputStream inputStream = null;
        MemoryFile memoryFile = new MemoryFile(null, (bArr == null ? 0 : bArr.length) + i);
        memoryFile.allowPurging(false);
        try {
            InputStream hVar = new h((PooledByteBuffer) aVar.a());
            try {
                aVar2 = new com.facebook.common.f.a(hVar, i);
                try {
                    outputStream = memoryFile.getOutputStream();
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                    inputStream = aVar2;
                    aVar2 = hVar;
                    a.c(aVar);
                    b.a(aVar2);
                    b.a(inputStream);
                    b.a(outputStream, true);
                    throw th;
                }
                try {
                    com.facebook.common.internal.a.a(aVar2, outputStream);
                    if (bArr != null) {
                        memoryFile.writeBytes(bArr, 0, i, bArr.length);
                    }
                    a.c(aVar);
                    b.a(hVar);
                    b.a(aVar2);
                    b.a(outputStream, true);
                    return memoryFile;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = aVar2;
                    aVar2 = hVar;
                    a.c(aVar);
                    b.a(aVar2);
                    b.a(inputStream);
                    b.a(outputStream, true);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                outputStream = null;
                aVar2 = hVar;
                a.c(aVar);
                b.a(aVar2);
                b.a(inputStream);
                b.a(outputStream, true);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            aVar2 = null;
            a.c(aVar);
            b.a(aVar2);
            b.a(inputStream);
            b.a(outputStream, true);
            throw th;
        }
    }

    private synchronized Method a() {
        if (b == null) {
            try {
                b = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Throwable e) {
                throw k.b(e);
            }
        }
        return b;
    }

    private FileDescriptor a(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) a().invoke(memoryFile, new Object[0]);
        } catch (Throwable e) {
            throw k.b(e);
        }
    }

    protected Bitmap a(a<PooledByteBuffer> aVar, int i, byte[] bArr, Options options) {
        MemoryFile memoryFile = null;
        try {
            memoryFile = a((a) aVar, i, bArr);
            Bitmap bitmap = (Bitmap) g.a(com.facebook.common.g.c.d.a(a(memoryFile), null, options), (Object) "BitmapFactory returned null");
            if (memoryFile != null) {
                memoryFile.close();
            }
            return bitmap;
        } catch (Throwable e) {
            throw k.b(e);
        } catch (Throwable th) {
            if (memoryFile != null) {
                memoryFile.close();
            }
        }
    }
}
