package okhttp3.internal.http2;

import okhttp3.internal.NamedRunnable;

class h extends NamedRunnable {
    final /* synthetic */ int a;
    final /* synthetic */ ErrorCode c;
    final /* synthetic */ Http2Connection d;

    h(Http2Connection http2Connection, String str, Object[] objArr, int i, ErrorCode errorCode) {
        this.d = http2Connection;
        this.a = i;
        this.c = errorCode;
        super(str, objArr);
    }

    public void execute() {
        this.d.i.onReset(this.a, this.c);
        synchronized (this.d) {
            this.d.r.remove(Integer.valueOf(this.a));
        }
    }
}
