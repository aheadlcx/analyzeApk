package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class UgcVideoDanmakuMsgJson {
    @JSONField(name = "ainfo")
    public UgcVideoDanmakuJson ancestorDanmaku;
    @JSONField(name = "list")
    public List<UgcVideoDanmakuJson> danmakus;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public long offset;
    @JSONField(name = "sinfo")
    public UgcVideoDanmakuJson sourceDanmaku;
}
