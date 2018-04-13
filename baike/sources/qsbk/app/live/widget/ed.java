package qsbk.app.live.widget;

import android.view.ViewGroup;
import qsbk.app.live.model.LiveGlobalRedEnvelopesMessage;
import qsbk.app.live.widget.GlobalBarrageLayout.BarrageItem;

class ed implements Runnable {
    final /* synthetic */ GlobalBarrageLayout a;

    ed(GlobalBarrageLayout globalBarrageLayout) {
        this.a = globalBarrageLayout;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (this.a.c.size() > 0) {
            ViewGroup b = this.a.getAvailableAnimLine();
            if (b != null) {
                int childCount = b.getChildCount();
                int i = childCount == 0 ? 1 : 0;
                if (i == 0) {
                    BarrageItem barrageItem = (BarrageItem) b.getChildAt(childCount - 1);
                    if (barrageItem.startTime > 0) {
                        int currentTimeMillis = (int) (System.currentTimeMillis() - barrageItem.startTime);
                        if (currentTimeMillis > 0) {
                            childCount = ((this.a.f * currentTimeMillis) / 1000) - (barrageItem.getWidth() + this.a.e) >= 0 ? 1 : 0;
                            if (childCount != 0) {
                                this.a.addBarrageItem(b, (LiveGlobalRedEnvelopesMessage) this.a.c.remove(0)).startMarquee();
                            }
                        }
                    }
                }
                childCount = i;
                if (childCount != 0) {
                    this.a.addBarrageItem(b, (LiveGlobalRedEnvelopesMessage) this.a.c.remove(0)).startMarquee();
                }
            }
        }
        if (this.a.c.size() > 0) {
            this.a.removeCallbacks(this);
            this.a.postDelayed(this, this.a.i);
        }
    }
}
