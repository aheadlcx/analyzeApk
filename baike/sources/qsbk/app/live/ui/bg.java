package qsbk.app.live.ui;

import android.text.TextUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.live.model.LiveMessage;

class bg implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    bg(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LogUtils.d(LiveBaseActivity.a, "live show system message");
        if (!TextUtils.isEmpty(this.a.e.sys_msg)) {
            this.a.a(LiveMessage.createSystemMessage(this.a.ax.getOriginId(), this.a.e.sys_msg));
            LiveBaseActivity.b(this.a, true);
        }
    }
}
