package qsbk.app.video;

import android.os.Message;
import android.view.ViewTreeObserver.OnPreDrawListener;
import qsbk.app.utils.DebugUtil;

class ar implements OnPreDrawListener {
    final /* synthetic */ VideoEditPlayView a;

    ar(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public boolean onPreDraw() {
        this.a.c.getViewTreeObserver().removeOnPreDrawListener(this);
        int f = (this.a.g - this.a.f) / 2;
        int g = (this.a.h - this.a.f) / 2;
        DebugUtil.debug(VideoEditPlayView.a, "centerX = " + f + "centerY = " + g);
        this.a.c.scrollTo(f, g);
        Message message = new Message();
        message.what = 0;
        message.obj = this.a.c;
        this.a.s.sendMessage(message);
        return true;
    }
}
