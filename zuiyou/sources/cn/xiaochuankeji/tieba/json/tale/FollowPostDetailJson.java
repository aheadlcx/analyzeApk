package cn.xiaochuankeji.tieba.json.tale;

import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import com.alibaba.fastjson.annotation.JSONField;

public class FollowPostDetailJson {
    @JSONField(name = "article")
    public TaleDetail detail;
}
