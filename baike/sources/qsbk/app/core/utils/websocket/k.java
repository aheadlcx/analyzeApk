package qsbk.app.core.utils.websocket;

class k implements Runnable {
    final /* synthetic */ WebSocketHandler a;

    k(WebSocketHandler webSocketHandler) {
        this.a = webSocketHandler;
    }

    public void run() {
        this.a.b((Runnable) this);
        if (!this.a.isConnected()) {
            return;
        }
        if (this.a.c.size() > 0) {
            while (!this.a.c.isEmpty()) {
                this.a.sendMessage(this.a.c.poll());
            }
            return;
        }
        this.a.sendMessage(this.a.b());
    }
}
