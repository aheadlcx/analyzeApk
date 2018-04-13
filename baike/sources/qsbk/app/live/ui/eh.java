package qsbk.app.live.ui;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class eh extends Builder {
    final /* synthetic */ LivePushActivity a;

    eh(LivePushActivity livePushActivity, int i) {
        this.a = livePushActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.z();
        this.a.f(true);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
