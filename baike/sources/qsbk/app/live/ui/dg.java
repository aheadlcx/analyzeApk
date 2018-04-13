package qsbk.app.live.ui;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.widget.FollowTipsDialog;
import qsbk.app.widget.RefreshTipView;

class dg implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    dg(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        if (!(this.a.l.getVisibility() != 0 || this.a.d == null || this.a.d.author == null || this.a.d.author.isFollow() || !this.a.az || !this.a.isOnResume || this.a.isFinishing())) {
            if (LiveBaseActivity.F(this.a) && AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                this.a.at = new FollowTipsDialog(this.a.getActivity(), this.a.getLiveUser());
                this.a.at.show();
                WindowUtils.setNonTransparentNavigationBar(this.a.getActivity());
                this.a.at.setOnDismissListener(new dh(this));
            } else {
                View findViewById = this.a.findViewById(R.id.tips_follow_arrow);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) findViewById.getLayoutParams();
                marginLayoutParams.leftMargin = ((MarginLayoutParams) findViewById.getLayoutParams()).leftMargin + (WindowUtils.dp2Px(10) + this.a.w.getLeft());
                findViewById.setLayoutParams(marginLayoutParams);
                this.a.x.setVisibility(0);
                LiveBaseActivity.g(this.a, LiveBaseActivity.G(this.a), RefreshTipView.FIRST_REFRESH_INTERVAL);
            }
        }
        LogUtils.d(LiveBaseActivity.a, "live show follow tips");
    }
}
