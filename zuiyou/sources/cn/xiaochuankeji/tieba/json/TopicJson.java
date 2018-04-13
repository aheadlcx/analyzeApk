package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class TopicJson {
    @JSONField(name = "atts_title")
    public String atts_title;
    @JSONField(name = "cover")
    public long cover;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "partners")
    public int partners;
    @JSONField(name = "topic")
    public String topic;
}
