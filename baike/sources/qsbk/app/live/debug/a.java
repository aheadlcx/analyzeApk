package qsbk.app.live.debug;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.LivePullLauncher;

class a implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ LiveDebugListAdapter b;

    a(LiveDebugListAdapter liveDebugListAdapter, int i) {
        this.b = liveDebugListAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toLive((Activity) this.b.d, (CommonVideo) this.b.c.get(this.a), view, LivePullLauncher.STSOURCE_livelist, this.a, 1009);
    }
}
