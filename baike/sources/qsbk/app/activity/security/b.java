package qsbk.app.activity.security;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

class b implements IUiListener {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    b(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void onError(UiError uiError) {
    }

    public void onComplete(Object obj) {
        this.a.c(((JSONObject) obj).optString("nickname"));
    }

    public void onCancel() {
    }
}
