package cn.xiaochuankeji.tieba.json.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ImgResultJson {
    @JSONField(name = "fmt")
    public String fmt;
    @JSONField(name = "h")
    public int height;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "md5")
    public String md5;
    @JSONField(name = "uri")
    public String uri;
    @JSONField(name = "w")
    public int width;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
