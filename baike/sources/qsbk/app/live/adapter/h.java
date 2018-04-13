package qsbk.app.live.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.FamilyMemberData;

class h implements OnClickListener {
    final /* synthetic */ FamilyMemberData a;
    final /* synthetic */ FamilyAllMemberAdapter b;

    h(FamilyAllMemberAdapter familyAllMemberAdapter, FamilyMemberData familyMemberData) {
        this.b = familyAllMemberAdapter;
        this.a = familyMemberData;
    }

    public void onClick(View view) {
        User user = new User();
        user.origin = (long) this.a.s;
        user.origin_id = this.a.u;
        user.id = this.a.p;
        user.name = this.a.n;
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.b, user);
    }
}
