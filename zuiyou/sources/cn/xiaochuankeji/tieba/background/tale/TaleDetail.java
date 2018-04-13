package cn.xiaochuankeji.tieba.background.tale;

import com.alibaba.fastjson.annotation.JSONField;

public class TaleDetail {
    @JSONField(name = "abstract")
    public String abstractX;
    @JSONField(name = "author")
    public TaleAuthor author;
    @JSONField(name = "comment_cnt")
    public int commentCnt;
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "content_img_count")
    public int contentImgCount;
    @JSONField(name = "cover")
    public long cover;
    @JSONField(name = "ct")
    public long ct;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "is_folded")
    public int is_folded;
    @JSONField(name = "like_cnt")
    public int likeCnt;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "share_cnt")
    public int shareCnt;
    @JSONField(name = "theme")
    public TaleTheme theme;
    @JSONField(name = "theme_id")
    public long themeId;
    @JSONField(name = "ut")
    public long ut;
}
