package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.ToastAndDialog;

class ap implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicListFragment b;

    ap(CircleTopicListFragment circleTopicListFragment, int i) {
        this.b = circleTopicListFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.c();
        this.b.j = jSONObject.optBoolean("has_more");
        try {
            Collection parseTopics = CircleTopicManager.parseTopics(jSONObject);
            this.b.i = this.a;
            this.b.h = false;
            if (this.a == 1) {
                this.b.c.refreshDone();
                this.b.a.clear();
                this.b.c.setLoadMoreEnable(true);
            }
            this.b.a.addAll(parseTopics);
            if (this.b.j) {
                this.b.c.loadMoreDone(true);
            } else {
                this.b.c.setLoadMoreEnable(false);
            }
            this.b.e.notifyDataSetChanged();
        } catch (JSONException e) {
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
