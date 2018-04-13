package qsbk.app.live.ui.bag;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class x extends Builder {
    final /* synthetic */ w a;

    x(w wVar, int i) {
        this.a = wVar;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.a.dismiss();
        AppUtils.getInstance().getUserInfoProvider().toPay(this.a.a.s, 103);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
