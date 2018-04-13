package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class MyHollowListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "next_cb")
    public String nextCb;
    @JSONField(name = "list")
    public List<RoomDataBean> roomDataList;
}
