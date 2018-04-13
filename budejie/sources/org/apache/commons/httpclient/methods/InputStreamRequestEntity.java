package org.apache.commons.httpclient.methods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InputStreamRequestEntity implements RequestEntity {
    public static final int CONTENT_LENGTH_AUTO = -2;
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
    private byte[] buffer;
    private InputStream content;
    private long contentLength;
    private String contentType;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$InputStreamRequestEntity == null) {
            class$ = class$("org.apache.commons.httpclient.methods.InputStreamRequestEntity");
            class$org$apache$commons$httpclient$methods$InputStreamRequestEntity = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
        }
        LOG = LogFactory.getLog(class$);
    }

    public InputStreamRequestEntity(InputStream inputStream) {
        this(inputStream, null);
    }

    public InputStreamRequestEntity(InputStream inputStream, String str) {
        this(inputStream, -2, str);
    }

    public InputStreamRequestEntity(InputStream inputStream, long j) {
        this(inputStream, j, null);
    }

    public InputStreamRequestEntity(InputStream inputStream, long j, String str) {
        this.buffer = null;
        if (inputStream == null) {
            throw new IllegalArgumentException("The content cannot be null");
        }
        this.content = inputStream;
        this.contentLength = j;
        this.contentType = str;
    }

    public String getContentType() {
        return this.contentType;
    }

    private void bufferContent() {
        if (this.buffer == null && this.content != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = this.content.read(bArr);
                    if (read < 0) {
                        this.buffer = byteArrayOutputStream.toByteArray();
                        this.content = null;
                        this.contentLength = (long) this.buffer.length;
                        return;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable e) {
                LOG.error(e.getMessage(), e);
                this.buffer = null;
                this.content = null;
                this.contentLength = 0;
            }
        }
    }

    public boolean isRepeatable() {
        return this.buffer != null;
    }

    public void writeRequest(OutputStream outputStream) throws IOException {
        if (this.content != null) {
            byte[] bArr = new byte[4096];
            int i = 0;
            while (true) {
                int read = this.content.read(bArr);
                if (read >= 0) {
                    outputStream.write(bArr, 0, read);
                    i += read;
                } else {
                    return;
                }
            }
        } else if (this.buffer != null) {
            outputStream.write(this.buffer);
        } else {
            throw new IllegalStateException("Content must be set before entity is written");
        }
    }

    public long getContentLength() {
        if (this.contentLength == -2 && this.buffer == null) {
            bufferContent();
        }
        return this.contentLength;
    }

    public InputStream getContent() {
        return this.content;
    }
}
