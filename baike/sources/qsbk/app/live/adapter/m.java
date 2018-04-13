package qsbk.app.live.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.FamilyAnchorData;

class m implements OnClickListener {
    final /* synthetic */ FamilyAnchorData a;
    final /* synthetic */ FamilyAnchorAdapter b;

    m(FamilyAnchorAdapter familyAnchorAdapter, FamilyAnchorData familyAnchorData) {
        this.b = familyAnchorAdapter;
        this.a = familyAnchorData;
    }

    public void onClick(View view) {
        User user = new User();
        user.origin = (long) this.a.s;
        user.id = this.a.p;
        user.origin_id = this.a.i;
        user.name = this.a.n;
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.d, user);
    }
}
