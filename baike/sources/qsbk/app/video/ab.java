package qsbk.app.video;

import android.view.ViewTreeObserver.OnPreDrawListener;

class ab implements OnPreDrawListener {
    final /* synthetic */ VideoEditActivity a;

    ab(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public boolean onPreDraw() {
        this.a.h.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.h.postDelayed(new ac(this), 200);
        return true;
    }
}
