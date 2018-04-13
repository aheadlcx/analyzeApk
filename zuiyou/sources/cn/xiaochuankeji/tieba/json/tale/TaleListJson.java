package cn.xiaochuankeji.tieba.json.tale;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TaleListJson {
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "articles")
    public List<FollowPostArticleJson> list;
    @JSONField(name = "more")
    public boolean more;
    @JSONField(name = "theme")
    public FollowPostThemeJson theme;
}
