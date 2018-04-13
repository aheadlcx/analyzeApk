package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.ui.LiveBaseActivity;

class bn implements OnClickListener {
    final /* synthetic */ FollowTipsDialog a;

    bn(FollowTipsDialog followTipsDialog) {
        this.a = followTipsDialog;
    }

    public void onClick(View view) {
        if (this.a.a instanceof LiveBaseActivity) {
            AppUtils.getInstance().getUserInfoProvider().toUserPage((LiveBaseActivity) this.a.a, this.a.c);
        }
    }
}
