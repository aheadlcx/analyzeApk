package qsbk.app.live.ui;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class an extends Builder {
    final /* synthetic */ LiveBaseActivity a;

    an(LiveBaseActivity liveBaseActivity, int i) {
        this.a = liveBaseActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.z();
    }
}
