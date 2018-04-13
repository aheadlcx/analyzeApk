package qsbk.app.push;

import android.content.Context;
import android.content.Intent;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.MainActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;

class l implements SimpleCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ XiaoMiPushReceiver b;

    l(XiaoMiPushReceiver xiaoMiPushReceiver, Context context) {
        this.b = xiaoMiPushReceiver;
        this.a = context;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            SharePreferenceUtils.setSharePreferencesValue("qsjx_article", jSONObject.getJSONObject("article").toString());
        } catch (JSONException e) {
        }
        Intent intent = new Intent();
        intent.setClass(this.a, MainActivity.class);
        intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
        intent.putExtra(MainActivity.SHOW_QSJX_ARTICLE, true);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        this.a.startActivity(intent);
    }

    public void onFailure(int i, String str) {
    }
}
