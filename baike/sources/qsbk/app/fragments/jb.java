package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.ToastAndDialog;

class jb implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ QiushiTopicListFragment b;

    jb(QiushiTopicListFragment qiushiTopicListFragment, int i) {
        this.b = qiushiTopicListFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.c();
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1);
        boolean optBoolean = jSONObject.optBoolean("has_more");
        if (optInt == 0) {
            if (this.a == 1) {
                this.b.f.refreshDone();
                this.b.c.clear();
                this.b.f.setLoadMoreEnable(true);
            }
            this.b.c.addAll(QiushiTopic.jsonToArray(jSONObject.optJSONArray("data")));
            this.b.j = this.a;
            if (optBoolean) {
                this.b.f.loadMoreDone(true);
            } else {
                this.b.f.setLoadMoreEnable(false);
            }
            this.b.b.notifyDataSetChanged();
            return;
        }
        onFailure(-1, "数据加载失败");
    }

    public void onFailure(int i, String str) {
        this.b.c();
        this.b.f.refreshDone();
        this.b.f.loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
