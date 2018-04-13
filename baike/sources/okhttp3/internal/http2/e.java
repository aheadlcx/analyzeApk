package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.NamedRunnable;

class e extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ List c;
    final /* synthetic */ Http2Connection d;

    e(Http2Connection http2Connection, String str, Object[] objArr, int i, List list) {
        this.d = http2Connection;
        this.a = i;
        this.c = list;
        super(str, objArr);
    }

    public void execute() {
        if (this.d.i.onRequest(this.a, this.c)) {
            try {
                this.d.p.rstStream(this.a, ErrorCode.CANCEL);
                synchronized (this.d) {
                    this.d.r.remove(Integer.valueOf(this.a));
                }
            } catch (IOException e) {
            }
        }
    }
}
