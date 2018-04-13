package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;

class ch implements HttpCallBack {
    final /* synthetic */ DiggerFragment a;

    ch(DiggerFragment diggerFragment) {
        this.a = diggerFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = 0;
        boolean z = true;
        try {
            if (1 == this.a.f) {
                this.a.i.clear();
            }
            DiggerFragment diggerFragment = this.a;
            if (jSONObject.optInt("has_more") != 1) {
                z = false;
            }
            diggerFragment.g = z;
            JSONArray jSONArray = jSONObject.getJSONArray("actors");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            while (i < jSONArray.length()) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.parseBaseInfo(jSONArray.getJSONObject(i));
                if (!this.a.i.contains(baseUserInfo)) {
                    this.a.i.add(baseUserInfo);
                    arrayList.add(baseUserInfo);
                }
                i++;
            }
            DiggerFragment diggerFragment2 = this.a;
            diggerFragment2.f++;
            this.a.h.notifyDataSetChanged();
            this.a.b.refreshDone();
            if (this.a.i.size() > 0) {
                this.a.b.loadMoreDone(true);
            }
            if (!this.a.g) {
                this.a.b.setLoadMoreEnable(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        if (1 == this.a.f) {
            this.a.b.refreshDone();
        } else {
            this.a.b.loadMoreDone(false);
        }
        if (!TextUtils.isEmpty(str2) && this.a.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
    }
}
