package qsbk.app.model;

import com.umeng.commonsdk.proguard.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.GroupRecommend.GroupRecommendObserver;

final class n implements SimpleCallBack {
    n() {
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            int i = jSONObject.getInt("updated_at");
            int i2 = jSONObject.getInt(g.az);
            ArrayList a = GroupRecommend.b(jSONArray);
            if (a == null) {
                onFailure(0, "数据加载失败");
                return;
            }
            Collections.shuffle(a);
            GroupRecommend.save(a);
            GroupRecommend.d(4);
            GroupRecommend.e(i + i2);
            GroupRecommend.getInstance().groups.clear();
            if (a.size() >= 5) {
                GroupRecommend.getInstance().groups = a.subList(0, 5);
            } else {
                GroupRecommend.getInstance().groups = a.subList(0, a.size());
            }
            if (GroupRecommend.listeners != null) {
                i = 0;
                while (i < GroupRecommend.listeners.size()) {
                    int i3;
                    WeakReference weakReference = (WeakReference) GroupRecommend.listeners.get(i);
                    if (weakReference == null || weakReference.get() == null) {
                        GroupRecommend.listeners.remove(i);
                        i3 = i - 1;
                    } else {
                        ((GroupRecommendObserver) weakReference.get()).onNewGroupRecommend(GroupRecommend.getInstance());
                        i3 = i;
                    }
                    i = i3 + 1;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
    }
}
