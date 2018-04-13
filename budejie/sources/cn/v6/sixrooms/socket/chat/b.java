package cn.v6.sixrooms.socket.chat;

import org.json.JSONObject;

final class b implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ String b;
    final /* synthetic */ ChatMsgSocket c;

    b(ChatMsgSocket chatMsgSocket, JSONObject jSONObject, String str) {
        this.c = chatMsgSocket;
        this.a = jSONObject;
        this.b = str;
    }

    public final void run() {
        this.c.onReceiveSuccess(this.a, this.b);
    }
}
