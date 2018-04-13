package qsbk.app.live.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class cv extends OnScrollListener {
    final /* synthetic */ LiveBaseActivity a;

    cv(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        boolean z = false;
        super.onScrolled(recyclerView, i, i2);
        if (i2 >= 0) {
            int findFirstVisibleItemPosition = this.a.N.findFirstVisibleItemPosition();
            int childCount = this.a.N.getChildCount();
            int itemCount = this.a.N.getItemCount();
            LiveBaseActivity liveBaseActivity = this.a;
            if (!LiveBaseActivity.b(this.a) || findFirstVisibleItemPosition + childCount >= itemCount) {
                z = true;
            }
            LiveBaseActivity.a(liveBaseActivity, z);
        } else {
            LiveBaseActivity.a(this.a, false);
        }
        if (LiveBaseActivity.c(this.a)) {
            LiveBaseActivity.a(this.a, LiveBaseActivity.d(this.a));
        }
    }
}
