package com.facebook.common.memory;

import com.facebook.common.internal.k;
import java.io.OutputStream;

public abstract class i extends OutputStream {
    public abstract int size();

    public abstract PooledByteBuffer toByteBuffer();

    public void close() {
        try {
            super.close();
        } catch (Throwable e) {
            k.b(e);
        }
    }
}
