package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class h extends s {
    private s a;

    public h(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = sVar;
    }

    public final s a() {
        return this.a;
    }

    public final h a(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = sVar;
        return this;
    }

    public s a(long j, TimeUnit timeUnit) {
        return this.a.a(j, timeUnit);
    }

    public long e_() {
        return this.a.e_();
    }

    public boolean f_() {
        return this.a.f_();
    }

    public long d() {
        return this.a.d();
    }

    public s a(long j) {
        return this.a.a(j);
    }

    public s g_() {
        return this.a.g_();
    }

    public s h_() {
        return this.a.h_();
    }

    public void g() throws IOException {
        this.a.g();
    }
}
