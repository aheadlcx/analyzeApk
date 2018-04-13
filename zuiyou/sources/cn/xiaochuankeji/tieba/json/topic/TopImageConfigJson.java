package cn.xiaochuankeji.tieba.json.topic;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TopImageConfigJson {
    @JSONField(serialize = false)
    public long dbId;
    @JSONField(name = "list")
    public List<ImageId> imageIdList;
    @JSONField(name = "version")
    public int imageVersion;

    public static class ImageId {
        @JSONField(name = "big_id")
        public long bigImageId;
        @JSONField(name = "small_id")
        public long smallImageId;
    }
}
