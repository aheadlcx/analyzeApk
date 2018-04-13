package qsbk.app.live.ui;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class dt extends Builder {
    final /* synthetic */ LivePullActivity a;

    dt(LivePullActivity livePullActivity, int i) {
        this.a = livePullActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.toCloseLive();
    }
}
