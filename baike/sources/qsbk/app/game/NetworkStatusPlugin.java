package qsbk.app.game;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.HttpUtils;

public class NetworkStatusPlugin extends Plugin {
    public static final String STATUS = "status";

    public String getNetwork() {
        if (HttpUtils.isNetworkConnected(QsbkApp.mContext)) {
            return HttpUtils.getNetwork(QsbkApp.mContext);
        }
        return "unconnect";
    }

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if ("status".equalsIgnoreCase(str)) {
            callback.sendResult(0, null, getNetwork());
        } else {
            callback.sendResult(1, str + " is not exist...", "");
        }
    }

    public void onDestroy() {
    }
}
