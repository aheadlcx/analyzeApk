package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

class go implements SimpleCallBack {
    final /* synthetic */ MyProfileFragment a;

    go(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.l = false;
        if (this.a.getActivity() != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("award");
                if (optJSONObject != null) {
                    this.a.n = optJSONObject.optBoolean("is_show");
                    this.a.o = optJSONObject.optString("link");
                    this.a.p = optJSONObject.optString("icon");
                    this.a.q = optJSONObject.optInt("type");
                    this.a.c();
                }
                if (MyProfileFragment.totalVis != jSONObject.getInt("tuv") || MyProfileFragment.todayVis != jSONObject.getInt("duv") || MyProfileFragment.newVis != jSONObject.getInt("iuv")) {
                    MyProfileFragment.totalVis = jSONObject.getInt("tuv");
                    MyProfileFragment.todayVis = jSONObject.getInt("duv");
                    MyProfileFragment.newVis = jSONObject.getInt("iuv");
                    if (this.a.e.size() >= 1) {
                        ((c) this.a.e.get(1)).q = MyProfileFragment.totalVis;
                        ((c) this.a.e.get(1)).r = MyProfileFragment.todayVis;
                        ((c) this.a.e.get(1)).s = MyProfileFragment.newVis;
                        this.a.d.notifyDataSetChanged();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onFailure(int i, String str) {
    }
}
