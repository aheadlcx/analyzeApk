package qsbk.app.live.ui;

import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class al implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    al(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        ToastUtil.Short(R.string.live_mic_user_may_leave_tips);
    }
}
