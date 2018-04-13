package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.util.Args;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

@NotThreadSafe
class p extends AbstractHttpEntity {
    private final Resource d;
    private final InputStream e;

    class a extends FilterInputStream {
        final /* synthetic */ p a;

        protected a(p pVar, InputStream inputStream) {
            this.a = pVar;
            super(inputStream);
        }

        public void close() throws IOException {
            try {
                super.close();
            } finally {
                this.a.a();
            }
        }
    }

    p(Resource resource, InputStream inputStream) throws IOException {
        this.d = resource;
        this.e = new SequenceInputStream(new a(this, resource.getInputStream()), inputStream);
    }

    public long getContentLength() {
        return -1;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.e;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[2048];
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

    private void a() {
        this.d.dispose();
    }
}
