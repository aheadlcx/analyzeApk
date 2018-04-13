package cn.v6.sixrooms.socket.IM;

import cn.v6.sixrooms.engine.ServerAddressEngine.CallBack;
import java.util.List;

final class b implements CallBack {
    final /* synthetic */ IMSocketService a;

    b(IMSocketService iMSocketService) {
        this.a = iMSocketService;
    }

    public final void retIMAddress(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            this.a.k = this.a.k + 1;
            if (this.a.k < 3) {
                this.a.start();
                return;
            } else {
                this.a.k = 0;
                return;
            }
        }
        this.a.k = 0;
        this.a.run(list, "IM_SOCKET");
    }

    public final void retChatAddress(List<String> list, List<String> list2) {
    }

    public final void error(int i) {
    }
}
