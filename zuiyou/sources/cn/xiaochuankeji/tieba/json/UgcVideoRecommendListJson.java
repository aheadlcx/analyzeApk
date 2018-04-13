package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class UgcVideoRecommendListJson {
    @JSONField(name = "list")
    public List<UgcVideoInfoBean> followerList;
    @JSONField(name = "refreshIndex")
    public int refreshItemIndex;
    @JSONField(name = "event_summary")
    public UgcEventSummaryJson summaryJson;
}
