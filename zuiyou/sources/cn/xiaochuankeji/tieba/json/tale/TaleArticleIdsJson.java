package cn.xiaochuankeji.tieba.json.tale;

import com.alibaba.fastjson.annotation.JSONField;

public class TaleArticleIdsJson {
    @JSONField(name = "article_ids")
    public long[] articleIds;
}
