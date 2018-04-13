package qsbk.app.im;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class dq implements OnScrollListener {
    final /* synthetic */ GroupConversationActivity a;

    dq(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.ay != 0 && i + i2 >= this.a.ay) {
            this.a.ay = 0;
            this.a.ah.setVisibility(4);
        }
        if (this.a.aK == 0 && this.a.aJ != 0 && this.a.aI && this.a.d.getFirstVisiblePosition() <= this.a.g.e.size() - this.a.aJ) {
            this.a.ai.setOnClickListener(null);
            this.a.ai.setVisibility(4);
            this.a.aJ = 0;
        }
    }
}
