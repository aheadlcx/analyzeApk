package cn.xiaochuankeji.tieba.json.upload;

import com.alibaba.fastjson.annotation.JSONField;

public class BlockInitJson {
    @JSONField(name = "bsize")
    public int bsize;
    @JSONField(name = "uploadid")
    public long uploadid;
}
