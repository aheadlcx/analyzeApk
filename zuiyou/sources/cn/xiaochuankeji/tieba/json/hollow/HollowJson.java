package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import com.alibaba.fastjson.annotation.JSONField;

public class HollowJson {
    @JSONField(name = "xroom")
    public RoomDataBean room;
}
