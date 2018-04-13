package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;

class d extends NamedRunnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ p e;
    final /* synthetic */ Http2Connection f;

    d(Http2Connection http2Connection, String str, Object[] objArr, boolean z, int i, int i2, p pVar) {
        this.f = http2Connection;
        this.a = z;
        this.c = i;
        this.d = i2;
        this.e = pVar;
        super(str, objArr);
    }

    public void execute() {
        try {
            this.f.b(this.a, this.c, this.d, this.e);
        } catch (IOException e) {
        }
    }
}
