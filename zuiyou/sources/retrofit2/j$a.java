package retrofit2;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

class j$a extends j {

    static class a implements Executor {
        private final Handler a = new Handler(Looper.getMainLooper());

        a() {
        }

        public void execute(Runnable runnable) {
            this.a.post(runnable);
        }
    }

    j$a() {
    }

    public Executor b() {
        return new a();
    }

    c$a a(@Nullable Executor executor) {
        if (executor != null) {
            return new g(executor);
        }
        throw new AssertionError();
    }
}
