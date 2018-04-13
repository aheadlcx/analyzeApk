package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;

class fe implements OnClickListener {
    final /* synthetic */ LiveRankActivity a;

    fe(LiveRankActivity liveRankActivity) {
        this.a = liveRankActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
