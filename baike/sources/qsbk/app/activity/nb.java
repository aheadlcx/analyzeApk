package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.GroupLeader;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class nb implements SimpleCallBack {
    final /* synthetic */ GroupLeaderListActivity a;

    nb(GroupLeaderListActivity groupLeaderListActivity) {
        this.a = groupLeaderListActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                this.a.d.clear();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    GroupLeader parseFromJsonObject = GroupLeader.parseFromJsonObject(optJSONArray.getJSONObject(i));
                    if (!this.a.d.contains(parseFromJsonObject)) {
                        this.a.d.add(parseFromJsonObject);
                    }
                }
            }
            if (this.a.d.size() > 0 && this.a.e != null) {
                this.a.e.notifyDataSetChanged();
            }
            this.a.b.refreshDone(true);
        } catch (Exception e) {
            this.a.b.refreshDone(false);
            ToastAndDialog.makeNegativeToast(this.a, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a, str, Integer.valueOf(1)).show();
    }
}
