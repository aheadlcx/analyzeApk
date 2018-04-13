package qsbk.app.push;

import android.content.Context;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.MainActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;

class h implements SimpleCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ int f;
    final /* synthetic */ PushMessageProcessor g;

    h(PushMessageProcessor pushMessageProcessor, Context context, int i, String str, String str2, String str3, int i2) {
        this.g = pushMessageProcessor;
        this.a = context;
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = i2;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            SharePreferenceUtils.setSharePreferencesValue("qsjx_article", jSONObject.getJSONObject("article").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setClass(this.a, MainActivity.class);
        intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
        intent.putExtra(MainActivity.SHOW_QSJX_ARTICLE, true);
        intent.putExtra("msgid", this.b);
        intent.putExtra(PushMessageProcessor.PUSH_LABEL, this.c);
        PushMessageProcessor.notification(intent, this.a, this.d, this.e, this.f, null);
    }

    public void onFailure(int i, String str) {
    }
}
