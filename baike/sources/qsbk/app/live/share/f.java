package qsbk.app.live.share;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.live.ui.share.ShareCallbackHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$SinaRequestLinstener;

class f extends ShareUtils$SinaRequestLinstener {
    final /* synthetic */ LiveShareActivity a;

    f(LiveShareActivity liveShareActivity, ShareUtils shareUtils) {
        this.a = liveShareActivity;
        shareUtils.getClass();
        super(shareUtils);
    }

    public void onComplete(String str) {
        super.onComplete(str);
        try {
            if (new JSONObject(str).has("id")) {
                if ("live".equals(this.a.h)) {
                    ShareCallbackHelper.getInstance().notifyShareSuccess(this.a.n);
                }
                this.a.b("success");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
