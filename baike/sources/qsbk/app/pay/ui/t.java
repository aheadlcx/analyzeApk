package qsbk.app.pay.ui;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class t extends Builder {
    final /* synthetic */ WithdrawActivity a;

    t(WithdrawActivity withdrawActivity, int i) {
        this.a = withdrawActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        AppUtils.getInstance().getUserInfoProvider().toPay(this.a.getActivity(), 100);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
