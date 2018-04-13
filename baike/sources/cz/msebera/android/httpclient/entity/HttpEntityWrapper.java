package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
public class HttpEntityWrapper implements HttpEntity {
    protected HttpEntity c;

    public HttpEntityWrapper(HttpEntity httpEntity) {
        this.c = (HttpEntity) Args.notNull(httpEntity, "Wrapped entity");
    }

    public boolean isRepeatable() {
        return this.c.isRepeatable();
    }

    public boolean isChunked() {
        return this.c.isChunked();
    }

    public long getContentLength() {
        return this.c.getContentLength();
    }

    public Header getContentType() {
        return this.c.getContentType();
    }

    public Header getContentEncoding() {
        return this.c.getContentEncoding();
    }

    public InputStream getContent() throws IOException {
        return this.c.getContent();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.c.writeTo(outputStream);
    }

    public boolean isStreaming() {
        return this.c.isStreaming();
    }

    @Deprecated
    public void consumeContent() throws IOException {
        this.c.consumeContent();
    }
}
