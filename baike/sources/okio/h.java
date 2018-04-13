package okio;

import java.io.IOException;

final class h implements Sink {
    h() {
    }

    public void write(Buffer buffer, long j) throws IOException {
        buffer.skip(j);
    }

    public void flush() throws IOException {
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public void close() throws IOException {
    }
}
