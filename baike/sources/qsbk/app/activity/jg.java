package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.activity.ClockedRankingActivity.ClockedRanking;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class jg implements SimpleCallBack {
    final /* synthetic */ ClockedRankingActivity a;

    jg(ClockedRankingActivity clockedRankingActivity) {
        this.a = clockedRankingActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("items");
            boolean optBoolean = jSONObject.optBoolean("has_more");
            if (this.a.a == 1) {
                this.a.e.clear();
                this.a.b.refreshDone();
            } else {
                this.a.b.loadMoreDone(true);
            }
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                ClockedRanking clockedRanking = new ClockedRanking(this.a);
                clockedRanking.pasreJson(optJSONObject);
                this.a.e.add(clockedRanking);
            }
            this.a.a = this.a.a + 1;
            this.a.f.notifyDataSetChanged();
            if (optBoolean) {
                this.a.b.setLoadMoreEnable(true);
            } else {
                this.a.b.setLoadMoreEnable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a, str).show();
    }
}
