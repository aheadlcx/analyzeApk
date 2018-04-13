package qsbk.app.core.web.plugin;

import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.web.ui.WebInterface;

public abstract class Plugin {
    protected WebInterface b;

    public abstract void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException;

    public abstract void onDestroy();

    public void exec(String str, String str2, Callback callback) throws JSONException {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str2)) {
            try {
                jSONObject = new JSONObject(str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.b.setFocusPlugin(this);
            exec(str, jSONObject, callback);
        }
        jSONObject = null;
        this.b.setFocusPlugin(this);
        exec(str, jSONObject, callback);
    }

    public void setContext(WebInterface webInterface) {
        this.b = webInterface;
    }

    public WebInterface getContext() {
        return this.b;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }
}
