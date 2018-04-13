package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class HollowMsgListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "list")
    public List<MsgDataBean> msgList;
    @JSONField(name = "next_cb")
    public String next_cb;
}
