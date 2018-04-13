package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.Collection;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.CheckInListFragment.CheckInInfo;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class al implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CheckInListFragment b;

    al(CheckInListFragment checkInListFragment, int i) {
        this.b = checkInListFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.c();
        this.b.j = jSONObject.optBoolean("has_more");
        if (this.a == 1) {
            this.b.c.refreshDone();
            this.b.a.clear();
            this.b.c.setLoadMoreEnable(true);
        }
        try {
            if (jSONObject.has("user")) {
                CheckInInfo checkInInfo = new CheckInInfo(jSONObject.optJSONObject("user"));
                checkInInfo.user.relationship = Relationship.MYSELF;
                this.b.a.add(checkInInfo);
            }
            Collection parseJsonArray = CheckInInfo.parseJsonArray(jSONObject.optJSONArray("rank"));
            this.b.i = this.a;
            this.b.h = false;
            this.b.a.addAll(parseJsonArray);
            if (this.b.j) {
                this.b.c.loadMoreDone(true);
            } else {
                this.b.c.setLoadMoreEnable(false);
            }
            this.b.e.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        this.b.c();
        this.b.h = false;
        this.b.c.refreshDone();
        this.b.c.loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
