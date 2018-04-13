package qsbk.app.live.ui;

import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

class ec implements Runnable {
    final /* synthetic */ LivePullActivity a;

    ec(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void run() {
        int dp2Px = WindowUtils.dp2Px(10);
        int measuredHeight = this.a.g.getMeasuredHeight();
        int measuredWidth = (this.a.g.getMeasuredWidth() * 3) / 4;
        int y = (((int) this.a.l.getY()) + this.a.l.getMeasuredHeight()) + dp2Px;
        LayoutParams layoutParams = (LayoutParams) this.a.g.getLayoutParams();
        layoutParams.gravity = 48;
        layoutParams.height = measuredWidth;
        layoutParams.topMargin = y;
        this.a.g.setLayoutParams(layoutParams);
        this.a.bm = ((measuredHeight - y) - measuredWidth) - WindowUtils.dp2Px(64);
        this.a.aq();
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, measuredWidth + (dp2Px * 2));
        layoutParams2.gravity = 48;
        layoutParams2.topMargin = y - dp2Px;
        ViewStub viewStub = (ViewStub) this.a.findViewById(R.id.view_stub_shadow);
        this.a.f = viewStub.inflate();
        this.a.f.setLayoutParams(layoutParams2);
        this.a.k.setImageResource(R.drawable.live_pc_gradient_bg);
        this.a.k.setVisibility(0);
    }
}
