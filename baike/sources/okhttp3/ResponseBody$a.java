package okhttp3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.BufferedSource;

final class ResponseBody$a extends Reader {
    private final BufferedSource a;
    private final Charset b;
    private boolean c;
    private Reader d;

    ResponseBody$a(BufferedSource bufferedSource, Charset charset) {
        this.a = bufferedSource;
        this.b = charset;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IOException("Stream closed");
        }
        Reader reader = this.d;
        if (reader == null) {
            reader = new InputStreamReader(this.a.inputStream(), Util.bomAwareCharset(this.a, this.b));
            this.d = reader;
        }
        return reader.read(cArr, i, i2);
    }

    public void close() throws IOException {
        this.c = true;
        if (this.d != null) {
            this.d.close();
        } else {
            this.a.close();
        }
    }
}
