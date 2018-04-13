package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.widgets.phone.ShowGuardPopWindow;

final class ap implements Runnable {
    final /* synthetic */ GuardStausBean a;
    final /* synthetic */ RoomActivity b;

    ap(RoomActivity roomActivity, GuardStausBean guardStausBean) {
        this.b = roomActivity;
        this.a = guardStausBean;
    }

    public final void run() {
        if (this.b.an == null) {
            this.b.an = new ShowGuardPopWindow(this.b);
        }
        this.b.an.onShow(this.a, this.b.ao);
    }
}
