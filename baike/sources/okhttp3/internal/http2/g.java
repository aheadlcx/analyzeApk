package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;
import okio.Buffer;

class g extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ Buffer c;
    final /* synthetic */ int d;
    final /* synthetic */ boolean e;
    final /* synthetic */ Http2Connection f;

    g(Http2Connection http2Connection, String str, Object[] objArr, int i, Buffer buffer, int i2, boolean z) {
        this.f = http2Connection;
        this.a = i;
        this.c = buffer;
        this.d = i2;
        this.e = z;
        super(str, objArr);
    }

    public void execute() {
        try {
            boolean onData = this.f.i.onData(this.a, this.c, this.d, this.e);
            if (onData) {
                this.f.p.rstStream(this.a, ErrorCode.CANCEL);
            }
            if (onData || this.e) {
                synchronized (this.f) {
                    this.f.r.remove(Integer.valueOf(this.a));
                }
            }
        } catch (IOException e) {
        }
    }
}
