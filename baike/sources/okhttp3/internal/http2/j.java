package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.platform.Platform;

class j extends NamedRunnable {
    final /* synthetic */ Http2Stream a;
    final /* synthetic */ a c;

    j(a aVar, String str, Object[] objArr, Http2Stream http2Stream) {
        this.c = aVar;
        this.a = http2Stream;
        super(str, objArr);
    }

    public void execute() {
        try {
            this.c.c.c.onStream(this.a);
        } catch (Throwable e) {
            Platform.get().log(4, "Http2Connection.Listener failure for " + this.c.c.e, e);
            try {
                this.a.close(ErrorCode.PROTOCOL_ERROR);
            } catch (IOException e2) {
            }
        }
    }
}
