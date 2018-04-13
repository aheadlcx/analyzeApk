package okhttp3.internal;

public abstract class b implements Runnable {
    protected final String b;

    protected abstract void c();

    public b(String str, Object... objArr) {
        this.b = c.a(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            c();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
