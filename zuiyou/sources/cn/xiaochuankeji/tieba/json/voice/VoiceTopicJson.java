package cn.xiaochuankeji.tieba.json.voice;

import cn.xiaochuankeji.tieba.json.TopicJson;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class VoiceTopicJson {
    @JSONField(name = "list")
    public List<TopicJson> list;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public int offset;
}
