package cn.v6.sixrooms.widgets.phone;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.bean.GuardStausBean;

final class ah extends Handler {
    final /* synthetic */ ShowGuardPopWindow a;

    ah(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ShowGuardPopWindow.a(this.a);
                ShowGuardPopWindow.b(this.a);
                ShowGuardPopWindow.c(this.a);
                ShowGuardPopWindow.d(this.a);
                ShowGuardPopWindow.e(this.a);
                ShowGuardPopWindow.f(this.a);
                return;
            case 2:
                ShowGuardPopWindow.g(this.a);
                if (ShowGuardPopWindow.h(this.a).size() > 0) {
                    ShowGuardPopWindow.a(this.a, (GuardStausBean) ShowGuardPopWindow.h(this.a).get(0));
                    ShowGuardPopWindow.h(this.a).remove(0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
