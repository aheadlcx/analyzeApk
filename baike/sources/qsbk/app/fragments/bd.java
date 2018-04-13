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

class bd implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicsFragment b;

    bd(CircleTopicsFragment circleTopicsFragment, int i) {
        this.b = circleTopicsFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        TimeDelta timeDelta = new TimeDelta();
        CircleTopicsFragment.a(this.b, true);
        CircleTopicsFragment.o(this.b);
        CircleTopicsFragment.a(this.b, this.a);
        boolean optBoolean = jSONObject.optBoolean("has_more");
        if (this.a == 1) {
            CircleTopicsFragment.c(this.b, jSONObject.optInt("total"));
            CircleTopicsFragment.d(this.b).setLoadMoreEnable(true);
            CircleTopicsFragment.t(this.b).clear();
        }
        try {
            Collection arrayList = new ArrayList();
            if (this.a == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("topic");
                if (optJSONObject != null) {
                    CircleTopic parseJson = CircleTopic.parseJson(optJSONObject);
                    if (parseJson != null) {
                        if (!(CircleTopicsFragment.k(this.b) && parseJson.isClocked())) {
                            arrayList.add(parseJson);
                        }
                        CircleTopicManager.notifyTopicUpdate(arrayList);
                    }
                }
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                CircleTopic parseJson2 = CircleTopic.parseJson(jSONArray.getJSONObject(i));
                if (!(parseJson2 == null || arrayList.contains(parseJson2))) {
                    CircleTopicsFragment.e(this.b, false);
                    if (!CircleTopicsFragment.k(this.b) || !parseJson2.isClocked()) {
                        arrayList.add(parseJson2);
                    }
                }
            }
            if (arrayList.size() > 0) {
                CircleTopicManager.notifyTopicUpdate(arrayList);
            }
            CircleTopicsFragment.e(this.b, true);
            CircleTopicsFragment.f(this.b, false);
            CircleTopicsFragment.a(this.b, null);
            CircleTopicsFragment.d(this.b).refreshDone();
            CircleTopicsFragment.t(this.b).addAll(arrayList);
            if (optBoolean) {
                CircleTopicsFragment.d(this.b).loadMoreDone(true);
            } else {
                CircleTopicsFragment.d(this.b).setLoadMoreEnable(false);
            }
            CircleTopicsFragment.i(this.b);
            if (this.a == 1) {
                CircleTopicsFragment.c(this.b).setSelection(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        CircleTopicsFragment.o(this.b);
        CircleTopicsFragment.f(this.b, false);
        CircleTopicsFragment.a(this.b, null);
        CircleTopicsFragment.d(this.b).refreshDone();
        if (this.a == 1) {
            CircleTopicsFragment.d(this.b).setLoadMoreEnable(false);
        } else {
            CircleTopicsFragment.d(this.b).loadMoreDone(false);
        }
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
