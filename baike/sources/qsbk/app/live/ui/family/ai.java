package qsbk.app.live.ui.family;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;

class ai implements OnClickListener {
    final /* synthetic */ FamilyDetailActivity a;

    ai(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toUserPage(this.a, this.a.M);
    }
}
