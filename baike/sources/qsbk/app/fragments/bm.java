package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.ToastAndDialog;

class bm implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicsFragment b;

    bm(CircleTopicsFragment circleTopicsFragment, int i) {
        this.b = circleTopicsFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (!this.b.isDetached() && this.b.getActivity() != null) {
            CircleTopicsFragment.o(this.b);
            CircleTopicsFragment.b(this.b, jSONObject.optBoolean("has_more"));
            try {
                Collection parseTopics = CircleTopicManager.parseTopics(jSONObject);
                CircleTopicsFragment.b(this.b, this.a);
                CircleTopicsFragment.c(this.b, false);
                if (this.a == 1) {
                    CircleTopicsFragment.d(this.b).refreshDone();
                    CircleTopicsFragment.p(this.b).clear();
                    CircleTopicsFragment.d(this.b).setLoadMoreEnable(true);
                }
                CircleTopicsFragment.p(this.b).addAll(parseTopics);
                if (!CircleTopicsFragment.q(this.b)) {
                    CircleTopicsFragment.i(this.b);
                }
                if (CircleTopicsFragment.h(this.b)) {
                    CircleTopicsFragment.d(this.b).loadMoreDone(true);
                } else {
                    CircleTopicsFragment.d(this.b).setLoadMoreEnable(false);
                }
                if (this.b.getUserVisibleHint() && this.a == 1 && !CircleTopicsFragment.n(this.b)) {
                    ToastAndDialog.makePositiveToast(this.b.getActivity(), "目前为止最新内容，请食用").show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onFailure(-1, "数据加载失败");
            }
        }
    }

    public void onFailure(int i, String str) {
        CircleTopicsFragment.o(this.b);
        CircleTopicsFragment.c(this.b, false);
        CircleTopicsFragment.d(this.b).refreshDone();
        CircleTopicsFragment.d(this.b).loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
