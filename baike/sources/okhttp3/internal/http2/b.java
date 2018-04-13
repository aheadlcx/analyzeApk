package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;

class b extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ ErrorCode c;
    final /* synthetic */ Http2Connection d;

    b(Http2Connection http2Connection, String str, Object[] objArr, int i, ErrorCode errorCode) {
        this.d = http2Connection;
        this.a = i;
        this.c = errorCode;
        super(str, objArr);
    }

    public void execute() {
        try {
            this.d.b(this.a, this.c);
        } catch (IOException e) {
        }
    }
}
