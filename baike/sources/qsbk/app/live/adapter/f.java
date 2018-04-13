package qsbk.app.live.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.FamilyAnchorData;

class f implements OnClickListener {
    final /* synthetic */ FamilyAnchorData a;
    final /* synthetic */ FamilyAllAnchoradapter b;

    f(FamilyAllAnchoradapter familyAllAnchoradapter, FamilyAnchorData familyAnchorData) {
        this.b = familyAllAnchoradapter;
        this.a = familyAnchorData;
    }

    public void onClick(View view) {
        User user = new User();
        user.id = this.a.p;
        user.origin_id = this.a.i;
        user.origin = (long) this.a.s;
        user.name = this.a.n;
        user.level = this.a.e;
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.b, user);
    }
}
