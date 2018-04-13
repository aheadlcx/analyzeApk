package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.impl.io.EmptyInputStream;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
public class BasicHttpEntity extends AbstractHttpEntity {
    private InputStream d;
    private long e = -1;

    public long getContentLength() {
        return this.e;
    }

    public InputStream getContent() throws IllegalStateException {
        Asserts.check(this.d != null, "Content has not been provided");
        return this.d;
    }

    public boolean isRepeatable() {
        return false;
    }

    public void setContentLength(long j) {
        this.e = j;
    }

    public void setContent(InputStream inputStream) {
        this.d = inputStream;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            content.close();
        }
    }

    public boolean isStreaming() {
        return (this.d == null || this.d == EmptyInputStream.INSTANCE) ? false : true;
    }
}
