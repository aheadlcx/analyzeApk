package cn.xiaochuankeji.tieba.json.tale;

import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import com.alibaba.fastjson.annotation.JSONField;

public class TaleCommentJson {
    @JSONField(name = "comment")
    public TaleComment comment;
}
