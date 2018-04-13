package qsbk.app.activity;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupBriefInfo;

class od implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ GroupRankFragment b;

    od(GroupRankFragment groupRankFragment, int i) {
        this.b = groupRankFragment;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = -1;
        int i2 = 0;
        GroupRankFragment.a(this.b, null);
        if (this.b.getActivity() != null) {
            GroupRankFragment.c(this.b);
            GroupRankFragment.a(this.b).getFirstVisiblePosition();
            GroupRankFragment.d(this.b).refreshDone();
            try {
                int i3;
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                boolean optBoolean = jSONObject.optBoolean("has_more");
                if (jSONObject.optInt("has_more") != 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i3 |= optBoolean;
                int length = jSONArray.length();
                Collection arrayList = new ArrayList();
                while (i2 < length) {
                    arrayList.add(new GroupBriefInfo(jSONArray.getJSONObject(i2)));
                    i2++;
                }
                if (this.a == -1) {
                    i = jSONObject.optInt("cur_page");
                    i2 = jSONObject.optInt("first_rank");
                    GroupRankFragment.a(this.b, i);
                    GroupRankFragment.b(this.b, i + 1);
                    GroupRankFragment.c(this.b, i2);
                    i = (GroupRankFragment.e(this.b) - GroupRankFragment.f(this.b)) - 2;
                    GroupRankFragment.g(this.b).addAll(arrayList);
                } else if (this.a == GroupRankFragment.h(this.b) - 1) {
                    GroupRankFragment.a(this.b, this.a);
                    GroupRankFragment.c(this.b, GroupRankFragment.f(this.b) - length);
                    i = length - 1;
                    GroupRankFragment.g(this.b).addAll(0, arrayList);
                } else if (this.a == 1) {
                    GroupRankFragment.g(this.b).clear();
                    GroupRankFragment.b(this.b, 2);
                    GroupRankFragment.c(this.b, 0);
                    GroupRankFragment.g(this.b).addAll(arrayList);
                } else {
                    GroupRankFragment.b(this.b, this.a + 1);
                    GroupRankFragment.d(this.b).loadMoreDone(true);
                    GroupRankFragment.g(this.b).addAll(arrayList);
                }
                GroupRankFragment.b(this.b).notifyDataSetChanged();
                if (i3 == 0) {
                    GroupRankFragment.d(this.b).setLoadMoreEnable(false);
                } else {
                    GroupRankFragment.d(this.b).setLoadMoreEnable(true);
                }
                if (i >= 0) {
                    GroupRankFragment.a(this.b).setSelection(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onFailure("", "数据加载失败");
            }
        }
    }

    public void onFailure(String str, String str2) {
        GroupRankFragment.a(this.b, null);
        if (this.b.getActivity() != null) {
            GroupRankFragment.c(this.b);
            if (this.a <= GroupRankFragment.h(this.b)) {
                GroupRankFragment.d(this.b).refreshDone();
            } else {
                GroupRankFragment.d(this.b).setLoadMoreEnable(false);
            }
        }
    }
}
