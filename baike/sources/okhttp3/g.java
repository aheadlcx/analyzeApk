package okhttp3;

class g implements Runnable {
    final /* synthetic */ ConnectionPool a;

    g(ConnectionPool connectionPool) {
        this.a = connectionPool;
    }

    public void run() {
        while (true) {
            long a = this.a.a(System.nanoTime());
            if (a != -1) {
                if (a > 0) {
                    long j = a / 1000000;
                    a -= j * 1000000;
                    synchronized (this.a) {
                        try {
                            this.a.wait(j, (int) a);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
