package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopicEpisode;
import qsbk.app.utils.ToastAndDialog;

class ar implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicRecommendFragment b;

    ar(CircleTopicRecommendFragment circleTopicRecommendFragment, int i) {
        this.b = circleTopicRecommendFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.b();
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1);
        boolean optBoolean = jSONObject.optBoolean("has_more");
        if (optInt == 0) {
            if (this.a == 1) {
                this.b.d.refreshDone();
                this.b.c.clear();
                this.b.d.setLoadMoreEnable(true);
            }
            this.b.c.addAll(CircleTopicEpisode.paseJsonArray(jSONObject.optJSONArray("data")));
            this.b.g = this.a;
            if (optBoolean) {
                this.b.d.loadMoreDone(true);
                this.b.f.setVisibility(8);
            } else {
                this.b.d.setLoadMoreEnable(false);
                this.b.f.setVisibility(0);
            }
            this.b.b.notifyDataSetChanged();
            return;
        }
        onFailure(-1, "数据加载失败");
    }

    public void onFailure(int i, String str) {
        this.b.b();
        this.b.d.refreshDone();
        this.b.d.loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
