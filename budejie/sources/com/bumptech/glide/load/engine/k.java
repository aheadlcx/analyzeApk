package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.i.h;

class k {
    private boolean a;
    private final Handler b = new Handler(Looper.getMainLooper(), new a());

    private static class a implements Callback {
        private a() {
        }

        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((j) message.obj).d();
            return true;
        }
    }

    k() {
    }

    public void a(j<?> jVar) {
        h.a();
        if (this.a) {
            this.b.obtainMessage(1, jVar).sendToTarget();
            return;
        }
        this.a = true;
        jVar.d();
        this.a = false;
    }
}
