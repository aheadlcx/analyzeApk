package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.message.e;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

class p {
    private final b a = new b();

    private static class a {
        private static final p a = new p();

        static {
            e.a().a(new z());
        }
    }

    private static class b {
        private ThreadPoolExecutor a;
        private LinkedBlockingQueue<Runnable> b;

        b() {
            a();
        }

        public void a(com.liulishuo.filedownloader.w.b bVar) {
            this.a.execute(new c(bVar));
        }

        private void a() {
            this.b = new LinkedBlockingQueue();
            this.a = com.liulishuo.filedownloader.g.b.a(3, this.b, "LauncherTask");
        }
    }

    private static class c implements Runnable {
        private final com.liulishuo.filedownloader.w.b a;
        private boolean b = false;

        c(com.liulishuo.filedownloader.w.b bVar) {
            this.a = bVar;
        }

        public void run() {
            if (!this.b) {
                this.a.l();
            }
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.a;
        }
    }

    p() {
    }

    public static p a() {
        return a.a;
    }

    synchronized void a(com.liulishuo.filedownloader.w.b bVar) {
        this.a.a(bVar);
    }
}
