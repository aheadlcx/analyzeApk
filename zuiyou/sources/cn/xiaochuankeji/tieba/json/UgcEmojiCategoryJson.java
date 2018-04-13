package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;

public class UgcEmojiCategoryJson {
    @JSONField(name = "cateid")
    public int cateid;
    @JSONField(name = "cates")
    public ArrayList<UgcEmojiCategory> cates;
    @JSONField(name = "list")
    public ArrayList<UgcEmoji> list;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public int offset;

    public String toString() {
        return "UgcEmojiCategoryJson{list=" + this.list + ", cates=" + this.cates + ", cateid=" + this.cateid + ", offset=" + this.offset + ", more=" + this.more + '}';
    }
}
