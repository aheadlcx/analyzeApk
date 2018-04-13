package com.facebook.e;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

abstract class a {

    @TargetApi(16)
    private static class a extends h {
        private final Choreographer b;
        private final FrameCallback c = new FrameCallback(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void doFrame(long j) {
                if (this.a.d && this.a.a != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    this.a.a.b((double) (uptimeMillis - this.a.e));
                    this.a.e = uptimeMillis;
                    this.a.b.postFrameCallback(this.a.c);
                }
            }
        };
        private boolean d;
        private long e;

        public static a a() {
            return new a(Choreographer.getInstance());
        }

        public a(Choreographer choreographer) {
            this.b = choreographer;
        }

        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeFrameCallback(this.c);
                this.b.postFrameCallback(this.c);
            }
        }

        public void c() {
            this.d = false;
            this.b.removeFrameCallback(this.c);
        }
    }

    private static class b extends h {
        private final Handler b;
        private final Runnable c = new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d && this.a.a != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    this.a.a.b((double) (uptimeMillis - this.a.e));
                    this.a.e = uptimeMillis;
                    this.a.b.post(this.a.c);
                }
            }
        };
        private boolean d;
        private long e;

        public static h a() {
            return new b(new Handler());
        }

        public b(Handler handler) {
            this.b = handler;
        }

        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeCallbacks(this.c);
                this.b.post(this.c);
            }
        }

        public void c() {
            this.d = false;
            this.b.removeCallbacks(this.c);
        }
    }

    public static h a() {
        if (VERSION.SDK_INT >= 16) {
            return a.a();
        }
        return b.a();
    }
}
