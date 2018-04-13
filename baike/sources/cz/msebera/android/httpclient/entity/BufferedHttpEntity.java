package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] a;

    public BufferedHttpEntity(HttpEntity httpEntity) throws IOException {
        super(httpEntity);
        if (!httpEntity.isRepeatable() || httpEntity.getContentLength() < 0) {
            this.a = EntityUtils.toByteArray(httpEntity);
        } else {
            this.a = null;
        }
    }

    public long getContentLength() {
        if (this.a != null) {
            return (long) this.a.length;
        }
        return super.getContentLength();
    }

    public InputStream getContent() throws IOException {
        if (this.a != null) {
            return new ByteArrayInputStream(this.a);
        }
        return super.getContent();
    }

    public boolean isChunked() {
        return this.a == null && super.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        if (this.a != null) {
            outputStream.write(this.a);
        } else {
            super.writeTo(outputStream);
        }
    }

    public boolean isStreaming() {
        return this.a == null && super.isStreaming();
    }
}
