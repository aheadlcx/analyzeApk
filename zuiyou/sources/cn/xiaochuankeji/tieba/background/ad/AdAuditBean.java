package cn.xiaochuankeji.tieba.background.ad;

import com.alibaba.fastjson.annotation.JSONField;

public class AdAuditBean {
    @JSONField(name = "description")
    public String adDesc;
    @JSONField(name = "adid")
    public String adId;
    @JSONField(name = "title")
    public String adTitle;
    @JSONField(name = "type")
    public String adtype;
    @JSONField(name = "download_url")
    public String apkDownloadUrl;
    @JSONField(name = "appid")
    public String appId;
    @JSONField(name = "image_url")
    public String imageUrl;
    @JSONField(name = "lp_open_url")
    public String lpOpenUrl;
    @JSONField(name = "adslot")
    public String slotId;
    @JSONField(name = "video_url")
    public String videoUrl;
}
