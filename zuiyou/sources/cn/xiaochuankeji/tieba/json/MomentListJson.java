package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.background.data.post.Moment;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

public class MomentListJson {
    @JSONField(name = "list")
    public List<Moment> momentList = new ArrayList();
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public long offset;
    @JSONField(name = "total")
    public int total;
}
