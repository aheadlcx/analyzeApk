package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

public class UgcVideoSubDanmakus {
    @JSONField(name = "list")
    public List<UgcVideoDanmakuJson> danmakus = new ArrayList();
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "offset")
    public String offset;
    @JSONField(name = "info")
    public UgcVideoDanmakuJson parentInfo;
    @JSONField(name = "total")
    public int total;
}