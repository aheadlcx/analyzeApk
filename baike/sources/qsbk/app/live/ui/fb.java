package qsbk.app.live.ui;

import qsbk.app.core.utils.ToastUtil;

class fb implements Runnable {
    final /* synthetic */ LivePushActivity a;

    fb(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void run() {
        ToastUtil.Short("也许你的摄像头打不开哦~");
    }
}
