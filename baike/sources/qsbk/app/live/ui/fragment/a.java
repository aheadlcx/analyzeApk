package qsbk.app.live.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class a extends OnScrollListener {
    final /* synthetic */ GiftRankFragment a;

    a(GiftRankFragment giftRankFragment) {
        this.a = giftRankFragment;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.g && this.a.h && i2 > 0) {
            this.a.c();
        }
        int findLastCompletelyVisibleItemPosition = this.a.c.findLastCompletelyVisibleItemPosition();
        if (i2 > 0 && findLastCompletelyVisibleItemPosition >= 9) {
            this.a.d();
        } else if (i2 < 0 && findLastCompletelyVisibleItemPosition <= 9) {
            this.a.e();
        }
    }
}
