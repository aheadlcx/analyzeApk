package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.ToastAndDialog;

class le implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ TopicsTopFragment b;

    le(TopicsTopFragment topicsTopFragment, int i) {
        this.b = topicsTopFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        TimeDelta timeDelta = new TimeDelta();
        TopicsTopFragment.c(this.b, true);
        TopicsTopFragment.a(this.b);
        TopicsTopFragment.b(this.b, this.a);
        boolean optBoolean = jSONObject.optBoolean("has_more");
        if (this.a == 1) {
            TopicsTopFragment.c(this.b, jSONObject.optInt("total"));
            TopicsTopFragment.b(this.b).setLoadMoreEnable(true);
            TopicsTopFragment.g(this.b).clear();
        }
        try {
            Collection arrayList = new ArrayList();
            if (this.a == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("topic");
                if (optJSONObject != null) {
                    CircleTopic parseJson = CircleTopic.parseJson(optJSONObject);
                    if (parseJson != null) {
                        arrayList.add(parseJson);
                        CircleTopicManager.notifyTopicUpdate(arrayList);
                    }
                }
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                CircleTopic parseJson2 = CircleTopic.parseJson(jSONArray.getJSONObject(i));
                if (!(parseJson2 == null || arrayList.contains(parseJson2))) {
                    TopicsTopFragment.d(this.b, false);
                    arrayList.add(parseJson2);
                }
            }
            if (arrayList.size() > 0) {
                CircleTopicManager.notifyTopicUpdate(arrayList);
            }
            TopicsTopFragment.d(this.b, true);
            TopicsTopFragment.e(this.b, false);
            TopicsTopFragment.a(this.b, null);
            TopicsTopFragment.b(this.b).refreshDone();
            TopicsTopFragment.g(this.b).addAll(arrayList);
            if (optBoolean) {
                TopicsTopFragment.b(this.b).loadMoreDone(true);
            } else {
                TopicsTopFragment.b(this.b).setLoadMoreEnable(false);
            }
            TopicsTopFragment.e(this.b);
            if (this.a == 1) {
                TopicsTopFragment.h(this.b).setSelection(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        TopicsTopFragment.a(this.b);
        TopicsTopFragment.e(this.b, false);
        TopicsTopFragment.a(this.b, null);
        TopicsTopFragment.b(this.b).refreshDone();
        if (this.a == 1) {
            TopicsTopFragment.b(this.b).setLoadMoreEnable(false);
        } else {
            TopicsTopFragment.b(this.b).loadMoreDone(false);
        }
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
