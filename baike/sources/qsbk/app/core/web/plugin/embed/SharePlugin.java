package qsbk.app.core.web.plugin.embed;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.Share;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.WebActivity;

public class SharePlugin extends Plugin {
    public static final String ACTION_SHARE_COPY = "share_copy";
    public static final String ACTION_SHARE_WEB = "share_web";
    public static final String MODEL = "share";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        int i;
        Object optString;
        int i2 = 1;
        int i3 = 0;
        CharSequence optString2 = jSONObject.optString("type");
        if (qsbk.app.cafe.plugin.SharePlugin.ACTION_WEXIN.equals(str)) {
            int i4 = jSONObject.getInt("scene");
            if (i4 == 2) {
                i3 = 1;
            } else if (i4 == 1) {
                i2 = 0;
                i3 = 2;
            } else {
                i2 = 0;
            }
            i = i3;
            i3 = i2;
        } else if (qsbk.app.cafe.plugin.SharePlugin.ACTION_QQ.equals(str)) {
            i = 4;
            i3 = 2;
        } else if (qsbk.app.cafe.plugin.SharePlugin.ACTION_QZONE.equals(str)) {
            i3 = 3;
            i = 5;
        } else {
            if (ACTION_SHARE_COPY.equals(str)) {
                optString = jSONObject.optString(PayPWDUniversalActivity.KEY);
                if (!TextUtils.isEmpty(optString)) {
                    AppUtils.copyToClipboard(AppUtils.getInstance().getAppContext(), optString);
                }
            }
            i = 0;
        }
        if (i <= 0) {
            return;
        }
        if (TextUtils.isEmpty(optString2)) {
            CommonVideo newInstance = CommonVideo.newInstance(0);
            newInstance.share = new Share();
            newInstance.share.url = jSONObject.optString("url");
            newInstance.share.caption = jSONObject.optString("title");
            newInstance.share.wb_info = jSONObject.optString("description");
            newInstance.share.imageUrl = jSONObject.optString("imgurl");
            if (TextUtils.isEmpty(newInstance.share.url)) {
                Activity curActivity = this.b.getCurActivity();
                if (curActivity != null && (curActivity instanceof WebActivity)) {
                    newInstance.share.url = ((WebActivity) curActivity).getUrl();
                }
            }
            AppUtils.getInstance().getUserInfoProvider().toShare(this.b.getCurActivity(), newInstance, "web", i);
            return;
        }
        optString = jSONObject.optString("imgurl");
        if (!TextUtils.isEmpty(optString)) {
            AppUtils.getInstance().getUserInfoProvider().shareImage(this.b.getCurActivity(), optString, i3);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
    }
}
