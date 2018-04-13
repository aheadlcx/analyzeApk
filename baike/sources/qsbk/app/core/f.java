package qsbk.app.core;

class f implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ AsyncTask$c b;

    f(AsyncTask$c asyncTask$c, Runnable runnable) {
        this.b = asyncTask$c;
        this.a = runnable;
    }

    public void run() {
        try {
            this.a.run();
        } finally {
            this.b.a();
        }
    }
}
