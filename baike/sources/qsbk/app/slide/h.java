package qsbk.app.slide;

import java.util.TimerTask;

class h extends TimerTask {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void run() {
        if (this.a.a.Z != null && this.a.a.Z.isShowing()) {
            this.a.a.Z.setCanceledOnTouchOutside(true);
        }
    }
}
