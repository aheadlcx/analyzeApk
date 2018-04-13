package com.facebook.common.memory;

import java.io.IOException;
import java.io.InputStream;

public interface g {
    PooledByteBuffer newByteBuffer(InputStream inputStream) throws IOException;

    PooledByteBuffer newByteBuffer(InputStream inputStream, int i) throws IOException;

    PooledByteBuffer newByteBuffer(byte[] bArr);

    i newOutputStream();

    i newOutputStream(int i);
}
