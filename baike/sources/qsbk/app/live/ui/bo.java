package qsbk.app.live.ui;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class bo extends Builder {
    final /* synthetic */ LiveBaseActivity a;

    bo(LiveBaseActivity liveBaseActivity, int i) {
        this.a = liveBaseActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        AppUtils.getInstance().getUserInfoProvider().toMobileBind(this.a);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
