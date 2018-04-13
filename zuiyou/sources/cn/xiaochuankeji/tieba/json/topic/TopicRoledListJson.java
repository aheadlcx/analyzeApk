package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TopicRoledListJson {
    @JSONField(name = "admin_members")
    public List<TopicMemberInfoBean> adminMembers;
    @JSONField(name = "guard_members")
    public List<TopicMemberInfoBean> guardMembers;
    @JSONField(name = "talent_members")
    public List<TopicMemberInfoBean> talentMembers;
    @JSONField(name = "talent_show")
    public String talentShow;
}
