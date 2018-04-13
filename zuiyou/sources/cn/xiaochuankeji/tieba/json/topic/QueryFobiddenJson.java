package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryFobiddenJson {
    @JSONField(name = "black")
    public boolean isFobidden;
}
