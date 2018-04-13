package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;

class q implements OnClickListener {
    final /* synthetic */ LiveBaseActivity a;

    q(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onClick(View view) {
        LiveBaseActivity.a(this.a, view);
    }
}
