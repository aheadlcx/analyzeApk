package qsbk.app.core.web.plugin.embed;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.WebActivity;

public class WebPlugin extends Plugin {
    public static final String MODEL = "open_web";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if ("activity".equals(str)) {
            Context curActivity = this.b.getCurActivity();
            if (curActivity != null) {
                try {
                    WebActivity.launch(curActivity, jSONObject.getString("url"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onDestroy() {
    }
}
