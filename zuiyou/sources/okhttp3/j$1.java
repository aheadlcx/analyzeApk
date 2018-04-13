package okhttp3;

class j$1 implements Runnable {
    final /* synthetic */ j a;

    j$1(j jVar) {
        this.a = jVar;
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
