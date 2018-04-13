package cn.xiaochuankeji.tieba.ui.topic.data;

import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.recommend.CommentBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerVideoBean;
import cn.xiaochuankeji.tieba.json.recommend.VoteInfoBean;
import cn.xiaochuankeji.tieba.json.recommend.WebPageBean;
import cn.xiaochuankeji.tieba.json.topic.TopicInfoBean;
import cn.xiaochuankeji.tieba.json.voice.AudioJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PostDataBean implements c, Serializable {
    @JSONField(name = "audio")
    public AudioJson audio;
    @JSONField(name = "c_type")
    public int c_type;
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "favored")
    public int favored;
    @JSONField(name = "god_review")
    public CommentBean godReview;
    @JSONField(name = "god_reviews")
    public List<CommentBean> godReviews;
    @JSONField(name = "gray")
    public int gray;
    public boolean hasUpdate = false;
    @JSONField(name = "imgs")
    public List<ServerImageBean> images;
    @JSONField(name = "liked")
    public int isLiked;
    @JSONField(name = "likes")
    public int likeCount;
    @JSONField(name = "liken")
    public int liken;
    @JSONField(name = "member")
    public MemberInfoBean member;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "my_reviews")
    public List<CommentBean> myReviews;
    @JSONField(name = "otid")
    public long oldTopicId;
    @JSONField(name = "id")
    public long postId;
    @JSONField(name = "type")
    public int postType;
    @JSONField(name = "reviews")
    public int reviewCount;
    @JSONField(name = "share")
    public int shareCount;
    @JSONField(name = "status")
    public int status;
    @JSONField(name = "topic")
    public TopicInfoBean topic;
    @JSONField(name = "videos")
    public Map<String, ServerVideoBean> videoJsons;
    @JSONField(name = "vote")
    public VoteInfoBean voteInfo;
    @JSONField(name = "webpage")
    public WebPageBean webPage;

    public long getCreateTime() {
        return this.createTime;
    }

    public int localPostType() {
        if (this.c_type == 2) {
            return 2;
        }
        if (this.c_type == 3) {
            return 3;
        }
        return 1;
    }

    public int getShareNum() {
        return this.shareCount;
    }

    public long getId() {
        return this.postId;
    }

    public long getMemberId() {
        return this.member.getId();
    }

    public void setFollowStatus(int i) {
        if (this.member != null) {
            this.member.setFollowStatus(i);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PostDataBean)) {
            return false;
        }
        if (this.postId == ((PostDataBean) obj).postId) {
            return true;
        }
        return false;
    }

    public static Post getPostFromPostDataBean(PostDataBean postDataBean) {
        String toJSONString = JSON.toJSONString(postDataBean);
        Post post = new Post();
        try {
            post.parseBaseInfo(new JSONObject(toJSONString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSON.parseObject("", PostDataBean.class);
        return post;
    }

    public static PostDataBean getPostDataBeanFromJson(com.alibaba.fastjson.JSONObject jSONObject) {
        return (PostDataBean) JSON.parseObject(JSON.toJSONString(jSONObject), PostDataBean.class);
    }

    public static PostDataBean getPostDataBeanFromJson(JSONObject jSONObject) {
        return (PostDataBean) JSON.parseObject(jSONObject.toString(), PostDataBean.class);
    }
}
