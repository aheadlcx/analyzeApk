package cn.v6.sixrooms.widgets.phone.switchbutton;

import android.os.Handler;
import android.os.Message;

final class a {
    private static int a = 256;
    private static int b = 7;
    private static int c = 16;
    private a d = new a();
    private b e;
    private boolean f = false;
    private int g;
    private int h;
    private int i;
    private int j = b;

    interface b {
        void a();

        void a(int i);

        boolean b();

        void c();
    }

    private static class a extends Handler {
        private a() {
        }

        public final void handleMessage(Message message) {
            if (message.what == a.a && message.obj != null) {
                ((Runnable) message.obj).run();
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ a a;

        c(a aVar) {
            this.a = aVar;
        }

        public final void run() {
            if (this.a.f) {
                this.a.e.a(this.a.g);
                if (this.a.e.b()) {
                    Message obtainMessage = this.a.d.obtainMessage();
                    obtainMessage.what = a.a;
                    obtainMessage.obj = this;
                    this.a.d.sendMessageDelayed(obtainMessage, (long) a.c);
                    return;
                }
                this.a.b();
                this.a.e.c();
            }
        }
    }

    private a() {
    }

    static a a() {
        return new a();
    }

    final a a(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("onAnimateListener can not be null");
        }
        this.e = bVar;
        return this;
    }

    final void a(int i, int i2) {
        this.f = true;
        this.h = i;
        this.i = i2;
        this.g = this.j;
        if (this.i > this.h) {
            this.g = Math.abs(this.j);
        } else if (this.i < this.h) {
            this.g = -Math.abs(this.j);
        } else {
            this.f = false;
            this.e.c();
            return;
        }
        this.e.a();
        new c(this).run();
    }

    final void b() {
        this.f = false;
    }

    public final void a(int i) {
        if (i <= 0) {
            this.j = b;
        } else {
            this.j = i;
        }
    }
}
