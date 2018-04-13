package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

class ey implements OnLongClickListener {
    final /* synthetic */ LivePushActivity a;

    ey(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public boolean onLongClick(View view) {
        AppUtils.copyToClipboard(AppUtils.getInstance().getAppContext(), this.a.ax.nick_id + "", R.string.nick_id_copy_success);
        return false;
    }
}
