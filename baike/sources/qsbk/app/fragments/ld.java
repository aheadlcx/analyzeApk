package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.ToastAndDialog;

class ld implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ TopicsTopFragment b;

    ld(TopicsTopFragment topicsTopFragment, int i) {
        this.b = topicsTopFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        TopicsTopFragment.a(this.b);
        TopicsTopFragment.a(this.b, jSONObject.optBoolean("has_more"));
        try {
            Collection parseTopics = CircleTopicManager.parseTopics(jSONObject);
            TopicsTopFragment.a(this.b, this.a);
            TopicsTopFragment.b(this.b, false);
            if (this.a == 1) {
                TopicsTopFragment.b(this.b).refreshDone();
                TopicsTopFragment.c(this.b).clear();
                TopicsTopFragment.b(this.b).setLoadMoreEnable(true);
            }
            TopicsTopFragment.c(this.b).addAll(parseTopics);
            if (!TopicsTopFragment.d(this.b)) {
                TopicsTopFragment.e(this.b);
            }
            if (TopicsTopFragment.f(this.b)) {
                TopicsTopFragment.b(this.b).loadMoreDone(true);
            } else {
                TopicsTopFragment.b(this.b).setLoadMoreEnable(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        TopicsTopFragment.a(this.b);
        TopicsTopFragment.b(this.b, false);
        TopicsTopFragment.b(this.b).refreshDone();
        TopicsTopFragment.b(this.b).loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
