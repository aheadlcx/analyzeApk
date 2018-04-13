package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Immutable
public class HeapResource implements Resource {
    private final byte[] a;

    public HeapResource(byte[] bArr) {
        this.a = bArr;
    }

    byte[] a() {
        return this.a;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.a);
    }

    public long length() {
        return (long) this.a.length;
    }

    public void dispose() {
    }
}
