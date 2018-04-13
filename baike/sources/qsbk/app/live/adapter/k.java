package qsbk.app.live.adapter;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.model.FamilyMemberData;

class k extends Builder {
    final /* synthetic */ FamilyMemberData a;
    final /* synthetic */ FamilyAllMemberAdapter b;

    k(FamilyAllMemberAdapter familyAllMemberAdapter, int i, FamilyMemberData familyMemberData) {
        this.b = familyAllMemberAdapter;
        this.a = familyMemberData;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        this.b.b(this.a);
        super.onPositiveActionClicked(dialogFragment);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
