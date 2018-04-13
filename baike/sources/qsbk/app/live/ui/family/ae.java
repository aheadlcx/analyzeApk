package qsbk.app.live.ui.family;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class ae extends Builder {
    final /* synthetic */ FamilyDetailActivity a;

    ae(FamilyDetailActivity familyDetailActivity, int i) {
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
