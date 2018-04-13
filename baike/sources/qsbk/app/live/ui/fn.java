package qsbk.app.live.ui;

import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class fn implements Runnable {
    final /* synthetic */ fm a;

    fn(fm fmVar) {
        this.a = fmVar;
    }

    public void run() {
        this.a.b.c.setBackgroundResource(R.drawable.live_publish_selector);
        this.a.b.c.setEnabled(true);
        this.a.b.d.setVisibility(8);
        ToastUtil.Short("网络诊断结束");
    }
}
