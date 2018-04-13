package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.PunchInfo;

class gr implements HttpCallBack {
    final /* synthetic */ MyProfileFragment a;

    gr(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isDetached() && this.a.isAdded()) {
            PunchInfo punchInfo = new PunchInfo();
            punchInfo.fromJson(jSONObject);
            if (this.a.e.contains(this.a.v)) {
                this.a.e.remove(this.a.v);
            }
            this.a.v = new d(punchInfo);
            this.a.e.add(2, this.a.v);
            this.a.d.notifyDataSetChanged();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
