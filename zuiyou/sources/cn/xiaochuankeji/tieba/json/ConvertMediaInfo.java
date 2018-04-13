package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class ConvertMediaInfo {
    @JSONField(name = "fmt")
    public String fmt;
    @JSONField(name = "h")
    public int height;
    @JSONField(name = "md5b64")
    public String md5;
    @JSONField(name = "id")
    public long mediaServerId;
    @JSONField(name = "resid")
    public String resId;
    @JSONField(name = "restype")
    public String resType;
    @JSONField(name = "o")
    public int rotate;
    @JSONField(name = "uri")
    public String uri;
    @JSONField(name = "url")
    public String url;
    @JSONField(name = "thumb_uri")
    public String videoThumbUri;
    @JSONField(name = "w")
    public int width;
}
