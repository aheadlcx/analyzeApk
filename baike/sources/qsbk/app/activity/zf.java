package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.fragments.OthersQiuShiFragment;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.ToastAndDialog;

class zf implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ OthersQiuShiActivity b;

    zf(OthersQiuShiActivity othersQiuShiActivity, String str) {
        this.b = othersQiuShiActivity;
        this.a = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        UserInfo userInfo = new UserInfo();
        UserInfo.updateServerJsonNearby(userInfo, jSONObject.optJSONObject("userdata"));
        Fragment othersQiuShiFragment = new OthersQiuShiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uid", this.a);
        bundle.putString("user_info", userInfo.toString());
        othersQiuShiFragment.setArguments(bundle);
        this.b.getSupportFragmentManager().beginTransaction().add(R.id.container, othersQiuShiFragment).commit();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
