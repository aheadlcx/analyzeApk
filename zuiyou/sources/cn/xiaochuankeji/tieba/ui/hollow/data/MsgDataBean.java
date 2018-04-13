package cn.xiaochuankeji.tieba.ui.hollow.data;

import com.alibaba.fastjson.annotation.JSONField;

public class MsgDataBean {
    @JSONField(name = "audio")
    public AudioDataBean audio;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "likes")
    public long likes;
    @JSONField(name = "xmember")
    public MemberDataBean member;
    @JSONField(name = "xroom")
    public RoomDataBean room;
    @JSONField(name = "xroom_id")
    public long room_id;
    @JSONField(name = "self")
    public long self;
    @JSONField(name = "text")
    public String text;
}
