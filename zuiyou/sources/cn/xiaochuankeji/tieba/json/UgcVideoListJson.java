package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class UgcVideoListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public long offset;
    @JSONField(name = "total")
    public int total;
    @JSONField(name = "list")
    public List<UgcVideoInfoBean> ugcVideoList;
}
