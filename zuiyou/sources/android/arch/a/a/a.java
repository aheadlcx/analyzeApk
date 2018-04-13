package android.arch.a.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY_GROUP})
public class a extends c {
    private static volatile a a;
    @NonNull
    private static final Executor d = new Executor() {
        public void execute(Runnable runnable) {
            a.a().b(runnable);
        }
    };
    @NonNull
    private static final Executor e = new Executor() {
        public void execute(Runnable runnable) {
            a.a().a(runnable);
        }
    };
    @NonNull
    private c b = this.c;
    @NonNull
    private c c = new b();

    private a() {
    }

    public static a a() {
        if (a != null) {
            return a;
        }
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
        }
        return a;
    }

    public void a(Runnable runnable) {
        this.b.a(runnable);
    }

    public void b(Runnable runnable) {
        this.b.b(runnable);
    }

    public boolean b() {
        return this.b.b();
    }
}
