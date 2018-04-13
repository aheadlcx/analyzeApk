package qsbk.app.core.utils.websocket;

class j implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ WebSocketHandler b;

    j(WebSocketHandler webSocketHandler, Object obj) {
        this.b = webSocketHandler;
        this.a = obj;
    }

    public void run() {
        if (this.b.j != null) {
            this.b.j.onReceiveMessage(this.a);
        } else {
            this.b.e.add(this.a);
        }
    }
}
