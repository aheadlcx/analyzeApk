package qsbk.app.live.ui;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class ds extends Builder {
    final /* synthetic */ LivePullActivity a;

    ds(LivePullActivity livePullActivity, int i) {
        this.a = livePullActivity;
        super(i);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
        this.a.toCloseLive();
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.doFollowAnchor(true);
        this.a.getLiveUser().is_follow = true;
        this.a.toCloseLive();
    }
}
