package cn.xiaochuankeji.tieba.json.videojson;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class ServerVideoJson implements Serializable {
    @JSONField(name = "dur")
    public long duration;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "urlsrc_logo")
    public String logoUrl;
    @JSONField(name = "priority")
    public int priority;
    @JSONField(name = "urlsrc")
    public String url;
}
