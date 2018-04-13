package qsbk.app.cafe.plugin;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.ToastAndDialog;

public class AlertPlugin extends Plugin {
    public static final String ACTION_ALERT = "alert";
    public static final String MODUL = "alert";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if ("alert".equals(str)) {
            String optString = jSONObject.optString("content");
            int optInt = jSONObject.optInt("success");
            if (!TextUtils.isEmpty(optString)) {
                if (optInt == 1) {
                    ToastAndDialog.makePositiveToast(getContext().getCurActivity(), optString).show();
                } else {
                    ToastAndDialog.makeNegativeToast(getContext().getCurActivity(), optString).show();
                }
            }
        }
    }

    public void onDestroy() {
    }
}
