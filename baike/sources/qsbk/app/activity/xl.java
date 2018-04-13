package qsbk.app.activity;

import android.text.TextUtils;
import com.tencent.bugly.Bugly;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.NewFan;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class xl implements HttpCallBack {
    final /* synthetic */ NewFansActivity a;

    xl(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = 0;
        NewFansActivity.b(this.a, false);
        try {
            NewFansActivity.t(this.a).setVisibility(8);
            String optString = jSONObject.optString("has_more");
            LogUtil.d("hasMoreString:" + optString);
            NewFansActivity newFansActivity = this.a;
            boolean z = ("0".equals(optString) || Bugly.SDK_IS_DEV.equals(optString)) ? false : true;
            NewFansActivity.c(newFansActivity, z);
            NewFansActivity.b(this.a, jSONObject.optInt("fan_count"));
            LogUtil.d("has_more:" + NewFansActivity.u(this.a));
            LogUtil.d("fan_count:" + NewFansActivity.g(this.a));
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                NewFan newFan = new NewFan();
                newFan.parseData(jSONArray.getJSONObject(i2));
                NewFansActivity.n(this.a).add(newFan);
            }
            NewFansActivity.c(this.a).clear();
            while (i < NewFansActivity.n(this.a).size()) {
                NewFansActivity.c(this.a).add(NewFansActivity.n(this.a).get(i));
                i++;
            }
            for (i = NewFansActivity.n(this.a).size(); i < NewFansActivity.g(this.a); i++) {
                NewFansActivity.c(this.a).add(new NewFan());
            }
            NewFansActivity.v(this.a);
            if (NewFansActivity.c(this.a).size() > 0) {
                NewFansActivity.o(this.a).setVisibility(0);
            }
            NewFansActivity.f(this.a).notifyDataSetChanged();
            NewFansActivity.d(this.a).notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        NewFansActivity.b(this.a, false);
        if (!TextUtils.isEmpty(str2)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
        NewFansActivity.t(this.a).setVisibility(8);
    }
}
