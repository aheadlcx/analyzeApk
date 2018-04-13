package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface q extends Closeable, Flushable {
    s a();

    void a_(c cVar, long j) throws IOException;

    void close() throws IOException;

    void flush() throws IOException;
}
