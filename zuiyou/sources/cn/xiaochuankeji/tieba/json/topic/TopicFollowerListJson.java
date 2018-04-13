package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TopicFollowerListJson {
    @JSONField(name = "list")
    public List<TopicMemberInfoBean> followerList;
    @JSONField(name = "more")
    public int hasMore;
    @JSONField(name = "offset")
    public int offset;
    @JSONField(name = "talent_show")
    public String talentName;
}
