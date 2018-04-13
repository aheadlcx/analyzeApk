package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import com.alibaba.fastjson.annotation.JSONField;

public class HollowMsgJson {
    @JSONField(name = "xmsg")
    public MsgDataBean message;
}
