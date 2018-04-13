package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.engine.ServerAddressEngine.CallBack;
import java.util.List;

final class c implements CallBack {
    final /* synthetic */ ChatSocketService a;

    c(ChatSocketService chatSocketService) {
        this.a = chatSocketService;
    }

    public final void retIMAddress(List<String> list, List<String> list2) {
        this.a.a(list, "IM_SOCKET");
    }

    public final void retChatAddress(List<String> list, List<String> list2) {
        if (!this.a.f) {
            this.a.a(list, "CHAT_SOCKET");
        }
    }

    public final void error(int i) {
    }
}
