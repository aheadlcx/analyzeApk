package qsbk.app.activity;

import java.util.TimerTask;

class fm extends TimerTask {
    final /* synthetic */ fl a;

    fm(fl flVar) {
        this.a = flVar;
    }

    public void run() {
        if (this.a.b.w != null && this.a.b.w.isShowing()) {
            this.a.b.w.setCanceledOnTouchOutside(true);
        }
    }
}
