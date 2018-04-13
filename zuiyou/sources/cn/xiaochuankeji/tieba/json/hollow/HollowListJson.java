package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class HollowListJson {
    @JSONField(name = "list")
    public List<HollowRecommendItemBean> hollowList;
    @JSONField(name = "next_cb")
    public String nextCb;
}
