package cn.xiaochuankeji.tieba.json.feed;

import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class FeedMemberListJson {
    @JSONField(name = "list")
    public List<MemberInfoBean> list;
    @JSONField(name = "offset")
    public long offset;
}
