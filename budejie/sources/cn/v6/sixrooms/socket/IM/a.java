package cn.v6.sixrooms.socket.IM;

import cn.v6.sixrooms.engine.ServerAddressEngine.CallBack;
import java.util.List;

final class a implements CallBack {
    final /* synthetic */ IMService a;

    a(IMService iMService) {
        this.a = iMService;
    }

    public final void retIMAddress(List<String> list, List<String> list2) {
        this.a.run(list, "IM_SOCKET");
    }

    public final void retChatAddress(List<String> list, List<String> list2) {
        this.a.run(list, "CHAT_SOCKET");
    }

    public final void error(int i) {
    }
}
