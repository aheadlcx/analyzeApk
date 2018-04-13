package qsbk.app.core.utils.websocket;

class l implements Runnable {
    final /* synthetic */ WebSocketHandler a;

    l(WebSocketHandler webSocketHandler) {
        this.a = webSocketHandler;
    }

    public void run() {
        if (!this.a.e.isEmpty()) {
            for (int i = 0; i < this.a.e.size(); i++) {
                if (this.a.j != null) {
                    this.a.j.onReceiveMessage(this.a.e.get(i));
                }
            }
            this.a.e.clear();
        }
    }
}
