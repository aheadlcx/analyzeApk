package qsbk.app.cafe.plugin;

import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class NewsOtherPlugin extends Plugin {
    public static final String MODEL = "others";
    private static final String[] a = new String[]{"qbNewsAll", "qbNewsDetail"};

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (a[0].equalsIgnoreCase(str)) {
            if (!(this.b.getCurActivity() instanceof NewsWebActivity)) {
            }
        } else if (!a[1].equalsIgnoreCase(str) || !(this.b.getCurActivity() instanceof NewsWebActivity) || jSONObject.isNull("newsId")) {
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
    }
}
