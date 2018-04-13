package qsbk.app.cafe.plugin;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.Util;

public class PasteboardPlugin extends Plugin {
    public static final String MODEL = "pasteboard";
    private static final String[] a = new String[]{MODEL};

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (TextUtils.equals(a[0], str)) {
            Util.copyContent(getContext().getCurActivity(), jSONObject.getString("text"));
        }
    }

    public void onDestroy() {
    }
}
