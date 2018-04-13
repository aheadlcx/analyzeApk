package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class BlockUserJson {
    @JSONField(name = "list")
    public List<MemberInfoBean> blockList;
    @JSONField(name = "more")
    public int hasMore;
    @JSONField(name = "offset")
    public int offset;
}
