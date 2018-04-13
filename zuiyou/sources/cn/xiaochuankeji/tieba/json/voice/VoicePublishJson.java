package cn.xiaochuankeji.tieba.json.voice;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.annotation.JSONField;

public class VoicePublishJson {
    @JSONField(name = "post")
    public PostDataBean post;
}
