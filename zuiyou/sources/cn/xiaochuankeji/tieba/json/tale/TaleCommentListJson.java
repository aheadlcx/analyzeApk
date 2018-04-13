package cn.xiaochuankeji.tieba.json.tale;

import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TaleCommentListJson {
    @JSONField(name = "comments")
    public List<TaleComment> comments;
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "more")
    public boolean more;
}
