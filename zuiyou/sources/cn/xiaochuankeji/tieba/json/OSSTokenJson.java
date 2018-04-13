package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class OSSTokenJson {
    @JSONField(name = "appid")
    public String appKey;
    @JSONField(name = "endpoint")
    public String endpoint;
    @JSONField(name = "imgbuck")
    public String imageBucket;
    @JSONField(name = "imgdir")
    public String imageDir;
    @JSONField(name = "appsec")
    public String keySecret;
    @JSONField(name = "apptoken")
    public String token;
    @JSONField(name = "videobuck")
    public String videoBucket;
    @JSONField(name = "videodir")
    public String videoDir;
}
