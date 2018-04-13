package org.greenrobot.eventbus;

import android.os.Looper;

public interface g {

    public static class a implements g {
        private final Looper a;

        public a(Looper looper) {
            this.a = looper;
        }

        public boolean a() {
            return this.a == Looper.myLooper();
        }

        public k a(c cVar) {
            return new e(cVar, this.a, 10);
        }
    }

    k a(c cVar);

    boolean a();
}
