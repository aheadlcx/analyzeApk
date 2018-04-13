package cn.xiaochuankeji.tieba.background.tale;

import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class TaleComment {
    @JSONField(name = "article_id")
    public long articleId;
    @JSONField(name = "author")
    public TaleAuthor author;
    public long count;
    @JSONField(name = "ct")
    public long ct;
    public TaleDetail detail;
    public String from;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "imgs")
    public List<Long> imgs;
    public int layoutType = R.layout.item_tale_detail_comment;
    @JSONField(name = "like_cnt")
    public int likeCnt;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "origin_author")
    public TaleAuthor originAuthor;
    @JSONField(name = "ref_id")
    public long refId;
    public a tale;
    public String title;
    @JSONField(name = "txt")
    public String txt;
    public int type;
    @JSONField(name = "ut")
    public long ut;
}
