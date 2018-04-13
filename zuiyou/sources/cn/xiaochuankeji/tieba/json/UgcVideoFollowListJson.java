package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class UgcVideoFollowListJson {
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public String offset;
    @JSONField(name = "list")
    public List<UgcVideoInfoBean> ugcVideoList;
}
