package com.ali.auth.third.login.bridge;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.ali.auth.third.core.util.JSONUtils;
import com.ali.auth.third.login.task.BindByUsernameTask;
import com.ali.auth.third.login.task.LoginByUsernameTask;
import com.ali.auth.third.ui.context.BridgeCallbackContext;
import com.ali.auth.third.ui.webview.BridgeMethod;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginBridge {
    @BridgeMethod
    public void auth(BridgeCallbackContext bridgeCallbackContext, String str) {
        new LoginByUsernameTask(bridgeCallbackContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
    }

    @BridgeMethod
    public void bindByUsername(BridgeCallbackContext bridgeCallbackContext, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("params");
                if (optJSONObject != null) {
                    String optString = JSONUtils.optString(optJSONObject, "loginInfo");
                    new BindByUsernameTask(bridgeCallbackContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{optString});
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @BridgeMethod
    public void unbindByUsername(BridgeCallbackContext bridgeCallbackContext, String str) {
    }

    @BridgeMethod
    public void loginByUsername(BridgeCallbackContext bridgeCallbackContext, String str) {
        new LoginByUsernameTask(bridgeCallbackContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
    }
}
