package qsbk.app.live.widget;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.ToastUtil;

class hh extends NetworkCallback {
    final /* synthetic */ LivePullEndDialog a;

    hh(LivePullEndDialog livePullEndDialog) {
        this.a = livePullEndDialog;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("f_source", this.a.k.getOrigin() + "");
        hashMap.put("f_uid", this.a.k.getOriginId() + "");
        return hashMap;
    }

    public void onFailed(int i, String str) {
        this.a.k.is_follow = !this.a.k.is_follow;
        this.a.showFollowBtn();
        this.a.hideFollowedBtn();
        ToastUtil.Short(str);
    }
}
