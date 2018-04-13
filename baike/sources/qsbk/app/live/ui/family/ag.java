package qsbk.app.live.ui.family;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class ag extends Builder {
    final /* synthetic */ FamilyDetailActivity a;

    ag(FamilyDetailActivity familyDetailActivity, int i) {
        this.a = familyDetailActivity;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        this.a.n();
        super.onPositiveActionClicked(dialogFragment);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
