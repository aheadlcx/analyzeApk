package qsbk.app.live.share;

import android.view.View;
import android.view.View.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ LiveShareActivity a;

    j(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
