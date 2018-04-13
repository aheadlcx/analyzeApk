package qsbk.app.nearby.api;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class k implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    k(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            ArrayList arrayList = new ArrayList();
            List jsonToArray = QiushiTopic.jsonToArray(jSONArray);
            if (jsonToArray.size() > 0) {
                this.a.i.getQiushiTopicSucc(jsonToArray);
            } else {
                this.a.i.getQiushiTopicFailed();
            }
        } catch (JSONException e) {
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.i.getQiushiTopicFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.i.getQiushiTopicFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.i.getQiushiTopicFailed();
        if (!this.a.l) {
            this.a.l = true;
            if (i != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(1)).show();
            } else if (QsbkApp.currentUser != null) {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        }
    }
}
