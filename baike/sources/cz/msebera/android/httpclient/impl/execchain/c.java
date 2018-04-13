package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
class c implements HttpEntity {
    private final HttpEntity a;
    private boolean b = false;

    static void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        HttpEntity entity = httpEntityEnclosingRequest.getEntity();
        if (entity != null && !entity.isRepeatable() && !a(entity)) {
            httpEntityEnclosingRequest.setEntity(new c(entity));
        }
    }

    static boolean a(HttpEntity httpEntity) {
        return httpEntity instanceof c;
    }

    static boolean a(HttpRequest httpRequest) {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            if (entity != null) {
                if (!a(entity) || ((c) entity).isConsumed()) {
                    return entity.isRepeatable();
                }
                return true;
            }
        }
        return true;
    }

    c(HttpEntity httpEntity) {
        this.a = httpEntity;
    }

    public HttpEntity getOriginal() {
        return this.a;
    }

    public boolean isConsumed() {
        return this.b;
    }

    public boolean isRepeatable() {
        return this.a.isRepeatable();
    }

    public boolean isChunked() {
        return this.a.isChunked();
    }

    public long getContentLength() {
        return this.a.getContentLength();
    }

    public Header getContentType() {
        return this.a.getContentType();
    }

    public Header getContentEncoding() {
        return this.a.getContentEncoding();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.a.getContent();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.b = true;
        this.a.writeTo(outputStream);
    }

    public boolean isStreaming() {
        return this.a.isStreaming();
    }

    @Deprecated
    public void consumeContent() throws IOException {
        this.b = true;
        this.a.consumeContent();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("RequestEntityProxy{");
        stringBuilder.append(this.a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
