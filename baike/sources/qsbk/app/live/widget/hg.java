package qsbk.app.live.widget;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.ui.LivePullActivity;

class hg implements OnClickListener {
    final /* synthetic */ LivePullEndDialog a;

    hg(LivePullEndDialog livePullEndDialog) {
        this.a = livePullEndDialog;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.a.a, this.a.k);
        ((LivePullActivity) this.a.a).doConfirm(false);
    }
}
