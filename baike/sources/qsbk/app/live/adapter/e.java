package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveDollHistoryData;

class e implements OnClickListener {
    final /* synthetic */ LiveDollHistoryData a;
    final /* synthetic */ DollHistoryAdapter b;

    e(DollHistoryAdapter dollHistoryAdapter, LiveDollHistoryData liveDollHistoryData) {
        this.b = dollHistoryAdapter;
        this.a = liveDollHistoryData;
    }

    public void onClick(View view) {
        User user = new User();
        user.id = this.a.UserPlatformId;
        user.origin = this.a.UserSource;
        user.origin_id = this.a.UserId;
        AppUtils.getInstance().getUserInfoProvider().toUserPage(this.b.a, user);
    }
}
