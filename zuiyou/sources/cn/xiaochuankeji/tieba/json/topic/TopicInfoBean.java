package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class TopicInfoBean implements Serializable {
    @JSONField(name = "addition")
    public String _addition;
    @JSONField(name = "atts_title")
    public String _attsTitle;
    @JSONField(name = "newcnt")
    public int _newPostCount;
    @JSONField(name = "partners")
    public long _partners;
    @JSONField(name = "posts")
    public int _postCount;
    @JSONField(name = "cover")
    public long _topicCoverID;
    @JSONField(name = "attinfo")
    public AttInfo attInfo;
    @JSONField(name = "blocked")
    public int blocked;
    @JSONField(name = "click_cb")
    public String click_cb;
    @JSONField(name = "enable_black")
    public int enable_black;
    @JSONField(name = "enable_guard")
    public int enable_guard;
    @JSONField(name = "guard_recruiting")
    public int guard_recruiting;
    @JSONField(name = "icon")
    public String icon;
    @JSONField(name = "top")
    public long isTop;
    @JSONField(name = "isadm")
    public int isadm;
    @JSONField(name = "list_show")
    public String list_show;
    @JSONField(name = "recruiting")
    public int recruiting;
    @JSONField(name = "share")
    public int share;
    @JSONField(name = "id")
    public long topicID;
    @JSONField(name = "topic")
    public String topicName;

    class AttInfo implements Serializable {
        @JSONField(name = "trank")
        public String trank;
        @JSONField(name = "up")
        public String up;

        AttInfo() {
        }
    }
}
