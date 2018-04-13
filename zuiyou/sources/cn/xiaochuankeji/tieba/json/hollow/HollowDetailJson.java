package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class HollowDetailJson {
    @JSONField(name = "xmember")
    public MemberDataBean member;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "next_cb")
    public String nextCb;
    @JSONField(name = "xroom")
    public RoomDataBean room;
    @JSONField(name = "xmsg_list")
    public List<MsgDataBean> roomMsgList;
}
