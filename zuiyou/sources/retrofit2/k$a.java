package retrofit2;

import java.io.IOException;
import okhttp3.u;
import okhttp3.z;
import okio.BufferedSink;

class k$a extends z {
    private final z a;
    private final u b;

    k$a(z zVar, u uVar) {
        this.a = zVar;
        this.b = uVar;
    }

    public u contentType() {
        return this.b;
    }

    public long contentLength() throws IOException {
        return this.a.contentLength();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        this.a.writeTo(bufferedSink);
    }
}
