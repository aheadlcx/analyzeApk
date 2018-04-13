package com.facebook.stetho.urlconnection;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayRequestEntity implements SimpleRequestEntity {
    private final byte[] mData;

    public ByteArrayRequestEntity(byte[] bArr) {
        this.mData = bArr;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.mData);
    }
}
