package cn.v6.sixrooms.room;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;

class RoomUpgradeWindowManager$a extends Handler {
    final /* synthetic */ RoomUpgradeWindowManager a;

    RoomUpgradeWindowManager$a(RoomUpgradeWindowManager roomUpgradeWindowManager) {
        this.a = roomUpgradeWindowManager;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (RoomUpgradeWindowManager.a(this.a) != null && RoomUpgradeWindowManager.a(this.a).size() > 0) {
                    RoomUpgradeWindowManager.a(this.a, (RoomUpgradeMsg) RoomUpgradeWindowManager.a(this.a).remove(0));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
