package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.NamedRunnable;

class f extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ List c;
    final /* synthetic */ boolean d;
    final /* synthetic */ Http2Connection e;

    f(Http2Connection http2Connection, String str, Object[] objArr, int i, List list, boolean z) {
        this.e = http2Connection;
        this.a = i;
        this.c = list;
        this.d = z;
        super(str, objArr);
    }

    public void execute() {
        boolean onHeaders = this.e.i.onHeaders(this.a, this.c, this.d);
        if (onHeaders) {
            try {
                this.e.p.rstStream(this.a, ErrorCode.CANCEL);
            } catch (IOException e) {
                return;
            }
        }
        if (onHeaders || this.d) {
            synchronized (this.e) {
                this.e.r.remove(Integer.valueOf(this.a));
            }
        }
    }
}
