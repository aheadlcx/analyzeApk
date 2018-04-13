package qsbk.app.core.utils.websocket;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.websocket.WebSocketClient.Listener;

class f implements Listener {
    final /* synthetic */ WebSocketHandler a;

    f(WebSocketHandler webSocketHandler) {
        this.a = webSocketHandler;
    }

    public void onConnect() {
        LogUtils.d("websocket", this.a.a() + " websocket status: connected to room");
        if (this.a.i != null) {
            this.a.i.removeCallbacks(this.a.k);
        }
        this.a.a(new g(this));
        this.a.a(this.a.k, !this.a.c.isEmpty() ? 0 : WebSocketHandler.a);
    }

    public void onMessage(String str) {
        this.a.c((Object) str);
    }

    public void onMessage(byte[] bArr) {
        this.a.c((Object) bArr);
    }

    public void onDisconnect(int i, String str) {
        LogUtils.d("websocket", this.a.a() + " websocket status: connection lost. " + ("(" + i + ")" + str) + ".");
        this.a.a(new h(this, i, str));
    }

    public void onError(Exception exception) {
        LogUtils.e("websocket", this.a.a() + " websocket status: websocket error.", exception);
        this.a.a(new i(this, exception));
    }
}
