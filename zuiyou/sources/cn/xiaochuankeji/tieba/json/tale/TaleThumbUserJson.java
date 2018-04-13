package cn.xiaochuankeji.tieba.json.tale;

import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TaleThumbUserJson {
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "more")
    public boolean more;
    @JSONField(name = "users")
    public List<TaleAuthor> users;
}
