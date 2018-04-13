package okhttp3.internal.http2;

import okhttp3.internal.NamedRunnable;

class k extends NamedRunnable {
    final /* synthetic */ a a;

    k(a aVar, String str, Object... objArr) {
        this.a = aVar;
        super(str, objArr);
    }

    public void execute() {
        this.a.c.c.onSettings(this.a.c);
    }
}
