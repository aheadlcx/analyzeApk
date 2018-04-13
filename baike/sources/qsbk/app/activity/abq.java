package qsbk.app.activity;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class abq implements HttpCallBack {
    final /* synthetic */ SearchGroupActivity a;

    abq(SearchGroupActivity searchGroupActivity) {
        this.a = searchGroupActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.hideLoading();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            this.a.c = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a.c.add(jSONArray.getString(i));
            }
            this.a.d.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.hideLoading();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
