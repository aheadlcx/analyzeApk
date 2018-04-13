package cn.xiaochuankeji.tieba.json.recommend;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

public class WebPageBean implements Serializable {
    @JSONField(name = "author")
    public String author;
    @JSONField(name = "desc")
    public String describe;
    @JSONField(name = "thumburl")
    public String thumbUrl;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "url_type")
    public int type;
    @JSONField(name = "url")
    public String url;
}
