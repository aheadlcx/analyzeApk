package cn.v6.sixrooms.room.popwindows;

import android.os.Handler;
import android.os.Message;

final class a extends Handler {
    final /* synthetic */ GodUpgradeWindow a;

    a(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                GodUpgradeWindow.a(this.a);
                GodUpgradeWindow.b(this.a);
                GodUpgradeWindow.c(this.a);
                GodUpgradeWindow.d(this.a);
                return;
            default:
                return;
        }
    }
}
