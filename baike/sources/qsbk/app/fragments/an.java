package qsbk.app.fragments;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.ToastAndDialog;

class an implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicFragment b;

    an(CircleTopicFragment circleTopicFragment, int i) {
        this.b = circleTopicFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.b();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            this.b.c = jSONObject.optBoolean("has_more");
            if (this.b.b == 1) {
                this.b.g.clear();
                this.b.d.refreshDone();
                JSONObject optJSONObject = jSONObject.optJSONObject("topic");
                if (optJSONObject != null) {
                    CircleTopic parseJson = CircleTopic.parseJson(optJSONObject);
                    if (parseJson != null) {
                        this.b.l = parseJson;
                        Collection arrayList = new ArrayList(1);
                        arrayList.add(this.b.l);
                        CircleTopicManager.notifyTopicUpdate(arrayList);
                    }
                }
            } else {
                this.b.d.loadMoreDone(true);
            }
            this.b.m = this.b.g.size();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object parseJson2 = CircleArticle.parseJson(jSONArray.optJSONObject(i));
                if (!(parseJson2 == null || this.b.g.contains(parseJson2))) {
                    this.b.g.add(parseJson2);
                }
            }
            this.b.b = this.a;
            this.b.j = false;
            if (this.b.c) {
                this.b.d.loadMoreDone(true);
            } else {
                this.b.d.setLoadMoreEnable(false);
            }
            this.b.h.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        this.b.b();
        this.b.j = false;
        this.b.d.refreshDone();
        this.b.d.loadMoreDone(false);
        if (!TextUtils.isEmpty(str) && this.b.getActivity() != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
