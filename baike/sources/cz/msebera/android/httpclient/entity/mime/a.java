package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

abstract class a {
    private static final ByteArrayBuffer c = a(MIME.DEFAULT_CHARSET, ": ");
    private static final ByteArrayBuffer d = a(MIME.DEFAULT_CHARSET, "\r\n");
    private static final ByteArrayBuffer e = a(MIME.DEFAULT_CHARSET, "--");
    final Charset a;
    final String b;

    protected abstract void a(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException;

    public abstract List<FormBodyPart> getBodyParts();

    private static ByteArrayBuffer a(Charset charset, String str) {
        ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encode.remaining());
        byteArrayBuffer.append(encode.array(), encode.position(), encode.remaining());
        return byteArrayBuffer;
    }

    private static void a(ByteArrayBuffer byteArrayBuffer, OutputStream outputStream) throws IOException {
        outputStream.write(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
    }

    private static void a(String str, Charset charset, OutputStream outputStream) throws IOException {
        a(a(charset, str), outputStream);
    }

    private static void a(String str, OutputStream outputStream) throws IOException {
        a(a(MIME.DEFAULT_CHARSET, str), outputStream);
    }

    protected static void a(MinimalField minimalField, OutputStream outputStream) throws IOException {
        a(minimalField.getName(), outputStream);
        a(c, outputStream);
        a(minimalField.getBody(), outputStream);
        a(d, outputStream);
    }

    protected static void a(MinimalField minimalField, Charset charset, OutputStream outputStream) throws IOException {
        a(minimalField.getName(), charset, outputStream);
        a(c, outputStream);
        a(minimalField.getBody(), charset, outputStream);
        a(d, outputStream);
    }

    public a(Charset charset, String str) {
        Args.notNull(str, "Multipart boundary");
        if (charset == null) {
            charset = MIME.DEFAULT_CHARSET;
        }
        this.a = charset;
        this.b = str;
    }

    public a(String str) {
        this(null, str);
    }

    void a(OutputStream outputStream, boolean z) throws IOException {
        ByteArrayBuffer a = a(this.a, this.b);
        for (FormBodyPart formBodyPart : getBodyParts()) {
            a(e, outputStream);
            a(a, outputStream);
            a(d, outputStream);
            a(formBodyPart, outputStream);
            a(d, outputStream);
            if (z) {
                formBodyPart.getBody().writeTo(outputStream);
            }
            a(d, outputStream);
        }
        a(e, outputStream);
        a(a, outputStream);
        a(e, outputStream);
        a(d, outputStream);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        a(outputStream, true);
    }

    public long getTotalLength() {
        long j = 0;
        for (FormBodyPart body : getBodyParts()) {
            long contentLength = body.getBody().getContentLength();
            if (contentLength < 0) {
                return -1;
            }
            j = contentLength + j;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(byteArrayOutputStream, false);
            return ((long) byteArrayOutputStream.toByteArray().length) + j;
        } catch (IOException e) {
            return -1;
        }
    }
}
