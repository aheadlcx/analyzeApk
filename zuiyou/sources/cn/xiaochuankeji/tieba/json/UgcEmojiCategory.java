package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcEmojiCategory {
    @JSONField(name = "emoji_num")
    public int emoji_num;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "pos")
    public int pos;
    @JSONField(name = "title")
    public String title;
}
