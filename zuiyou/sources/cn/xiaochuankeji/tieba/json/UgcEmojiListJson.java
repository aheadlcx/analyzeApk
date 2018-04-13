package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;

public class UgcEmojiListJson {
    @JSONField(name = "cateid")
    public int cateid;
    @JSONField(name = "list")
    public ArrayList<UgcEmoji> list = new ArrayList();
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public int offset;
    @JSONField(name = "ver")
    public long version;
}
