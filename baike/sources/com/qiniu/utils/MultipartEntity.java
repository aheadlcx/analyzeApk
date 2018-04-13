package com.qiniu.utils;

import com.qiniu.conf.Conf;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MultipartEntity extends AbstractHttpEntity {
    private final c d;
    private IOnProcess e;
    private volatile long f;
    private volatile long g;

    interface b {
        long getContentLength();

        String getContentType();

        String getFilename();

        String getName();

        void writeTo(OutputStream outputStream) throws IOException;
    }

    class a implements b {
        final /* synthetic */ MultipartEntity a;
        private final String b;
        private final String c;
        private final String d;
        private final InputStreamAt e;

        public a(MultipartEntity multipartEntity, String str, String str2, String str3, InputStreamAt inputStreamAt) {
            this.a = multipartEntity;
            this.b = str;
            this.d = a(str3, inputStreamAt);
            this.e = inputStreamAt;
            if (str2 == null || str2.length() == 0) {
                str2 = "application/octet-stream";
            }
            this.c = str2;
        }

        private String a(String str, InputStreamAt inputStreamAt) {
            if (str != null) {
                return str;
            }
            str = inputStreamAt.getFilename();
            return str == null ? "_null_" : str;
        }

        public String getName() {
            return this.b;
        }

        public long getContentLength() {
            return this.e.length();
        }

        public String getFilename() {
            return this.d;
        }

        public String getContentType() {
            return this.c;
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            long length = this.e.length();
            int i = Conf.ONCE_WRITE_SIZE;
            long j = 0;
            while (j < length) {
                int min = (int) Math.min((long) i, length - j);
                outputStream.write(this.e.readNext(min).readAll(), 0, min);
                j += (long) min;
                if (this.a.e != null) {
                    this.a.f = this.a.f + ((long) min);
                    this.a.e.onProcess(this.a.f, length);
                }
            }
            outputStream.flush();
        }
    }

    static class c {
        private static final char[] a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        private static final Charset b = Charset.forName("US-ASCII");
        private static final ByteArrayBuffer c = a(b, "\r\n");
        private static final ByteArrayBuffer d = a(b, "--");
        private static final ByteArrayBuffer e = a(b, "Content-Disposition: form-data");
        private final ArrayList<b> f = new ArrayList();
        private final String g = a();

        private static ByteArrayBuffer a(Charset charset, String str) {
            ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encode.remaining());
            byteArrayBuffer.append(encode.array(), encode.position(), encode.remaining());
            return byteArrayBuffer;
        }

        private static ByteArrayBuffer b(String str) {
            return a(Charset.forName("utf-8"), str);
        }

        private static void a(ByteArrayBuffer byteArrayBuffer, OutputStream outputStream) throws IOException {
            outputStream.write(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
        }

        public void add(b bVar) {
            this.f.add(bVar);
        }

        public String getBoundary() {
            return this.g;
        }

        public long getTotalLength() {
            Iterator it = this.f.iterator();
            long j = 0;
            while (it.hasNext()) {
                long contentLength = ((b) it.next()).getContentLength();
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

        public void writeTo(OutputStream outputStream) throws IOException {
            a(outputStream, true);
        }

        private void a(OutputStream outputStream, boolean z) throws IOException {
            ByteArrayBuffer a = a(b, this.g);
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                a(outputStream, a);
                a(b(a(bVar.getName())), outputStream);
                if (bVar.getFilename() != null) {
                    a(b(c(bVar.getFilename())), outputStream);
                    a(c, outputStream);
                    a(b(d(bVar.getContentType())), outputStream);
                }
                a(c, outputStream);
                a(c, outputStream);
                if (z) {
                    bVar.writeTo(outputStream);
                }
                a(c, outputStream);
            }
            b(outputStream, a);
        }

        private void a(OutputStream outputStream, ByteArrayBuffer byteArrayBuffer) throws IOException {
            a(d, outputStream);
            a(byteArrayBuffer, outputStream);
            a(c, outputStream);
            a(e, outputStream);
        }

        private void b(OutputStream outputStream, ByteArrayBuffer byteArrayBuffer) throws IOException {
            a(d, outputStream);
            a(byteArrayBuffer, outputStream);
            a(d, outputStream);
            a(c, outputStream);
        }

        protected String a(String str) {
            return "; name=\"" + str + "\"";
        }

        private String c(String str) {
            return "; filename=\"" + str + "\"";
        }

        private String d(String str) {
            return "Content-Type: " + str;
        }

        protected String a() {
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();
            int nextInt = random.nextInt(11) + 30;
            for (int i = 0; i < nextInt; i++) {
                stringBuilder.append(a[random.nextInt(a.length)]);
            }
            return stringBuilder.toString();
        }
    }

    class d implements b {
        final /* synthetic */ MultipartEntity a;
        private final byte[] b;
        private final String c;

        public d(MultipartEntity multipartEntity, String str, String str2) {
            this.a = multipartEntity;
            this.b = Util.toByte(str2);
            this.c = str;
        }

        public String getName() {
            return this.c;
        }

        public long getContentLength() {
            return (long) this.b.length;
        }

        public String getFilename() {
            return null;
        }

        public String getContentType() {
            return HTTP.PLAIN_TEXT_TYPE;
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            if (outputStream == null) {
                throw new IllegalArgumentException("Output stream may not be null");
            }
            InputStream byteArrayInputStream = new ByteArrayInputStream(this.b);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    try {
                        byteArrayInputStream.close();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        }
    }

    public MultipartEntity() {
        this.f = 0;
        this.g = -1;
        this.d = new c();
        this.a = new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.d.getBoundary());
    }

    public void addField(String str, String str2) {
        this.d.add(new d(this, str, str2));
    }

    public void addFile(String str, String str2, String str3, InputStreamAt inputStreamAt) {
        this.d.add(new a(this, str, str2, str3, inputStreamAt));
    }

    public void setProcessNotify(IOnProcess iOnProcess) {
        this.e = iOnProcess;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return null;
    }

    public long getContentLength() {
        if (this.g < 0) {
            this.g = this.d.getTotalLength();
        }
        return this.g;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.d.writeTo(outputStream);
    }
}
