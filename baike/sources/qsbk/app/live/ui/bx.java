package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;

class bx implements OnClickListener {
    final /* synthetic */ LiveBaseActivity a;

    bx(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onClick(View view) {
        LiveBaseActivity.z(this.a);
    }
}
