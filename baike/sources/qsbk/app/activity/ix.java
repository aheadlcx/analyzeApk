package qsbk.app.activity;

import android.text.Layout;
import android.view.ViewTreeObserver.OnPreDrawListener;

class ix implements OnPreDrawListener {
    final /* synthetic */ CircleTopicActivity a;

    ix(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public boolean onPreDraw() {
        this.a.n.getViewTreeObserver().removeOnPreDrawListener(this);
        Layout layout = this.a.n.getLayout();
        if (layout != null) {
            this.a.p.setVisibility(4);
            if (layout.getLineCount() >= 1 && layout.getEllipsisCount(0) > 0) {
                this.a.p.setVisibility(0);
            }
        }
        return true;
    }
}
