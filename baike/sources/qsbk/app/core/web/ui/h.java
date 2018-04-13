package qsbk.app.core.web.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.embed.SharePlugin;

class h implements OnClickListener {
    final /* synthetic */ WebActivity a;

    h(WebActivity webActivity) {
        this.a = webActivity;
    }

    public void onClick(View view) {
        if (this.a.h != null) {
            User user;
            CommonVideo commonVideo = new CommonVideo();
            commonVideo.share = this.a.h;
            commonVideo.thumbnail_url = this.a.h.imageUrl;
            AppUtils.getInstance().getUserInfoProvider().toShare(this.a.getCurActivity(), commonVideo, "web");
            User user2 = AppUtils.getInstance().getUserInfoProvider().getUser();
            if (user2 == null) {
                user = new User();
            } else {
                user = user2;
            }
            StatSDK.onEvent(this.a.getActivity(), SharePlugin.ACTION_SHARE_WEB, this.a.h.url, "", "button", user.getOrigin() + "_" + user.getOriginId());
        }
    }
}
