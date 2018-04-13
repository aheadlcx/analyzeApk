package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

class dw implements OnLongClickListener {
    final /* synthetic */ LivePullActivity a;

    dw(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public boolean onLongClick(View view) {
        AppUtils.copyToClipboard(AppUtils.getInstance().getAppContext(), this.a.d.author.nick_id + "", R.string.nick_id_copy_success);
        return false;
    }
}
