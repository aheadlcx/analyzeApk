package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import com.alibaba.fastjson.annotation.JSONField;

public class UgcEventSummaryJson {
    @JSONField(name = "activity_icon")
    public EventIcon eventIcon;
    @JSONField(name = "index_activities")
    public UgcEventJsonInRecommend eventJson;

    public class EventIcon {
        @JSONField(name = "img")
        public ServerImgJson img;
        @JSONField(name = "type")
        public String type;
        @JSONField(name = "url")
        public String url;
    }

    public class UgcEventJsonInRecommend {
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "banner")
        public ServerImgJson img;
        @JSONField(name = "post")
        public UgcVideoInfoBean post;
        @JSONField(name = "type")
        public String type = "";
        @JSONField(name = "url")
        public String url;
    }
}
