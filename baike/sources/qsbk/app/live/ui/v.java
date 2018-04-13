package qsbk.app.live.ui;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class v extends Builder {
    final /* synthetic */ LiveBaseActivity a;

    v(LiveBaseActivity liveBaseActivity, int i) {
        this.a = liveBaseActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.toPayActivity();
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
