package qsbk.app.core.web.plugin.embed;

import android.app.Activity;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.cafe.plugin.JumpPlugin;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class LivePlugin extends Plugin {
    public static final String MODEL = "live";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (JumpPlugin.ACTION_OPEN_LIVE.equals(str)) {
            Activity curActivity = this.b.getCurActivity();
            if (curActivity != null) {
                try {
                    String string = jSONObject.getString("uid");
                    long j = jSONObject.getLong("source");
                    AppUtils.getInstance().getUserInfoProvider().toLive(curActivity, string, jSONObject.getString("platform_id"), j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onDestroy() {
    }
}
