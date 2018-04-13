package cn.xiaochuankeji.tieba.json.voice;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class VoiceListJson {
    @JSONField(name = "list")
    public List<PostDataBean> list;
}
