package pl.droidsonroids.gif;

import java.lang.Thread.UncaughtExceptionHandler;

abstract class h implements Runnable {
    final b c;

    abstract void a();

    h(b bVar) {
        this.c = bVar;
    }

    public final void run() {
        try {
            if (!this.c.b()) {
                a();
            }
        } catch (Throwable th) {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
            }
        }
    }
}
