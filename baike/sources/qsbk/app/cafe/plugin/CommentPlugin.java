package qsbk.app.cafe.plugin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.utils.ToastAndDialog;

public class CommentPlugin extends Plugin {
    public static final String MODEL = "comment";
    private static final String[] a = new String[]{"comment", "long_press"};

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (a[0].equalsIgnoreCase(str)) {
            Activity curActivity = this.b.getCurActivity();
            if ((curActivity instanceof NewsWebActivity) && QsbkApp.currentUser == null) {
                ToastAndDialog.makePositiveToast(curActivity.getApplicationContext(), "你还未登录糗百").show();
            }
        } else if (a[1].equalsIgnoreCase(str) && (this.b.getCurActivity() instanceof NewsWebActivity) && QsbkApp.currentUser != null) {
            int optInt = jSONObject.optInt("comment_id");
            CharSequence optString = jSONObject.optString("content");
            if (optInt > 0 && !TextUtils.isEmpty(optString)) {
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
    }
}
