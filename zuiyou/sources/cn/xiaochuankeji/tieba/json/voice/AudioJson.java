package cn.xiaochuankeji.tieba.json.voice;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class AudioJson implements Serializable {
    @JSONField(name = "dur")
    public int dur;
    @JSONField(name = "url")
    public String url;
}
