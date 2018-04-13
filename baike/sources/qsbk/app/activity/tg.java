package qsbk.app.activity;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Medal;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class tg implements SimpleCallBack {
    final /* synthetic */ MedalListActivity a;

    tg(MedalListActivity medalListActivity) {
        this.a = medalListActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("medals");
            if (this.a.i == a.TOP_REFRESH) {
                this.a.g.clear();
            }
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                this.a.e.removeFooterView(this.a.f);
                this.a.f.setHeight(this.a.i());
                this.a.f.setText(this.a.getResources().getString(R.string.medal_none));
                this.a.e.addFooterView(this.a.f);
            } else {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    Medal medal = new Medal();
                    medal.total = jSONObject2.optInt("total");
                    medal.current = jSONObject2.optInt("current");
                    medal.icon = jSONObject2.optString("icon");
                    medal.name = jSONObject2.optString("name");
                    medal.describe = jSONObject2.optString("description");
                    if (!this.a.g.contains(medal)) {
                        this.a.g.add(medal);
                    }
                }
                if (optJSONArray.length() == 30) {
                    this.a.d.setLoadMoreEnable(true);
                    this.a.j = this.a.j + 1;
                }
                if (TextUtils.equals(this.a.c, MedalListActivity.MEDAL_FROM)) {
                    this.a.e.removeFooterView(this.a.f);
                    this.a.f.setHeight(this.a.i());
                    this.a.f.setText(this.a.getResources().getString(R.string.medal_tip));
                    this.a.e.addFooterView(this.a.f);
                } else {
                    this.a.e.removeFooterView(this.a.f);
                }
            }
            this.a.h.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            ToastAndDialog.makeNegativeToast(this.a, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
        }
        if (this.a.i == a.TOP_REFRESH) {
            this.a.d.refreshDone();
        } else {
            this.a.d.loadMoreDone(true);
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a, str, Integer.valueOf(1)).show();
        if (this.a.e.getChildCount() == 0) {
            this.a.e.removeFooterView(this.a.f);
            this.a.f.setHeight(this.a.i());
            this.a.f.setText(this.a.getResources().getString(R.string.medal_network_error));
            this.a.e.addFooterView(this.a.f);
        }
        this.a.h.notifyDataSetChanged();
        if (this.a.i == a.TOP_REFRESH) {
            this.a.d.refreshDone();
        } else {
            this.a.d.loadMoreDone(true);
        }
    }
}
