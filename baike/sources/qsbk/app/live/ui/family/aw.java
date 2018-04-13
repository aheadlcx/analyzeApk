package qsbk.app.live.ui.family;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class aw extends OnScrollListener {
    final /* synthetic */ FamilyMessageActivity a;

    aw(FamilyMessageActivity familyMessageActivity) {
        this.a = familyMessageActivity;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.e && this.a.f && i2 > 0) {
            this.a.a();
        }
    }
}
