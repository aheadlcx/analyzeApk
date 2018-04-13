package cn.xiaochuankeji.tieba.background.data.post;

import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.topic.TopicInfoBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Moment extends AbstractPost implements Serializable {
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "danmakus")
    public long danmakuCount;
    @JSONField(name = "activity_desc")
    public String eventDesc;
    @JSONField(name = "activity_title")
    public String eventTitle;
    @JSONField(name = "reviews")
    public long followCount;
    public boolean hasUpdate = false;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "like")
    public int isLiked;
    @JSONField(name = "likes")
    public long likeCount;
    @JSONField(name = "member")
    public MemberInfoBean member;
    @JSONField(name = "mid")
    public long memberId;
    @JSONField(name = "plays")
    public int playCount;
    @JSONField(name = "reviews_bg")
    public String reviewBack;
    @JSONField(name = "share")
    public long shareCount;
    @JSONField(name = "status")
    public long status;
    @JSONField(name = "text4zy")
    public String title;
    @JSONField(name = "topic")
    public TopicInfoBean topic = new TopicInfoBean();
    @JSONField(name = "tid")
    public long topicId;
    @JSONField(name = "list")
    public List<UgcVideoInfoBean> ugcVideos = new ArrayList();

    public Moment(JSONObject jSONObject) {
        parseBaseInfo(jSONObject);
    }

    public Moment(com.alibaba.fastjson.JSONObject jSONObject) {
        JSON.parseObject(JSON.toJSONString(jSONObject), Moment.class);
    }

    public void parseBaseInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                Moment moment = (Moment) JSON.parseObject(jSONObject.toString(), Moment.class);
                if (moment != null) {
                    this.id = moment.id;
                    this.topicId = moment.topicId;
                    this.createTime = moment.createTime;
                    this.memberId = moment.memberId;
                    this.title = moment.title;
                    this.playCount = moment.playCount;
                    this.danmakuCount = moment.danmakuCount;
                    this.reviewBack = moment.reviewBack;
                    this.shareCount = moment.shareCount;
                    this.followCount = moment.followCount;
                    this.eventTitle = moment.eventTitle;
                    this.eventDesc = moment.eventDesc;
                    this.likeCount = moment.likeCount;
                    this.isLiked = moment.isLiked;
                    this.member = moment.member;
                    this.topic = moment.topic;
                    this.ugcVideos = moment.ugcVideos;
                }
            } catch (Exception e) {
            }
        }
    }

    public JSONObject serializeTo() throws JSONException {
        if (this.ugcVideos == null || this.ugcVideos.size() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(JSON.toJSONString(this));
        if (jSONObject == null) {
            return jSONObject;
        }
        jSONObject.put("AbstractPost_classType", classType());
        return jSONObject;
    }

    public int classType() {
        return 3;
    }

    public long getMemberId() {
        return this.member.getId();
    }

    public void setFollowStatus(int i) {
        this.member.setFollowStatus(i);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Moment) && ((Moment) obj).id == this.id) {
            return true;
        }
        return false;
    }
}
