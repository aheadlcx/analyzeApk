package cn.xiaochuankeji.tieba.ui.hollow.data;

import com.alibaba.fastjson.annotation.JSONField;

public class HollowRecommendItemBean {
    @JSONField(name = "audio")
    public AudioDataBean audio;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "emotion")
    public EmotionDataBean emotion;
    @JSONField(name = "hugged")
    public int hugged;
    @JSONField(name = "hugs")
    public long hugs;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "xmember")
    public MemberDataBean member;
    @JSONField(name = "msg_count")
    public int msgCount;
    @JSONField(name = "self")
    public long self;
    @JSONField(name = "subject")
    public String subject;

    public boolean equals(Object obj) {
        if ((obj instanceof HollowRecommendItemBean) && ((HollowRecommendItemBean) obj).id == this.id) {
            return true;
        }
        return false;
    }

    public void a(HollowRecommendItemBean hollowRecommendItemBean) {
        this.id = hollowRecommendItemBean.id;
        this.subject = hollowRecommendItemBean.subject;
        this.audio = hollowRecommendItemBean.audio;
        this.emotion = hollowRecommendItemBean.emotion;
        this.member = hollowRecommendItemBean.member;
        this.msgCount = hollowRecommendItemBean.msgCount;
        this.hugged = hollowRecommendItemBean.hugged;
        this.hugs = hollowRecommendItemBean.hugs;
        this.createTime = hollowRecommendItemBean.createTime;
        this.self = hollowRecommendItemBean.self;
    }

    public static HollowRecommendItemBean a(RoomDataBean roomDataBean) {
        HollowRecommendItemBean hollowRecommendItemBean = new HollowRecommendItemBean();
        hollowRecommendItemBean.id = roomDataBean.id;
        hollowRecommendItemBean.audio = roomDataBean.audio;
        hollowRecommendItemBean.subject = roomDataBean.subject;
        hollowRecommendItemBean.emotion = roomDataBean.emotion;
        hollowRecommendItemBean.member = roomDataBean.member;
        hollowRecommendItemBean.msgCount = (int) roomDataBean.msgCount;
        hollowRecommendItemBean.hugged = roomDataBean.hugged;
        hollowRecommendItemBean.hugs = roomDataBean.hugs;
        hollowRecommendItemBean.createTime = roomDataBean.createTime;
        hollowRecommendItemBean.self = roomDataBean.self;
        return hollowRecommendItemBean;
    }

    public static RoomDataBean b(HollowRecommendItemBean hollowRecommendItemBean) {
        RoomDataBean roomDataBean = new RoomDataBean();
        roomDataBean.audio = hollowRecommendItemBean.audio;
        roomDataBean.emotion = hollowRecommendItemBean.emotion;
        roomDataBean.createTime = hollowRecommendItemBean.createTime;
        roomDataBean.id = hollowRecommendItemBean.id;
        roomDataBean.member = hollowRecommendItemBean.member;
        roomDataBean.msgCount = (long) hollowRecommendItemBean.msgCount;
        roomDataBean.hugged = hollowRecommendItemBean.hugged;
        roomDataBean.hugs = hollowRecommendItemBean.hugs;
        roomDataBean.subject = hollowRecommendItemBean.subject;
        roomDataBean.self = hollowRecommendItemBean.self;
        return roomDataBean;
    }
}
