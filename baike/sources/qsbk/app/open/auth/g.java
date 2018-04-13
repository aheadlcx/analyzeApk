package qsbk.app.open.auth;

import android.content.Intent;
import android.util.Pair;
import org.json.JSONObject;
import qsbk.app.utils.LogUtil;

class g extends CommHttpAsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ SSOAuthActivity b;

    g(SSOAuthActivity sSOAuthActivity, String str) {
        this.b = sSOAuthActivity;
        this.a = str;
    }

    public String getJSONResp() throws Exception {
        String requestAccessToken = this.b.a.requestAccessToken(this.b.j, this.a, this.b.getNativeClientSecret(this.b.j, this.a));
        LogUtil.d("json resp 返回:" + requestAccessToken);
        return requestAccessToken;
    }

    protected void a(Pair<Integer, String> pair) {
        this.b.hideLoadingInfo();
        if (!this.b.h) {
            if (((Integer) pair.first).intValue() == 0) {
                JSONObject jSONObj = getJSONObj();
                LogUtil.d("get access token success:" + jSONObj.toString());
                Intent intent = new Intent();
                intent.putExtra("json_resp", jSONObj.toString());
                this.b.setResult(-1, intent);
                this.b.finish();
                return;
            }
            LogUtil.d("get access token fail");
            this.b.showAuthFailAndRetry((String) pair.second);
        }
    }
}
