package qsbk.app.live.widget;

import com.opensource.svgaplayer.SVGACallback;
import qsbk.app.live.model.LiveEnterMessage;

class jc implements SVGACallback {
    final /* synthetic */ iz a;

    jc(iz izVar) {
        this.a = izVar;
    }

    public void onPause() {
    }

    public void onFinished() {
        this.a.c.p.setVisibility(8);
        this.a.c.f = true;
        this.a.c.d();
        if (this.a.c.e != null && this.a.c.e.size() > 0) {
            LiveEnterMessage liveEnterMessage;
            synchronized (this) {
                liveEnterMessage = (LiveEnterMessage) this.a.c.e.remove(0);
            }
            if (liveEnterMessage != null) {
                this.a.c.setViewContentAndStartAnim(liveEnterMessage);
            }
        }
    }

    public void onRepeat() {
    }

    public void onStep(int i, double d) {
    }
}
