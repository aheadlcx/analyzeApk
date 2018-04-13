package qsbk.app.core.utils.websocket;

class c implements Runnable {
    final /* synthetic */ WebSocketClient a;

    c(WebSocketClient webSocketClient) {
        this.a = webSocketClient;
    }

    public void run() {
        if (this.a.d != null) {
            this.a.d.quit();
            try {
                this.a.d.interrupt();
            } catch (SecurityException e) {
            }
        }
        if (this.a.i != null && !this.a.i.isShutdown()) {
            this.a.i.shutdownNow();
            this.a.i = null;
        }
    }
}
