package qsbk.app.live.widget;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveMessage;

class p implements Runnable {
    final /* synthetic */ DollCatchView a;

    p(DollCatchView dollCatchView) {
        this.a = dollCatchView;
    }

    public void run() {
        this.a.y.removeCallbacksAndMessages(this);
        this.a.a.sendLiveMessageAndRefreshUI(LiveMessage.createDollActionMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), this.a.z));
        this.a.y.postDelayed(this, (long) DollCatchView.INTERVAL_SEND_MESSAGE);
    }
}
