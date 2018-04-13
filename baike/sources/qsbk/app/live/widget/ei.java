package qsbk.app.live.widget;

import qsbk.app.live.model.LiveGlobalGiftMessage;
import qsbk.app.live.widget.GlobalGiftView.MarqueeItem;

class ei implements Runnable {
    final /* synthetic */ GlobalGiftView a;

    ei(GlobalGiftView globalGiftView) {
        this.a = globalGiftView;
    }

    public void run() {
        this.a.removeCallbacks(this);
        int childCount = this.a.getChildCount();
        if (childCount < 2 && this.a.b.size() > 0) {
            this.a.setVisibility(0);
            int i = childCount == 0 ? 1 : 0;
            if (i == 0) {
                MarqueeItem marqueeItem = (MarqueeItem) this.a.getChildAt(childCount - 1);
                if (marqueeItem.startTime > 0) {
                    int currentTimeMillis = (int) (System.currentTimeMillis() - marqueeItem.startTime);
                    if (currentTimeMillis > 0) {
                        if (((this.a.e * currentTimeMillis) / 1000) - (marqueeItem.getWidth() + this.a.d) >= 0) {
                            childCount = 1;
                        } else {
                            childCount = 0;
                        }
                        if (childCount != 0) {
                            this.a.addMarqueeItem().startMarquee((LiveGlobalGiftMessage) this.a.b.remove(0));
                            this.a.h = System.currentTimeMillis();
                        }
                    }
                }
            }
            childCount = i;
            if (childCount != 0) {
                this.a.addMarqueeItem().startMarquee((LiveGlobalGiftMessage) this.a.b.remove(0));
                this.a.h = System.currentTimeMillis();
            }
        } else if (childCount >= 2 && System.currentTimeMillis() - this.a.h >= 10000) {
            this.a.removeAllViews();
            this.a.setVisibility(8);
        }
        if (this.a.b.size() > 0) {
            this.a.removeCallbacks(this);
            this.a.postDelayed(this, this.a.g);
        }
    }
}
