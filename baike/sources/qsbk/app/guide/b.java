package qsbk.app.guide;

import qsbk.app.utils.DebugUtil;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        DebugUtil.debug("luolong", "mWindowManager removeView");
        this.a.a.g.removeView(this.a.a.f);
        this.a.a.g.removeViewImmediate(this.a.a.f);
    }
}
