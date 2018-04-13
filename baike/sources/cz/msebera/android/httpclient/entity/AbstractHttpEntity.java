package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.IOException;

@NotThreadSafe
public abstract class AbstractHttpEntity implements HttpEntity {
    protected Header a;
    protected Header b;
    protected boolean c;

    protected AbstractHttpEntity() {
    }

    public Header getContentType() {
        return this.a;
    }

    public Header getContentEncoding() {
        return this.b;
    }

    public boolean isChunked() {
        return this.c;
    }

    public void setContentType(Header header) {
        this.a = header;
    }

    public void setContentType(String str) {
        Header header = null;
        if (str != null) {
            header = new BasicHeader("Content-Type", str);
        }
        setContentType(header);
    }

    public void setContentEncoding(Header header) {
        this.b = header;
    }

    public void setContentEncoding(String str) {
        Header header = null;
        if (str != null) {
            header = new BasicHeader("Content-Encoding", str);
        }
        setContentEncoding(header);
    }

    public void setChunked(boolean z) {
        this.c = z;
    }

    @Deprecated
    public void consumeContent() throws IOException {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        if (this.a != null) {
            stringBuilder.append("Content-Type: ");
            stringBuilder.append(this.a.getValue());
            stringBuilder.append(',');
        }
        if (this.b != null) {
            stringBuilder.append("Content-Encoding: ");
            stringBuilder.append(this.b.getValue());
            stringBuilder.append(',');
        }
        long contentLength = getContentLength();
        if (contentLength >= 0) {
            stringBuilder.append("Content-Length: ");
            stringBuilder.append(contentLength);
            stringBuilder.append(',');
        }
        stringBuilder.append("Chunked: ");
        stringBuilder.append(this.c);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
