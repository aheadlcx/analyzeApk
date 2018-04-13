package okhttp3;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import okhttp3.internal.a.d;
import okhttp3.internal.a.e;

public final class c implements Closeable, Flushable {
    final e a;
    final d b;

    public void flush() throws IOException {
        this.b.flush();
    }

    public void close() throws IOException {
        this.b.close();
    }
}
