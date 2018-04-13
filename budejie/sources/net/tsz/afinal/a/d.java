package net.tsz.afinal.a;

import cn.v6.sixrooms.socket.common.SocketUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MIME;
import org.apache.http.message.BasicHeader;

class d implements HttpEntity {
    private static final char[] d = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    ByteArrayOutputStream a = new ByteArrayOutputStream();
    boolean b = false;
    boolean c = false;
    private String e = null;

    public d() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(d[random.nextInt(d.length)]);
            i++;
        }
        this.e = stringBuffer.toString();
    }

    public void a() {
        if (!this.c) {
            try {
                this.a.write(("--" + this.e + SocketUtil.CRLF).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.c = true;
    }

    public void b() {
        if (!this.b) {
            try {
                this.a.write(("\r\n--" + this.e + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.b = true;
        }
    }

    public void a(String str, String str2) {
        a();
        try {
            this.a.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n").getBytes());
            this.a.write(str2.getBytes());
            this.a.write(("\r\n--" + this.e + SocketUtil.CRLF).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, InputStream inputStream, boolean z) {
        a(str, str2, inputStream, FilePart.DEFAULT_CONTENT_TYPE, z);
    }

    public void a(String str, String str2, InputStream inputStream, String str3, boolean z) {
        a();
        try {
            String str4 = "Content-Type: " + str3 + SocketUtil.CRLF;
            this.a.write(("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n").getBytes());
            this.a.write(str4.getBytes());
            this.a.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                this.a.write(bArr, 0, read);
            }
            if (!z) {
                this.a.write(("\r\n--" + this.e + SocketUtil.CRLF).getBytes());
            }
            this.a.flush();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            throw th;
        }
    }

    public long getContentLength() {
        b();
        return (long) this.a.toByteArray().length;
    }

    public Header getContentType() {
        return new BasicHeader(MIME.CONTENT_TYPE, "multipart/form-data; boundary=" + this.e);
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.a.toByteArray());
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return new ByteArrayInputStream(this.a.toByteArray());
    }
}
