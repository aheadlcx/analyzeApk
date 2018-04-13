package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.NamedRunnable;

class l extends NamedRunnable {
    final /* synthetic */ Settings a;
    final /* synthetic */ a c;

    l(a aVar, String str, Object[] objArr, Settings settings) {
        this.c = aVar;
        this.a = settings;
        super(str, objArr);
    }

    public void execute() {
        try {
            this.c.c.p.applyAndAckSettings(this.a);
        } catch (IOException e) {
        }
    }
}
