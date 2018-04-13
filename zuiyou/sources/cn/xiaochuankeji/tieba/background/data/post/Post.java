package cn.xiaochuankeji.tieba.background.data.post;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.voice.AudioJson;
import cn.xiaochuankeji.tieba.ui.utils.d;
import com.izuiyou.a.a.b;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post extends AbstractPost implements Serializable {
    public static final int IMAGE_LOAD_FETCHING = 1;
    public static final int IMAGE_LOAD_ONE_SHOT = 0;
    public static final int IMAGE_LOAD_SUCCESS = 2;
    public static final int POST_LINK_TYPE_DEFAULT = 0;
    public static final int POST_LINK_TYPE_H5VIDEO = 2;
    public static final int POST_LINK_TYPE_WECHAT = 1;
    public static final int POST_LINK_TYPE_WY = 3;
    public static final int POST_STATUS_EDITOR_RECOMMEND = 3;
    public static final int POST_TYPE_DEFAULT = 0;
    public static final int POST_TYPE_H5VIDEO = 2;
    public static final int POST_TYPE_REGISTER = 3;
    public static final int POST_TYPE_WEBPAGE = 1;
    private static final long serialVersionUID = -6621425110951126659L;
    public long _ID;
    public int _commentCount;
    public long _createTime;
    public final ArrayList<ServerImage> _imgList;
    public int _likeCount;
    public int _liked;
    public Member _member;
    public String _postContent;
    public int _share;
    public Topic _topic;
    public AudioJson audio;
    public String campaignId;
    public final ArrayList<Comment> comments;
    private boolean favored;
    public int gray;
    public boolean hasUpdate;
    public int imageLoadState;
    public final HashMap<Long, ServerVideo> imgVideos;
    public PostLink link;
    public int postType;
    public PostVote postVote;
    public int status;
    public Webpage webpage;

    public static class PostLink implements Serializable {
        private static final long serialVersionUID = -6234672976715874797L;
        public long imgId;
        public String link;
        public String title;

        public PostLink(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.link = jSONObject.optString("link");
                this.imgId = jSONObject.optLong("imgid");
                this.title = jSONObject.optString("title");
            }
        }

        public JSONObject serializeTo() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("link", this.link);
            jSONObject.put("imgid", this.imgId);
            jSONObject.put("title", this.title);
            return jSONObject;
        }
    }

    public static class PostVote implements Serializable {
        private static final long serialVersionUID = 4174837301708715168L;
        private int id;
        private ArrayList<PostVoteItem> voteItems = new ArrayList();
        private ArrayList<Long> voteMids = new ArrayList();
        private String votedItem;

        public PostVote(JSONObject jSONObject) {
            int i = 0;
            this.id = jSONObject.optInt("id");
            this.votedItem = jSONObject.optString("voted_item");
            JSONArray optJSONArray = jSONObject.optJSONArray("opt");
            this.voteItems = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    try {
                        this.voteItems.add(new PostVoteItem(optJSONArray.getJSONObject(i2)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (jSONObject.has("voted_mids")) {
                JSONArray optJSONArray2 = jSONObject.optJSONArray("voted_mids");
                while (i < optJSONArray2.length()) {
                    this.voteMids.add(Long.valueOf(optJSONArray2.optLong(i)));
                    i++;
                }
            }
            if (this.votedItem != null && this.votedItem.length() > 0 && !a.g().d()) {
                if (this.voteMids != null) {
                    this.voteMids = new ArrayList();
                }
                this.voteMids.add(Long.valueOf(a.g().c()));
            }
        }

        public JSONObject serializeTo() throws JSONException {
            int i = 0;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.voteItems.size(); i2++) {
                jSONArray.put(((PostVoteItem) this.voteItems.get(i2)).serializeTo());
            }
            jSONObject.put("opt", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            while (i < this.voteMids.size()) {
                jSONArray2.put(this.voteMids.get(i));
                i++;
            }
            jSONObject.put("voted_mids", jSONArray2);
            jSONObject.put("voted_item", this.votedItem);
            return jSONObject;
        }

        public int getVoteCount() {
            int i = 0;
            for (int i2 = 0; i2 < this.voteItems.size(); i2++) {
                i += ((PostVoteItem) this.voteItems.get(i2)).getPollCount();
            }
            return i;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void addVoteMid(long j) {
            this.voteMids.add(Long.valueOf(j));
        }

        public void addVoteMids(ArrayList<Long> arrayList) {
            if (arrayList != null) {
                this.voteMids.addAll(arrayList);
            }
        }

        public ArrayList<Long> getVoteMids() {
            return this.voteMids;
        }

        public ArrayList<PostVoteItem> getVoteItems() {
            return this.voteItems;
        }

        public void setVoteItems(ArrayList<PostVoteItem> arrayList) {
            this.voteItems = arrayList;
        }

        public String getVotedItem() {
            return this.votedItem;
        }

        public void setVotedItem(String str) {
            this.votedItem = str;
        }
    }

    public static class PostVoteItem implements Serializable {
        private static final long serialVersionUID = 6810290254647166665L;
        private String id;
        private ArrayList<Member> members;
        private String name;
        private int pollCount;

        public PostVoteItem(JSONObject jSONObject) {
            this.id = jSONObject.optString("id");
            this.pollCount = jSONObject.optInt("pollcn");
            this.name = jSONObject.optString("name");
            try {
                if (jSONObject.has("poll") && (jSONObject.get("poll") instanceof JSONArray)) {
                    JSONArray jSONArray = jSONObject.getJSONArray("poll");
                    this.members = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        this.members.add(new Member(jSONArray.getJSONObject(i)));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public JSONObject serializeTo() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put("pollcn", this.pollCount);
            jSONObject.put("name", this.name);
            if (this.members != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < this.members.size(); i++) {
                    jSONArray.put(((Member) this.members.get(i)).serializeTo());
                }
                jSONObject.put("poll", jSONArray);
            }
            return jSONObject;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public int getPollCount() {
            return this.pollCount;
        }

        public void setPollCount(int i) {
            this.pollCount = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public ArrayList<Member> getMembers() {
            return this.members;
        }

        public void setMembers(ArrayList<Member> arrayList) {
            this.members = arrayList;
        }
    }

    public static class Webpage implements Serializable {
        public String author;
        public String desc;
        public int linkType;
        public String thumbUrl;
        public String title;
        public String url;

        public Webpage(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.url = jSONObject.optString("url");
                this.thumbUrl = jSONObject.optString("thumburl");
                this.title = jSONObject.optString("title");
                this.desc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                this.author = jSONObject.optString("author");
                this.linkType = jSONObject.optInt("url_type");
            }
        }

        public JSONObject serializeTo() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", this.url);
            jSONObject.put("thumburl", this.thumbUrl);
            jSONObject.put("title", this.title);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, this.desc);
            jSONObject.put("author", this.author);
            jSONObject.put("url_type", this.linkType);
            return jSONObject;
        }

        public String toString() {
            return String.format("[link_type:%d, url:%s, title:%s]", new Object[]{Integer.valueOf(this.linkType), this.url, this.title});
        }
    }

    public Post() {
        this._imgList = new ArrayList();
        this.imgVideos = new HashMap();
        this.comments = new ArrayList();
        this._topic = new Topic();
        this._member = new Member();
        this._share = -1;
        this.hasUpdate = false;
        this._ID = 0;
        this._commentCount = 0;
        this._likeCount = 0;
        this.postType = 0;
        this._postContent = "";
        this.gray = 0;
        this.c_type = 1;
    }

    public Post(long j) {
        this();
        this._ID = j;
    }

    public Post(JSONObject jSONObject) {
        this();
        parseBaseInfo(jSONObject);
    }

    public int classType() {
        return 0;
    }

    public long getMemberId() {
        return this._member.getId();
    }

    public void setFollowStatus(int i) {
        this._member.setAtted(i);
    }

    public void parseBaseInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            int length;
            super.parseBaseInfo(jSONObject);
            this._ID = jSONObject.optLong("id");
            this._commentCount = jSONObject.optInt("reviews");
            this._likeCount = jSONObject.optInt("likes");
            this.postType = jSONObject.optInt("type", 0);
            this.campaignId = jSONObject.optString("campaign_id");
            this._createTime = jSONObject.optLong("ct");
            this.status = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
            this._postContent = jSONObject.optString("content");
            this._postContent = this._postContent.trim();
            this._liked = jSONObject.optInt("liked", 0);
            this._share = jSONObject.optInt("share", -1);
            this.favored = jSONObject.optInt("favored", 0) == 1;
            this.gray = jSONObject.optInt("gray", 0);
            this.c_type = jSONObject.optInt("c_type", 1);
            if (jSONObject.has("link")) {
                this.link = new PostLink(jSONObject.optJSONObject("link"));
            }
            if (jSONObject.has("webpage")) {
                this.webpage = new Webpage(jSONObject.optJSONObject("webpage"));
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("imgs");
            this._imgList.clear();
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                this._imgList.add(new ServerImage(optJSONArray.optJSONObject(i)));
                i++;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("videos");
            if (optJSONObject != null) {
                Iterator it = this._imgList.iterator();
                while (it.hasNext()) {
                    ServerImage serverImage = (ServerImage) it.next();
                    long j = serverImage.postImageId;
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(String.valueOf(j));
                    if (optJSONObject2 != null) {
                        this.imgVideos.put(Long.valueOf(j), new ServerVideo(optJSONObject2));
                        serverImage.parseVideoJSONObject(optJSONObject2);
                    }
                }
            }
            this._topic = new Topic(jSONObject.optJSONObject("topic"));
            JSONObject optJSONObject3 = jSONObject.optJSONObject("member");
            optJSONObject = jSONObject.optJSONObject("vote");
            if (optJSONObject != null) {
                b.b(jSONObject.toString());
                this.postVote = new PostVote(optJSONObject);
            }
            if (optJSONObject3 != null) {
                this._member = new Member(optJSONObject3);
            }
            this.comments.clear();
            optJSONArray = jSONObject.optJSONArray("god_reviews");
            if (optJSONArray != null) {
                length = optJSONArray.length();
                for (i = 0; i < length; i++) {
                    this.comments.add(new Comment(optJSONArray.optJSONObject(i)));
                }
            }
            optJSONArray = jSONObject.optJSONArray("fine_reviews");
            if (optJSONArray != null) {
                length = optJSONArray.length();
                for (i = 0; i < length; i++) {
                    this.comments.add(new Comment(optJSONArray.optJSONObject(i)));
                }
            }
            this.imageLoadState = jSONObject.optInt("image_load_state", 0);
        }
    }

    public JSONObject serializeTo() throws JSONException {
        Iterator it;
        JSONObject serializeTo = super.serializeTo();
        JSONArray jSONArray = new JSONArray();
        serializeTo.put("id", this._ID);
        serializeTo.put("reviews", this._commentCount);
        serializeTo.put("likes", this._likeCount);
        serializeTo.put("type", this.postType);
        serializeTo.put("campaign_id", this.campaignId);
        serializeTo.put("ct", this._createTime);
        serializeTo.put(NotificationCompat.CATEGORY_STATUS, this.status);
        serializeTo.put("content", this._postContent);
        serializeTo.put("liked", this._liked);
        serializeTo.put("c_type", this.c_type);
        Iterator it2 = this._imgList.iterator();
        while (it2.hasNext()) {
            jSONArray.put(((ServerImage) it2.next()).serializeTo());
        }
        serializeTo.put("imgs", jSONArray);
        serializeTo.put("image_load_state", this.imageLoadState);
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : this.imgVideos.entrySet()) {
            jSONObject.put(String.valueOf(((Long) entry.getKey()).longValue()), ((ServerVideo) entry.getValue()).serializeTo());
        }
        serializeTo.put("videos", jSONObject);
        serializeTo.put("topic", this._topic.serializeTo());
        serializeTo.put("member", this._member.serializeTo());
        serializeTo.put("share", this._share);
        serializeTo.put("gray", this.gray);
        if (this.postVote != null) {
            serializeTo.put("vote", this.postVote.serializeTo());
        }
        serializeTo.put("favored", this.favored ? 1 : 0);
        if (this.link != null) {
            serializeTo.put("link", this.link.serializeTo());
        }
        if (this.webpage != null) {
            serializeTo.put("webpage", this.webpage.serializeTo());
        }
        if (this.comments.size() > 0) {
            jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            it = this.comments.iterator();
            while (it.hasNext()) {
                Comment comment = (Comment) it.next();
                if (comment.isGod()) {
                    jSONArray.put(comment.serializeTo());
                } else {
                    jSONArray2.put(comment.serializeTo());
                }
            }
            serializeTo.put("god_reviews", jSONArray);
            serializeTo.put("fine_reviews", jSONArray2);
        }
        return serializeTo;
    }

    public boolean hasImage() {
        return this._imgList != null && this._imgList.size() > 0;
    }

    public boolean isSingleImage() {
        return this._imgList != null && this._imgList.size() == 1;
    }

    public boolean isFavored() {
        return this.favored;
    }

    public void setFavored(boolean z) {
        this.favored = z;
    }

    public cn.htjyb.b.a getLinkData(int i) {
        if (this.link == null) {
            return null;
        }
        return a.f().a(Type.kLinkPic228White, this.link.imgId);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!Post.class.isInstance(obj)) {
            return false;
        }
        if (((Post) obj)._ID != this._ID) {
            return false;
        }
        return true;
    }

    public ServerVideo getImgVideoBy(long j) {
        return (ServerVideo) this.imgVideos.get(Long.valueOf(j));
    }

    public boolean isVideoPost() {
        return this.imgVideos.size() != 0;
    }

    public boolean hasGifInImages() {
        if (this._imgList.isEmpty()) {
            return false;
        }
        Iterator it = this._imgList.iterator();
        while (it.hasNext()) {
            ServerImage serverImage = (ServerImage) it.next();
            if (!serverImage.isGif()) {
                if (serverImage.isMP4()) {
                }
            }
            return true;
        }
        return false;
    }

    public void copyLink() {
        copyLink(null);
    }

    public void copyLink(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            stringBuilder.append("#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>");
        } else {
            stringBuilder.append(str);
        }
        stringBuilder.append(' ');
        stringBuilder.append(cn.xiaochuankeji.tieba.background.utils.d.a.a(this));
        stringBuilder.append("?zy_to=applink&to=applink");
        d.a(stringBuilder.toString());
        g.a("复制成功");
    }

    public void copyCommentsLink() {
        d.a("#最右#请你围观一条神奇的评论，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.a(this) + "?zy_to=applink&to=applink");
        g.a("复制成功");
    }
}
