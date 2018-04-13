package qsbk.app.live.ui.family;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;

class ah implements OnClickListener {
    final /* synthetic */ FamilyDetailActivity a;

    ah(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toUserPage(this.a, this.a.M);
    }
}
