package qsbk.app.fragments;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.live.widget.LiveStartUpDialog;

class ey extends Callback {
    final /* synthetic */ LiveTabsFragment a;

    ey(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("popupwindow");
        if (optJSONObject != null) {
            Object optString = optJSONObject.optString("popup_url");
            if (!TextUtils.isEmpty(optString) && this.a.isResumed()) {
                this.a.y = new LiveStartUpDialog(this.a.getActivity(), optString);
                this.a.y.show();
            }
        }
    }
}
