package com.facebook.common.references;

import com.facebook.common.internal.Closeables;
import java.io.Closeable;
import java.io.IOException;

class CloseableReference$1 implements ResourceReleaser<Closeable> {
    CloseableReference$1() {
    }

    public void release(Closeable closeable) {
        try {
            Closeables.close(closeable, true);
        } catch (IOException e) {
        }
    }
}
