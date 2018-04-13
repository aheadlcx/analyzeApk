package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

@NotThreadSafe
public class SerializableEntity extends AbstractHttpEntity {
    private byte[] d;
    private Serializable e;

    public SerializableEntity(Serializable serializable, boolean z) throws IOException {
        Args.notNull(serializable, "Source object");
        if (z) {
            a(serializable);
        } else {
            this.e = serializable;
        }
    }

    public SerializableEntity(Serializable serializable) {
        Args.notNull(serializable, "Source object");
        this.e = serializable;
    }

    private void a(Serializable serializable) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.flush();
        this.d = byteArrayOutputStream.toByteArray();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        if (this.d == null) {
            a(this.e);
        }
        return new ByteArrayInputStream(this.d);
    }

    public long getContentLength() {
        if (this.d == null) {
            return -1;
        }
        return (long) this.d.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.d == null;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        if (this.d == null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this.e);
            objectOutputStream.flush();
            return;
        }
        outputStream.write(this.d);
        outputStream.flush();
    }
}
