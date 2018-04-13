package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class f implements HttpEntity {
    private final a a;
    private final Header b;
    private final long c;

    f(a aVar, ContentType contentType, long j) {
        this.a = aVar;
        this.b = new BasicHeader("Content-Type", contentType.toString());
        this.c = j;
    }

    public boolean isRepeatable() {
        return this.c != -1;
    }

    public boolean isChunked() {
        return !isRepeatable();
    }

    public boolean isStreaming() {
        return !isRepeatable();
    }

    public long getContentLength() {
        return this.c;
    }

    public Header getContentType() {
        return this.b;
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException {
        throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.a.writeTo(outputStream);
    }
}
