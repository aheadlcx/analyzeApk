package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicDetail implements Serializable {
    private static final String kKeyAttention = "atted";
    private static final String kKeyAttsTitle = "atts_title";
    private static final String kKeyBlocked = "blocked";
    private static final String kKeyBrief = "brief";
    private static final String kKeyHotStamp = "key_hot_stamp";
    private static final String kKeyPartner = "partners";
    private static final String kKeyPostCount = "posts";
    private static final String kKeyPrevIcon = "icon";
    private static final String kKeyTopicCover = "cover";
    private static final String kKeyTopicID = "id";
    private static final String kKeyTopicName = "topic";
    private static final long serialVersionUID = -8258939106380755712L;
    public String _attsTitle;
    public String _brief;
    private int _hasnewhot;
    private long _hotstamp;
    public boolean _isAttention;
    public int _isadm;
    private long _lastHotStamp;
    public int _partners;
    public int _postCount;
    public Topic _topic;
    public long _topicCoverID;
    public long _topicID;
    public String _topicName;
    public int blocked;
    public int hot_type;
    public int mUpTotalCount;
    public ArrayList<Member> mUppedMembers;
    public String next_list_cb;
    public List<c> postVisitableList;
    public List<Integer> publishTypes;
    public List<TopPostInfo> topPostInfos;
    public List<Post> topicPosts;

    public interface OnQueryTopicDetailFinishedListener {
        void onQueryTopicDetailFailed(String str);

        void onQueryTopicDetailFinished(int i, List<c> list, ArrayList<AbstractPost> arrayList, boolean z, long j);
    }

    public static class TopPostInfo implements Serializable {
        public long img_id;
        public long pid;
        public String text;

        public TopPostInfo(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.pid = jSONObject.optLong("pid", 0);
                this.text = jSONObject.optString("text", "");
                this.img_id = jSONObject.optLong("img_id", 0);
            }
        }
    }

    public TopicDetail() {
        this._isadm = 0;
        this._lastHotStamp = 0;
        this.mUppedMembers = new ArrayList();
        this.mUpTotalCount = 0;
        this._topicID = 0;
        this._topic = new Topic();
        this._topicName = "";
        this._brief = "";
        this._partners = 0;
        this._isAttention = false;
        this._attsTitle = "";
        this.publishTypes = new ArrayList();
        this.topPostInfos = new ArrayList();
        this.topicPosts = new ArrayList();
        this.postVisitableList = new ArrayList();
    }

    public TopicDetail(long j) {
        this();
        this._topicID = j;
        this._lastHotStamp = a.a().getLong(kKeyHotStamp + this._topicID, 0);
    }

    private void unserializeFrom(JSONObject jSONObject) {
        if (jSONObject != null) {
            this._topic = new Topic(jSONObject);
            this.blocked = jSONObject.optInt(kKeyBlocked);
            this._topicID = jSONObject.optLong("id");
            this._topicName = jSONObject.optString(kKeyTopicName);
            this._topicCoverID = jSONObject.optLong(kKeyTopicCover);
            this._postCount = jSONObject.optInt(kKeyPostCount);
            this._brief = jSONObject.optString(kKeyBrief);
            this._partners = jSONObject.optInt(kKeyPartner);
            this._isAttention = jSONObject.optInt(kKeyAttention) != 0;
            this._isadm = jSONObject.optInt("isadm");
            this._attsTitle = jSONObject.optString(kKeyAttsTitle);
        }
    }

    public boolean showNewFlag() {
        return this._hasnewhot == 1 && this._hotstamp > this._lastHotStamp;
    }

    public cn.htjyb.b.a topicCover() {
        return a.f().a(Type.kTopicCover280, this._topicCoverID);
    }

    public void query(final OnQueryTopicDetailFinishedListener onQueryTopicDetailFinishedListener, String str, long j, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", a.g().a());
            jSONObject.put("tid", this._topicID);
            jSONObject.put("from", str);
            jSONObject.put("pid", j);
            jSONObject.put("hotstamp", this._lastHotStamp);
            jSONObject.put("click_cb", str2);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(1);
            jSONArray.put(2);
            jSONArray.put(3);
            jSONObject.put("c_types", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/detail_v2"), a.d(), jSONObject, new d.a() {
            public void onTaskFinish(d dVar) {
                b.a aVar = dVar.c;
                if (aVar.a) {
                    int i;
                    long optLong;
                    TopicDetail.this.unserializeFrom(aVar.c.optJSONObject(TopicDetail.kKeyTopicName));
                    JSONArray optJSONArray = aVar.c.optJSONArray("list");
                    for (i = 0; i < optJSONArray.length(); i++) {
                        TopicDetail.this.postVisitableList.add(HolderCreator.a(optJSONArray.optJSONObject(i)));
                    }
                    ArrayList arrayList = new ArrayList();
                    for (i = 0; i < optJSONArray.length(); i++) {
                        Post post = new Post(optJSONArray.optJSONObject(i));
                        arrayList.add(post);
                        TopicDetail.this.topicPosts.add(post);
                    }
                    optJSONArray = aVar.c.optJSONArray("ugcvideo_items");
                    if (optJSONArray != null) {
                        for (i = optJSONArray.length() - 1; i >= 0; i--) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
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
                    int optInt2 = aVar.c.optInt("posttype");
                    TopicDetail.this.next_list_cb = aVar.c.optString("next_cb", "");
                    TopicDetail.this.hot_type = aVar.c.optInt("hot_type", 1);
                    boolean z = aVar.c.optInt("more", 0) == 1;
                    if (optInt2 == 1) {
                        optLong = aVar.c.optLong("t");
                    } else {
                        optLong = aVar.c.optLong("offset");
                    }
                    JSONArray optJSONArray2 = aVar.c.optJSONArray("publish_ctypes");
                    if (optJSONArray2 != null) {
                        TopicDetail.this.publishTypes = new ArrayList(JSON.parseArray(optJSONArray2.toString(), Integer.class));
                    }
                    optJSONArray2 = aVar.c.optJSONArray("attedusers");
                    if (optJSONArray2 != null) {
                        TopicDetail.this.mUppedMembers = new ArrayList(JSON.parseArray(optJSONArray2.toString(), Member.class));
                    }
                    optJSONArray2 = aVar.c.optJSONArray("top_posts");
                    if (optJSONArray2 != null) {
                        TopicDetail.this.topPostInfos = new ArrayList(JSON.parseArray(optJSONArray2.toString(), TopPostInfo.class));
                    }
                    TopicDetail.this.mUpTotalCount = aVar.c.optInt("attedcnt");
                    TopicDetail.this._hasnewhot = aVar.c.optInt("hasnewhot");
                    TopicDetail.this._hotstamp = aVar.c.optLong("hotstamp");
                    if (onQueryTopicDetailFinishedListener != null) {
                        onQueryTopicDetailFinishedListener.onQueryTopicDetailFinished(optInt2, TopicDetail.this.postVisitableList, arrayList, z, optLong);
                    }
                } else if (onQueryTopicDetailFinishedListener != null) {
                    onQueryTopicDetailFinishedListener.onQueryTopicDetailFailed(aVar.c());
                }
            }
        }).b();
    }

    public void saveStamp() {
        a.a().edit().putLong(kKeyHotStamp + this._topicID, this._hotstamp).apply();
    }
}
