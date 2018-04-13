package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckUrlJson {
    public static final int LINK_TYPE_URL = 0;
    public static final int LINK_TYPE_VIDEO = 2;
    public static final int LINK_TYPE_WECHAT = 1;
    public static final int LINK_TYPE_WY = 3;
    public static final int STATUS_CONTINUE = 0;
    public static final int STATUS_FAIL = -1;
    public static final int STATUS_SUCCESS = 1;
    @JSONField(name = "status")
    public int status;
    @JSONField(name = "pageres")
    public PageRes webPage;

    public static class PageRes {
        @JSONField(name = "author")
        public String author = "";
        @JSONField(name = "desc")
        public String describe = "";
        @JSONField(name = "url_type")
        public int linkType = -1;
        @JSONField(name = "thumbid")
        public long thumbId = -1;
        @JSONField(name = "thumburl")
        public String thumbUrl = "";
        @JSONField(name = "title")
        public String title = "";
        @JSONField(name = "url")
        public String url = "";
        @JSONField(name = "vd_dur")
        public long videoDuration = -1;
        @JSONField(name = "vd_url")
        public String videoUrl = "";

        public String toString() {
            return String.format("thumb:%s, \nurl:%s, \ntitle:%s, \nlinktype:%d, \nvideo_url:%s, \nthumbId:%d", new Object[]{this.thumbUrl, this.url, this.title, Integer.valueOf(this.linkType), this.videoUrl, Long.valueOf(this.thumbId)});
        }
    }

    public String toString() {
        return String.format("status:%d, \nwebpage:%s", new Object[]{Integer.valueOf(this.status), this.webPage.toString()});
    }
}
