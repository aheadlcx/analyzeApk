package qsbk.app.live.ui.family;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.FamilyRankData;

class bh implements OnClickListener {
    final /* synthetic */ FamilyRankData a;
    final /* synthetic */ FamilyRankFragment b;

    bh(FamilyRankFragment familyRankFragment, FamilyRankData familyRankData) {
        this.b = familyRankFragment;
        this.a = familyRankData;
    }

    public void onClick(View view) {
        if (this.a != null) {
            Intent intent = new Intent(this.b.getActivity(), FamilyDetailActivity.class);
            intent.putExtra("familyId", this.a.i);
            intent.putExtra("familyAvatar", this.a.c);
            intent.putExtra("familyName", this.a.n);
            intent.putExtra("familyBadge", this.a.b);
            this.b.getActivity().startActivity(intent);
        }
    }
}
