package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReportedPostList extends m {
    public static final int TYPE_HOT = 0;
    public static final int TYPE_NEW = 1;
    private long mOffset;
    private int mType = 1;
    private boolean more = false;
    public int post_report_count;
    public int proc_count;
    private long tid;

    public ReportedPostList(long j) {
        this.tid = j;
    }

    public void setRequestType(int i) {
        this.mType = i;
        this.mOffset = 0;
        this.more = false;
    }

    protected String getQueryUrl() {
        return a.b("/topic/post_reports");
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        super.fillJSONObjectHeaderInfo(jSONObject);
        try {
            jSONObject.put("tid", this.tid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean hasMore() {
        return this.more;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = false;
        if (0 == this.mOffset) {
            this._items.clear();
        }
        Collection arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    AbstractPost parseItem = parseItem(optJSONObject);
                    if (parseItem != null) {
                        arrayList.add(parseItem);
                    }
                }
            }
        }
        this._items.addAll(arrayList);
        this.post_report_count = jSONObject.optInt("post_report_count", 0);
        this.proc_count = jSONObject.optInt("proc_count", 0);
        if (jSONObject.optInt("more", 0) == 1) {
            z = true;
        }
        this.more = z;
        if (this.mType == 1) {
            this.mOffset = jSONObject.optLong("t");
        } else {
            this.mOffset = jSONObject.optLong("offset");
        }
        notifyListUpdate();
    }
}
