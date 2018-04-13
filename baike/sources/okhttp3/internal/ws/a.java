package okhttp3.internal.ws;

class a implements Runnable {
    final /* synthetic */ RealWebSocket a;

    a(RealWebSocket realWebSocket) {
        this.a = realWebSocket;
    }

    public void run() {
        do {
            try {
            } catch (Exception e) {
                this.a.failWebSocket(e, null);
                return;
            }
        } while (this.a.a());
    }
}
