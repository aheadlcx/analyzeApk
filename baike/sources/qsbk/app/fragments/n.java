package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class n extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("holder", "true");
            String post = HttpClient.getIntentce().post(Constants.URL_CLEAR, hashMap);
            LogUtil.d("response:" + post);
            JSONObject jSONObject = new JSONObject(post);
            return new Pair(Integer.valueOf(jSONObject.getInt(NotificationCompat.CATEGORY_ERROR)), jSONObject.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return SimpleHttpTask.getLocalError();
        }
    }

    protected void a(Pair<Integer, String> pair) {
        if (this.a.a.m) {
            this.a.a.c();
            if (((Integer) pair.first).equals(Integer.valueOf(0))) {
                this.a.a.b();
                this.a.a.a(1);
                return;
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) pair.second, Integer.valueOf(0)).show();
        }
    }
}
