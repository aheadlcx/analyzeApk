package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.e;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.c.d;
import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicInvolvedRankMemberList extends e<d> {
    private String requestTag = "weekly";
    private long tid;

    public TopicInvolvedRankMemberList(long j) {
        this.tid = j;
    }

    public void setRequestTag(String str) {
        this.requestTag = str;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/rank");
    }

    protected d parseItem(JSONObject jSONObject) {
        return new d(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("tid", this.tid);
            jSONObject.put(AIUIConstant.KEY_TAG, this.requestTag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        int i = 0;
        if (0 == this._offset) {
            this._items.clear();
        }
        if (jSONObject.optInt("more") != 1) {
            z = false;
        }
        this.mServerMore = z;
        this.mServerOffset = (long) jSONObject.optInt("offset");
        JSONArray optJSONArray = jSONObject.optJSONArray("admins");
        ArrayList arrayList = null;
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList2.add(new d(optJSONArray.optJSONObject(i2)));
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                dVar.d = 0;
                this._items.add(dVar);
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
        if (optJSONArray2 != null) {
            while (i < optJSONArray2.length()) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                if (!(optJSONObject == null || parseItem(optJSONObject) == null)) {
                    this._items.add(parseItem(optJSONObject));
                }
                i++;
            }
        }
        notifyListUpdate();
    }
}
