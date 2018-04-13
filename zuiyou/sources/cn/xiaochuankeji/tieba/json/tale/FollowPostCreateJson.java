package cn.xiaochuankeji.tieba.json.tale;

import com.alibaba.fastjson.annotation.JSONField;

public class FollowPostCreateJson {
    @JSONField(name = "article")
    public FollowPostArticleJson article;
    @JSONField(name = "theme")
    public FollowPostThemeJson theme;
}
