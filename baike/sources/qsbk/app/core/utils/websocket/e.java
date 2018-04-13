package qsbk.app.core.utils.websocket;

import java.io.OutputStream;

class e implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ WebSocketClient b;

    e(WebSocketClient webSocketClient, byte[] bArr) {
        this.b = webSocketClient;
        this.a = bArr;
    }

    public void run() {
        try {
            synchronized (this.b.j) {
                if (this.b.c == null) {
                    throw new IllegalStateException("Socket not connected");
                }
                OutputStream outputStream = this.b.c.getOutputStream();
                outputStream.write(this.a);
                outputStream.flush();
            }
        } catch (Exception e) {
            this.b.b.onError(e);
        }
    }
}
