package qsbk.app.utils;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.datastore.GroupStore;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupItem;

final class al implements SimpleCallBack {
    ArrayList<GroupInfo> a = new ArrayList();
    final /* synthetic */ String b;
    final /* synthetic */ GroupStore c;

    al(String str, GroupStore groupStore) {
        this.b = str;
        this.c = groupStore;
    }

    public void onSuccess(JSONObject jSONObject) {
        SharePreferenceUtils.setSharePreferencesValue("get_joined_group_" + this.b, true);
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.a.add(new GroupInfo(optJSONObject));
                }
            }
            try {
                this.c.deleteJoinedGroup();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (this.a.size() > 0) {
                this.c.insert(this.a);
            }
            JoinedGroupGetter.b(true, this.a, 0, null);
            ArrayList load = GroupRecommend.load();
            if (load != null) {
                Iterator it = load.iterator();
                while (it.hasNext()) {
                    GroupItem groupItem = (GroupItem) it.next();
                    Iterator it2 = this.a.iterator();
                    while (it2.hasNext()) {
                        if (((GroupInfo) it2.next()).id == groupItem.id) {
                            groupItem.joinStatus = 2;
                        }
                    }
                }
                GroupRecommend.save(load);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        this.c.deleteJoinedGroup();
        JoinedGroupGetter.b(false, null, i, str);
    }
}
