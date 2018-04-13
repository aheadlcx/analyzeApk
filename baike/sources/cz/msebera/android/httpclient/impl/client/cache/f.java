package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

@Immutable
class f implements HttpEntity, Serializable {
    private final HttpCacheEntry a;

    public f(HttpCacheEntry httpCacheEntry) {
        this.a = httpCacheEntry;
    }

    public Header getContentType() {
        return this.a.getFirstHeader("Content-Type");
    }

    public Header getContentEncoding() {
        return this.a.getFirstHeader("Content-Encoding");
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return this.a.getResource().length();
    }

    public InputStream getContent() throws IOException {
        return this.a.getResource().getInputStream();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        InputStream inputStream = this.a.getResource().getInputStream();
        try {
            s.a(inputStream, outputStream);
        } finally {
            inputStream.close();
        }
    }

    public boolean isStreaming() {
        return false;
    }

    public void consumeContent() throws IOException {
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
