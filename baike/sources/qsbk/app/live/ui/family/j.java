package qsbk.app.live.ui.family;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class j extends Builder {
    final /* synthetic */ FamilyCreateActivity a;

    j(FamilyCreateActivity familyCreateActivity, int i) {
        this.a = familyCreateActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        AppUtils.getInstance().getUserInfoProvider().toPay(this.a.getActivity(), 103);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
