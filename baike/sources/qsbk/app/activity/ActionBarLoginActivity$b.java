package qsbk.app.activity;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

class ActionBarLoginActivity$b implements IUiListener {
    final /* synthetic */ ActionBarLoginActivity a;

    private ActionBarLoginActivity$b(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    protected void a(JSONObject jSONObject) {
        try {
            DebugUtil.debug(ActionBarLoginActivity.e(), "QQ:" + jSONObject.toString());
            ActionBarLoginActivity.d(this.a, jSONObject.getString("openid"));
            ActionBarLoginActivity.b(this.a, jSONObject.getString("access_token"));
            ActionBarLoginActivity.c(this.a, jSONObject.getString("expires_in"));
            ActionBarLoginActivity.accessToken = new ThirdOauth2AccessToken(ActionBarLoginActivity.d(this.a), ActionBarLoginActivity.e(this.a));
            AccessTokenKeeper.keepAccessToken(this.a, ActionBarLoginActivity.accessToken);
            ActionBarLoginActivity.b(this.a, ActionBarLoginActivity.d(this.a), ActionBarLoginActivity.e(this.a), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onError(UiError uiError) {
        ToastAndDialog.makeNegativeToast(this.a, uiError.errorMessage, Integer.valueOf(0)).show();
    }

    public void onCancel() {
    }

    public void onComplete(Object obj) {
        a((JSONObject) obj);
    }
}
