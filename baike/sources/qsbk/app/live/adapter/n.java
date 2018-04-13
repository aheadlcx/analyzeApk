package qsbk.app.live.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.FamilyMemberData;

class n implements OnClickListener {
    final /* synthetic */ FamilyMemberData a;
    final /* synthetic */ FamilyEliteAdapter b;

    n(FamilyEliteAdapter familyEliteAdapter, FamilyMemberData familyMemberData) {
        this.b = familyEliteAdapter;
        this.a = familyMemberData;
    }

    public void onClick(View view) {
        User user = new User();
        user.origin = (long) this.a.s;
        user.id = this.a.p;
        user.name = this.a.n;
        user.origin_id = this.a.u;
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.d, user);
    }
}
