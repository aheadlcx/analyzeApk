package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ LiveBaseActivity a;

    ak(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onClick(View view) {
        this.a.doMicClose();
    }
}
