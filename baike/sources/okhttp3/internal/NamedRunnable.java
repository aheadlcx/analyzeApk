package okhttp3.internal;

public abstract class NamedRunnable implements Runnable {
    protected final String b;

    protected abstract void execute();

    public NamedRunnable(String str, Object... objArr) {
        this.b = Util.format(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            execute();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
