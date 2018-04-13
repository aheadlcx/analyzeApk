package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.EofSensorInputStream;
import cz.msebera.android.httpclient.conn.EofSensorWatcher;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@NotThreadSafe
class d extends HttpEntityWrapper implements EofSensorWatcher {
    private final a a;

    public static void enchance(HttpResponse httpResponse, a aVar) {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && entity.isStreaming() && aVar != null) {
            httpResponse.setEntity(new d(entity, aVar));
        }
    }

    d(HttpEntity httpEntity, a aVar) {
        super(httpEntity);
        this.a = aVar;
    }

    private void a() {
        if (this.a != null) {
            this.a.abortConnection();
        }
    }

    public void releaseConnection() throws IOException {
        if (this.a != null) {
            try {
                if (this.a.isReusable()) {
                    this.a.releaseConnection();
                }
                a();
            } catch (Throwable th) {
                a();
            }
        }
    }

    public boolean isRepeatable() {
        return false;
    }

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.c.getContent(), this);
    }

    @Deprecated
    public void consumeContent() throws IOException {
        releaseConnection();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            this.c.writeTo(outputStream);
            releaseConnection();
        } finally {
            a();
        }
    }

    public boolean eofDetected(InputStream inputStream) throws IOException {
        try {
            inputStream.close();
            releaseConnection();
            return false;
        } finally {
            a();
        }
    }

    public boolean streamClosed(InputStream inputStream) throws IOException {
        boolean z;
        try {
            z = (this.a == null || this.a.isReleased()) ? false : true;
            inputStream.close();
            releaseConnection();
        } catch (SocketException e) {
            if (z) {
                throw e;
            }
        } catch (Throwable th) {
            a();
        }
        a();
        return false;
    }

    public boolean streamAbort(InputStream inputStream) throws IOException {
        a();
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ResponseEntityProxy{");
        stringBuilder.append(this.c);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
