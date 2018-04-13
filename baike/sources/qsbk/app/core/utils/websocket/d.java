package qsbk.app.core.utils.websocket;

import qsbk.app.core.utils.LogUtils;

class d implements Runnable {
    final /* synthetic */ WebSocketClient a;

    d(WebSocketClient webSocketClient) {
        this.a = webSocketClient;
    }

    public void run() {
        this.a.a();
        LogUtils.d("websocket", "websocket disconnected and socket closed");
    }
}
