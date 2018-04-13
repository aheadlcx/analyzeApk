package qsbk.app.live.ui.family;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.FamilyRankData;

class ba implements OnClickListener {
    final /* synthetic */ FamilyRankData a;
    final /* synthetic */ FamilyRankAdapter b;

    ba(FamilyRankAdapter familyRankAdapter, FamilyRankData familyRankData) {
        this.b = familyRankAdapter;
        this.a = familyRankData;
    }

    public void onClick(View view) {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            Intent intent = new Intent(this.b.d, FamilyDetailActivity.class);
            intent.putExtra("familyId", this.a.i);
            intent.putExtra("familyAvatar", this.a.c);
            intent.putExtra("familyName", this.a.n);
            intent.putExtra("familyBadge", this.a.b);
            this.b.d.startActivity(intent);
            return;
        }
        AppUtils.getInstance().getUserInfoProvider().toLogin((Activity) this.b.d, FamilyRankAdapter.f);
    }
}
