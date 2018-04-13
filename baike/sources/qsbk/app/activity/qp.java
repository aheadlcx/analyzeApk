package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.LaiseeRecord;
import qsbk.app.utils.ToastAndDialog;

class qp implements HttpCallBack {
    final /* synthetic */ LaiseeDetailActivity a;

    qp(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            this.a.a.setLoadMoreEnable(1 == jSONObject.optInt("has_more"));
            if (this.a.k == 1) {
                this.a.f.totalCount = jSONObject.optInt("total_count");
                this.a.f.gotCount = jSONObject.optInt("got_count");
                this.a.f.totalMoney = jSONObject.optDouble("total_money");
                this.a.f.gotMoney = jSONObject.optDouble("got_money");
                this.a.f.myMoney = jSONObject.optString("my_money");
                this.a.i();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("records");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        LaiseeRecord parse = LaiseeRecord.parse(optJSONArray.getJSONObject(i));
                        if (!this.a.e.contains(parse)) {
                            this.a.e.add(parse);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                this.a.d.notifyDataSetChanged();
                this.a.k = this.a.k + 1;
            }
        }
        this.a.l = false;
    }

    public void onFailure(String str, String str2) {
        this.a.l = false;
        ToastAndDialog.makeNegativeToast(this.a, str2).show();
    }
}
