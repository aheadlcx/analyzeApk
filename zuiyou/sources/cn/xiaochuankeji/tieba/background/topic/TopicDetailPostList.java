package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.background.utils.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicDetailPostList extends m {
    public static final int HOT_NEW_RECOMMEND = 2;
    public static final int HOT_OLD_RECOMMEND = 1;
    public static final int TYPE_HOT = 0;
    public static final int TYPE_NEW = 1;
    private int hotType = 1;
    private long mOffset;
    private int mType = 1;
    private boolean more = false;
    private String next_list_cb = "";
    private int refreshItemCount;
    private long tid;

    public TopicDetailPostList(long j, String str, int i) {
        this.tid = j;
        this.next_list_cb = str;
        this.hotType = i;
        this.refreshItemCount = 0;
    }

    public void setRequestType(int i) {
        this.mType = i;
        this.mOffset = 0;
        this.more = false;
    }

    public void initWithPostList(ArrayList<AbstractPost> arrayList, boolean z, long j) {
        if (this._items != null) {
            this._items.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AbstractPost abstractPost = (AbstractPost) it.next();
            if (abstractPost != null) {
                this._items.add(abstractPost);
            }
        }
        this.mOffset = j;
        this.more = z;
        notifyListUpdate();
        notifyQueryFinish(true, "");
        if (itemCount() == 0) {
            refresh();
        }
    }

    protected String getQueryUrl() {
        if (this.mType == 1) {
            return a.b("/topic/posts");
        }
        return a.b("/topic/hotposts_new");
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        super.fillJSONObjectHeaderInfo(jSONObject);
        try {
            jSONObject.put("tid", this.tid);
            if (this.mType == 1) {
                jSONObject.put("t", this.mOffset);
                h.a(BaseApplication.getAppContext(), "zy_event_topicdetail_page", "新帖请求次数");
            } else {
                jSONObject.put("offset", this.mOffset);
                h.a(BaseApplication.getAppContext(), "zy_event_topicdetail_page", "热帖请求次数");
            }
            jSONObject.put("next_list_cb", this.next_list_cb);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean hasMore() {
        return this.more;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        int i;
        JSONObject optJSONObject;
        boolean z = false;
        if (0 == this.mOffset && (this.mType == 1 || this.hotType == 1)) {
            this._items.clear();
        }
        Collection arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        this.refreshItemCount = optJSONArray.length();
        if (optJSONArray != null) {
            for (i = 0; i < optJSONArray.length(); i++) {
                optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    AbstractPost parseItem = parseItem(optJSONObject);
                    if (parseItem != null) {
                        arrayList.add(parseItem);
                    }
                }
            }
        }
        optJSONArray = jSONObject.optJSONArray("ugcvideo_items");
        if (optJSONArray != null) {
            for (i = optJSONArray.length() - 1; i >= 0; i--) {
                optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("ugcvideo");
                    int optInt = optJSONObject.optInt("pos", 0);
                    if (optJSONObject2 != null) {
                        Moment moment = new Moment(optJSONObject2);
                        if (optInt >= 0 && optInt <= arrayList.size()) {
                            arrayList.add(optInt, moment);
                        }
                    }
                }
            }
        }
        if (this.mType == 1) {
            this._items.addAll(arrayList);
        } else if (isQueryMore()) {
            this._items.addAll(arrayList);
        } else {
            this._items.addAll(0, arrayList);
        }
        if (jSONObject.optInt("more", 0) == 1) {
            z = true;
        }
        this.more = z;
        if (this.mType == 1) {
            this.mOffset = jSONObject.optLong("t");
        } else {
            this.mOffset = jSONObject.optLong("offset");
        }
        this.next_list_cb = jSONObject.optString("next_list_cb", "");
        notifyListUpdate();
    }

    public void refresh() {
        this.mOffset = 0;
        this._offset = 0;
        super.refresh();
    }

    public int getRefreshItemCount() {
        return this.refreshItemCount;
    }
}
