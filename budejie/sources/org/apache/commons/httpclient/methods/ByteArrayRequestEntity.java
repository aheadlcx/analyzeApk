package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayRequestEntity implements RequestEntity {
    private byte[] content;
    private String contentType;

    public ByteArrayRequestEntity(byte[] bArr) {
        this(bArr, null);
    }

    public ByteArrayRequestEntity(byte[] bArr, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException("The content cannot be null");
        }
        this.content = bArr;
        this.contentType = str;
    }

    public boolean isRepeatable() {
        return true;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void writeRequest(OutputStream outputStream) throws IOException {
        outputStream.write(this.content);
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public byte[] getContent() {
        return this.content;
    }
}
