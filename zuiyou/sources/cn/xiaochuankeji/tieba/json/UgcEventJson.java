package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.alibaba.fastjson.annotation.JSONField;

public class UgcEventJson {
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "banner")
    public ServerImgJson img;
    @JSONField(name = "post")
    public Moment momentJson;
    @JSONField(name = "type")
    public String type = "";
    @JSONField(name = "url")
    public String url;
}
