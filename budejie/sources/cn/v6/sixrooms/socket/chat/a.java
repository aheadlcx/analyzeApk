package cn.v6.sixrooms.socket.chat;

import android.widget.Toast;
import cn.v6.sdk.sixrooms.coop.V6Coop;

final class a implements Runnable {
    final /* synthetic */ ChatMsgSocket a;

    a(ChatMsgSocket chatMsgSocket) {
        this.a = chatMsgSocket;
    }

    public final void run() {
        Toast.makeText(V6Coop.getInstance().getContext(), "服务器正在连接中...", 0).show();
    }
}
