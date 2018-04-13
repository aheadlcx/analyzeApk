package qsbk.app.activity;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class jt implements SimpleCallBack {
    final /* synthetic */ CreateNewGroupActivity a;

    jt(CreateNewGroupActivity createNewGroupActivity) {
        this.a = createNewGroupActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            this.a.y = jSONObject.getBoolean("qb_age");
            this.a.C = jSONObject.getInt("limit_day");
            this.a.B = jSONObject.getInt("left_day");
            this.a.z = jSONObject.getBoolean("is_completed");
            this.a.A = jSONObject.getBoolean("create_limit");
            this.a.D = jSONObject.optInt("tribe_count_limit");
            this.a.E = jSONObject.optInt("tribe_count");
            this.a.f();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a, str, Integer.valueOf(1)).show();
    }
}
