package cn.xiaochuankeji.tieba.json.tale;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class ThemeListJson {
    @JSONField(name = "cursor")
    public String cursor;
    @JSONField(name = "more")
    public boolean more;
    @JSONField(name = "themes")
    public List<FollowPostThemeJson> themes;
}
