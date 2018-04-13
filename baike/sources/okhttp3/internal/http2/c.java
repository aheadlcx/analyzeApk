package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;

class c extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ long c;
    final /* synthetic */ Http2Connection d;

    c(Http2Connection http2Connection, String str, Object[] objArr, int i, long j) {
        this.d = http2Connection;
        this.a = i;
        this.c = j;
        super(str, objArr);
    }

    public void execute() {
        try {
            this.d.p.windowUpdate(this.a, this.c);
        } catch (IOException e) {
        }
    }
}
