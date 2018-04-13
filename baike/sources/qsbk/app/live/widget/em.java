package qsbk.app.live.widget;

import android.view.ViewGroup;
import qsbk.app.live.widget.GlobalGiftView.MarqueeItem;

class em implements Runnable {
    final /* synthetic */ MarqueeItem a;

    em(MarqueeItem marqueeItem) {
        this.a = marqueeItem;
    }

    public void run() {
        ViewGroup viewGroup = (ViewGroup) this.a.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.a);
            if (this.a.a.b.size() == 0 && viewGroup.getChildCount() == 0) {
                viewGroup.setVisibility(8);
            }
        }
    }
}
