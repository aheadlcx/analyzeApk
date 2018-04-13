package qsbk.app.live.ui;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;

class ez extends Callback {
    final /* synthetic */ LivePushActivity a;

    ez(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            Object simpleDataStr = baseResponse.getSimpleDataStr("cover_url");
            if (TextUtils.isEmpty(simpleDataStr)) {
                AppUtils.getInstance().getImageProvider().loadAvatar(this.a.bL, this.a.ax.headurl, WindowUtils.dp2Px(10));
            } else {
                AppUtils.getInstance().getImageProvider().loadAvatar(this.a.bL, simpleDataStr, WindowUtils.dp2Px(10));
            }
        }
    }
}
