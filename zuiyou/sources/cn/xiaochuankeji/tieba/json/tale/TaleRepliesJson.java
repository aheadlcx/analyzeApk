package cn.xiaochuankeji.tieba.json.tale;

import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TaleRepliesJson {
    @JSONField(name = "comment")
    public TaleComment comment;
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "list")
    public List<TaleComment> list;
    @JSONField(name = "more")
    public boolean more;
}
