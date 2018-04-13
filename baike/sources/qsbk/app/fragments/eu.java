package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class eu implements OnClickListener {
    final /* synthetic */ LiveTabsFragment a;

    eu(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onClick(View view) {
        if (this.a.i == 2) {
            this.a.c();
        } else {
            this.a.e();
        }
    }
}
