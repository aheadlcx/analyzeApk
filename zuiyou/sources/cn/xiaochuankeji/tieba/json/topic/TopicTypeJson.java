package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;

public class TopicTypeJson {
    @JSONField(name = "partition_name")
    public String typeName;
}
