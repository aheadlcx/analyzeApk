package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TopicRoleApplyListJson {
    @JSONField(name = "list")
    public List<TopicMemberInfoBean> applyList;
    @JSONField(name = "more")
    public int hasMore;
    @JSONField(name = "t")
    public long lastTimestamp;
}
