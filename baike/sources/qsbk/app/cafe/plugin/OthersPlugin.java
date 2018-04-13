package qsbk.app.cafe.plugin;

import android.app.Activity;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class OthersPlugin extends Plugin {
    public static final String ACTION_POP = "pop";
    public static final String ACTION_RELOAD = "reload";
    public static final String MODUL = "others";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (ACTION_POP.equals(str)) {
            getContext().getCurActivity().finish();
        } else if (ACTION_RELOAD.equals(str)) {
            Activity curActivity = getContext().getCurActivity();
            if (curActivity instanceof SimpleWebActivity) {
                ((SimpleWebActivity) curActivity).reloadUrl();
            }
        }
    }

    public void onDestroy() {
    }
}
