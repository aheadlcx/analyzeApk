package qsbk.app.nearby.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class j implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    j(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int optInt;
            int optInt2;
            int i = jSONObject.getInt("total");
            if (jSONObject.has("rank")) {
                optInt = jSONObject.optInt("rank");
            } else {
                optInt = -1;
            }
            if (jSONObject.has("score")) {
                optInt2 = jSONObject.optInt("score");
            } else {
                optInt2 = -1;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            CircleArticle circleArticle = null;
            if (jSONArray.length() > 0) {
                circleArticle = (CircleArticle) CircleArticle.parseJson(jSONArray.getJSONObject(0));
            }
            this.a.c.getPersonalDynamicSucc(circleArticle, i, optInt, optInt2);
        } catch (JSONException e) {
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.c.getPersonalDynamicFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.c.getPersonalDynamicFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.c.getPersonalDynamicFailed();
        if (!this.a.l) {
            this.a.l = true;
            if (i != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(1)).show();
            } else if (QsbkApp.currentUser != null) {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        }
    }
}
